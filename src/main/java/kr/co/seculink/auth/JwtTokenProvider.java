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
		
		// 사용자 별 키를 DB에 저장
		Map<String, String> param = new HashMap<>();
		param.put("userId", userId);
	    param.put("regUserId", userId);
	    param.put("uptUserId", userId);
		param.put("clntId", clientId);
		param.put("keyVal", encKey);
		
		dao.update("TOKN_KEY.merge", param);
		
		// Redis에도 캐시
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
		
		//blacklist 확인
		if (true == this.blackListToken.contains(token)) {
			
			log.debug("blacklist token 요청 : {}", token);
			log.debug("blicklist size : {}", this.blackListToken.size());
			return false;
		}
		
		DecodedJWT jwt = decodeToken(token);
		
		Map<String, Claim> claims = jwt.getClaims();
		
		if (false == claims.containsKey("userId") || false == claims.containsKey("clientId")) {
		
			log.debug("token 정보가 잘못됨");
			
			addBlackListToken(token);
			return false;
		}
		
		String userId = jwt.getClaim("userId").asString();
		String clientId = jwt.getClaim("clientId").asString();
		
		// key 가져오기 Redis먼저
		String secret = "";
		
		// Redis에도 캐시
//		ValueOperations<String, String> vop = redisTemplate.opsForValue();
//		
//		secret = vop.get(userId + "_" + clientId);
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("userId", userId);
		param.put("clntId", clientId);
		
		if (null == secret || secret.isEmpty() == true) {
			//Redis에 없으면 DB확인
			log.debug("Redis에 토큰 정보 없음");
			
			// key 가져오기
			Map<String, String> toknKeyData = this.dao.selectOne("TOKN_KEY.select", param);
			
			if (null == toknKeyData || toknKeyData.containsKey("keyVal") == false ) {
				
				log.debug("DB에 토큰 정보 없음");
				addBlackListToken(token);
				return false;
			}
			
			secret = toknKeyData.get("keyVal");
			
			// Redis에 secret 저장
//			vop.set(userId + clientId, secret);
		} else {
			log.debug("redis에 정보 있음 {}", secret);
		}
		
		try {
		    Algorithm algorithm = getAlgorithm(secret);
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer(this.issuer)
		        .build(); //Reusable verifier instance
		    verifier.verify(token);
		} catch (TokenExpiredException e) {
			
			log.debug("만료된 토큰 key 삭제 ::: token : [{}] / userId : [{}] / clientId : [{}]", jwt.getToken(), userId, clientId);
			
			//토큰 키 삭제
			this.dao.delete("TOKN_KEY.delete", param);
			
			//Redis 삭제
//			this.redisTemplate.delete(userId + "_" + clientId);
			
			this.addBlackListToken(token);
			
			return false;
		} catch (SignatureVerificationException e) {
			
			log.debug("유효한 토큰 아님 잘못된 접근 ::: token : [{}] / userId : [{}] / clientId : [{}]", jwt.getToken(), userId, clientId);
			
			this.addBlackListToken(token);
			return false;
			
		} catch (JWTVerificationException e) {
			
			log.debug("유효한 토큰 아님 ::: token : [{}] / userId : [{}] / clientId : [{}]", e.getMessage(), userId, clientId);
			
			this.addBlackListToken(token);
		    return false;
		}
		
		return true;
	}
	
	private int maxBlacklistTokenSize = 1000;
	
	private void addBlackListToken(String token) {
    	
    	// max가 넘어가면 처음 블랙리스트 삭제
    	if (maxBlacklistTokenSize < blackListToken.size()) {
    		Iterator<String> iter = blackListToken.iterator();
    	    iter.next();
    	    iter.remove();
    	}
    	
    	blackListToken.add(token);
    }
	
}
