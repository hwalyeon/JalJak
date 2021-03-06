package kr.co.seculink.auth;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import kr.co.seculink.exception.SysException;
import kr.co.seculink.util.Seed256EncUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {

	@Value("${jwt.token.issuer}")
	String issuer;
	
	@Value("${jwt.token.expirySeconds}")
	int expirySeconds;
	
	@Autowired
	private SqlSessionTemplate dao;
	
	@Autowired
	private LinkedHashSet<String> blackListToken;
	
//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;
	
	public Algorithm getAlgorithm(String secret) {
		return Algorithm.HMAC256(secret);
	}
	
	public String issueToken(String userId, String clientId, String... roles) throws SysException {
		
		String token = "";
		String encKey = Seed256EncUtil.getRandomKey();
		
		Date expires = Date.from(Instant.now().plusSeconds(this.expirySeconds));
		
		try {
		    Algorithm algorithm = getAlgorithm(encKey);
		    token = JWT.create()
		        .withIssuer(this.issuer)
		        .withExpiresAt(expires)
		        .withClaim("userId", userId)
		        .withClaim("clientId", clientId)
		        .withArrayClaim("roles", roles)
		        .sign(algorithm);
		} catch (JWTCreationException e) {
			e.printStackTrace();			
		    throw new SysException(e, "ESYS001", null);
		}
		
		// ????????? ??? ?????? DB??? ??????
		Map<String, String> param = new HashMap<>();
		param.put("userId", userId);
	    param.put("regUserId", userId);
	    param.put("uptUserId", userId);
		param.put("clntId", clientId);
		param.put("keyVal", encKey);
		
		dao.update("TOKN_KEY.merge", param);
		
		// Redis?????? ??????
//		ValueOperations<String, String> vop = redisTemplate.opsForValue();
//		
//		vop.set(userId + "_" + clientId, encKey);
		
		return token;
	}
	
	public DecodedJWT decodeToken(String token) {
		return JWT.decode(token);
	}
	
	public String getRequestToken(HttpServletRequest request) {
		
		String authorization = request.getHeader("Authorization");
		
		if (null == authorization) {
			return null;
		} else {
			if (authorization.startsWith("Bearer") == false) {
				return null;
			} else {
				String token = authorization.substring("Bearer".length() + 1);
				
				if (null == token || token.length() < 20) {
					return null;
				}
				return token;
			}
		}
	}
	
	public boolean validateToken(String token) {
		
		//blacklist ??????
		if (true == this.blackListToken.contains(token)) {
			
			log.debug("blacklist token ?????? : {}", token);
			log.debug("blicklist size : {}", this.blackListToken.size());
			return false;
		}
		
		DecodedJWT jwt = decodeToken(token);
		
		Map<String, Claim> claims = jwt.getClaims();
		
		if (false == claims.containsKey("userId") || false == claims.containsKey("clientId")) {
		
			log.debug("token ????????? ?????????");
			
			addBlackListToken(token);
			return false;
		}
		
		String userId = jwt.getClaim("userId").asString();
		String clientId = jwt.getClaim("clientId").asString();
		
		// key ???????????? Redis??????
		String secret = "";
		
		// Redis?????? ??????
//		ValueOperations<String, String> vop = redisTemplate.opsForValue();
//		
//		secret = vop.get(userId + "_" + clientId);
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("userId", userId);
		param.put("clntId", clientId);
		
		if (null == secret || secret.isEmpty() == true) {
			//Redis??? ????????? DB??????
			log.debug("Redis??? ?????? ?????? ??????");
			
			// key ????????????
			Map<String, String> toknKeyData = this.dao.selectOne("TOKN_KEY.select", param);
			
			if (null == toknKeyData || toknKeyData.containsKey("keyVal") == false ) {
				
				log.debug("DB??? ?????? ?????? ??????");
				addBlackListToken(token);
				return false;
			}
			
			secret = toknKeyData.get("keyVal");
			
			// Redis??? secret ??????
//			vop.set(userId + clientId, secret);
		} else {
			log.debug("redis??? ?????? ?????? {}", secret);
		}
		
		try {
		    Algorithm algorithm = getAlgorithm(secret);
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer(this.issuer)
		        .build(); //Reusable verifier instance
		    verifier.verify(token);
		} catch (TokenExpiredException e) {
			
			log.debug("????????? ?????? key ?????? ::: token : [{}] / userId : [{}] / clientId : [{}]", jwt.getToken(), userId, clientId);
			
			//?????? ??? ??????
			this.dao.delete("TOKN_KEY.delete", param);
			
			//Redis ??????
//			this.redisTemplate.delete(userId + "_" + clientId);
			
			this.addBlackListToken(token);
			
			return false;
		} catch (SignatureVerificationException e) {
			
			log.debug("????????? ?????? ?????? ????????? ?????? ::: token : [{}] / userId : [{}] / clientId : [{}]", jwt.getToken(), userId, clientId);
			
			this.addBlackListToken(token);
			return false;
			
		} catch (JWTVerificationException e) {
			
			log.debug("????????? ?????? ?????? ::: token : [{}] / userId : [{}] / clientId : [{}]", e.getMessage(), userId, clientId);
			
			this.addBlackListToken(token);
		    return false;
		}
		
		return true;
	}
	
	private int maxBlacklistTokenSize = 1000;
	
	private void addBlackListToken(String token) {
    	
    	// max??? ???????????? ?????? ??????????????? ??????
    	if (maxBlacklistTokenSize < blackListToken.size()) {
    		Iterator<String> iter = blackListToken.iterator();
    	    iter.next();
    	    iter.remove();
    	}
    	
    	blackListToken.add(token);
    }
	
}
