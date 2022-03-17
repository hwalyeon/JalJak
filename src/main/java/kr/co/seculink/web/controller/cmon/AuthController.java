package kr.co.seculink.web.controller.cmon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import kr.co.seculink.domain.GEConstant.API_DIV;
import kr.co.seculink.domain.RtnMsg;
import kr.co.seculink.web.model.cmon.SessionVO;
import kr.co.seculink.web.service.cmon.AuthService;
import kr.co.seculink.auth.CustomAuthenticationProvider;
import kr.co.seculink.auth.JwtTokenProvider;
import kr.co.seculink.exception.BaseException;
import kr.co.seculink.exception.BizException;
import kr.co.seculink.exception.SysException;
import kr.co.seculink.util.GEUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
//@Api(tags = {"[" + API_DIV.인증 + "] 인증 API"})
@RequestMapping("/v1/auth")
public class AuthController {
	
	@Autowired 
	private CustomAuthenticationProvider authenticationProvider;
	
//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	private SqlSessionTemplate dao;
	
	@Autowired
    private JwtTokenProvider jwtTokenProvider;
	
	@Resource(name = "authService")
	private AuthService authService;
	
	private ObjectMapper om = new ObjectMapper();
	
	@PostMapping("/login.ab")
	public RtnMsg login(@RequestBody Map<String, String> params) throws BaseException {
		
		RtnMsg rtnMsg = new RtnMsg();

		try {
			
			// DB에서 사용자 정보 확인
			Map<String, String> userInfo = dao.selectOne("cmon.user.searchUserInfo", params);
			
			if (null == userInfo) {
				throw new BizException("ECOM007"); // 사용자ID 또는 비밀번호 오류
			}
			
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			
			// 권한조회
			List<Map<String, String>> roleList = dao.selectList("cmon.user.searchRoleList", params);
			
			String[] roles = new String[roleList.size()];
			
			for ( int i = 0 ; i < roleList.size() ; i++ )
			{
				Map<String, String> roleMap = roleList.get(i);
				
				grantedAuths.add(new SimpleGrantedAuthority(roleMap.get("roleCd")));
				roles[i] = roleMap.get("roleCd");
			}
			
//			String[] roles = new String[] {ROLE.일반사용자};
//			
//			List<GrantedAuthority> grantedAuths = new ArrayList<>();
//			
//			grantedAuths.add(new SimpleGrantedAuthority(ROLE.일반사용자));

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(params.get("userId"), params.get("userId"), grantedAuths);
			SecurityContextHolder.getContext().setAuthentication(this.authenticationProvider.authenticate(authToken));
			
			// token 발행
			String token = jwtTokenProvider.issueToken(params.get("userId"), params.get("userId"), roles);
	
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("jwtToken", token);
			result.put("userInfo", userInfo);
			result.put("roleList", roleList);
			
			rtnMsg.setRtnData(result);
			
			
		} catch (UsernameNotFoundException e) {
			
			throw new BizException("ECOM007"); // 사용자ID 또는 비밀번호 오류
		} catch (AuthenticationException e) {
			
			throw new SysException(e, "ECOM008", null); // 로그인 처리 중 오류
		} catch (BaseException e) {
			throw e;
		}
				
		log.debug("session user id {}", GEUtil.getSessionUserId());
		
		return rtnMsg;
	}
	
	@PostMapping("/stdtLogin")
	public RtnMsg stdtLogin(@RequestBody Map<String, String> params) throws BaseException {
		
		RtnMsg rtnMsg = new RtnMsg();

		try {
			
			// DB에서 사용자 정보 확인
			Map<String, String> userInfo = dao.selectOne("cmon.user.searchUserInfo", params);
			
			if ( null == userInfo ) {
				throw new BizException("ECOM007"); // 사용자ID 또는 비밀번호 오류
			} else {
				if ( "N".equals(userInfo.get("stdtYn")) ) {
					throw new BizException("ECOM007"); // 사용자ID 또는 비밀번호 오류
				}
			}
			
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			
			// 권한조회
			List<Map<String, String>> roleList = dao.selectList("cmon.user.searchRoleList", params);
			
			String[] roles = new String[roleList.size()];
			
			for ( int i = 0 ; i < roleList.size() ; i++ )
			{
				Map<String, String> roleMap = roleList.get(i);
				
				grantedAuths.add(new SimpleGrantedAuthority(roleMap.get("roleCd")));
				roles[i] = roleMap.get("roleCd");
			}
			
//			String[] roles = new String[] {ROLE.일반사용자};
//			
//			List<GrantedAuthority> grantedAuths = new ArrayList<>();
//			
//			grantedAuths.add(new SimpleGrantedAuthority(ROLE.일반사용자));

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(params.get("userId"), params.get("userId"), grantedAuths);
			SecurityContextHolder.getContext().setAuthentication(this.authenticationProvider.authenticate(authToken));
			
			// token 발행
			String token = jwtTokenProvider.issueToken(params.get("userId"), params.get("userId"), roles);
	
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("jwtToken", token);
			result.put("userInfo", userInfo);
			result.put("roleList", roleList);
			
			rtnMsg.setRtnData(result);
			
			
		} catch (UsernameNotFoundException e) {
			
			throw new BizException("ECOM007"); // 사용자ID 또는 비밀번호 오류
		} catch (AuthenticationException e) {
			
			throw new SysException(e, "ECOM008", null); // 로그인 처리 중 오류
		} catch (BaseException e) {
			throw e;
		}
				
		log.debug("session user id {}", GEUtil.getSessionUserId());
		
		return rtnMsg;
	}
	
	@PostMapping("/logout")
//	@ApiOperation(value = "[" + API_DIV.마이페이지 + "-2000] 로그아웃 <Z완>", notes = "<로그아웃> 버튼 https://zpl.io/aBD6Z8K")
	public RtnMsg<Void> logout() {	

		// Session (Token) 정보에서 userId 및 clientId 취득
		SessionVO vo = GEUtil.getSessionVo();

		String userId   = "admin";
		String clientId = "admin";
		
		if ( vo != null ) {
			userId   = vo.getUserId();
			clientId = vo.getClntId();			
		}
		
		Map<String, String> dbParam = new HashMap<String, String>();

		dbParam.put("userId", userId);
		dbParam.put("clntId", clientId);

		// 토큰 키 삭제
		this.dao.delete("TOKN_KEY.delete", dbParam);

		// Redis 삭제
//		this.redisTemplate.delete(userId + "_" + clientId);

		RtnMsg<Void> rtnMsg = new RtnMsg<>();

		return rtnMsg;
	}


//	@PostMapping("/loginSns")
//	@ApiOperation(value = "[" + API_DIV.인증 +"-0600] SNS 로그인 <Z완료>", notes = "[로그인] <로그인> 버튼 https://zpl.io/a846J7J")
//	public RtnMsg<GEA0620> loginSns(@Valid @RequestBody GEA0610 params) throws BaseException {
//		
//		RtnMsg<GEA0620> rtnMsg = new RtnMsg<>();
//
//		try {
//		
//			Map<String, Object> dbParams = om.convertValue(params, Map.class);
//			
//			// DB에서 사용자 정보 확인
//			GEA0620 rtnData = dao.selectOne("GEA.GEA0620", dbParams);
//			
//			if (null == rtnData) {
//				// 결과가 없는 경우 신규 가입
//				rtnData = new GEA0620();
//				
//				
//				// 신규 사용자 여부
//				rtnData.setNewUserYn("Y");
//			} else {
//				// SNS 토큰 값 확인
//				if (false == params.getSnsTokn().equals(rtnData.getSnsTokn())) {
//					throw new BizException("ECOM018"); //기 가입된 사용자와 SNS인증정보가 일치하지 않습니다
//				}
//				
//				// 신규 사용자 여부
//				rtnData.setNewUserYn("N");
//				
//				String[] roles = new String[] {ROLE.일반사용자};
//				
//				List<GrantedAuthority> grantedAuths = new ArrayList<>();
//				
//				grantedAuths.add(new SimpleGrantedAuthority(ROLE.일반사용자));
//
//				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(params.getUserId(), params.getClientId(), grantedAuths);
//				SecurityContextHolder.getContext().setAuthentication(this.authenticationProvider.authenticate(authToken));
//				
//				// token 발행
//				String token = jwtTokenProvider.issueToken(params.getUserId(), params.getClientId(), roles);
//		
//				rtnData.setAccToken(token);
//			}
//			
//			rtnMsg.setRtnData(rtnData);
//			
//			// FCM token 저장
//			this.authService.regFcmTokn(dbParams);
//			
//		} catch (UsernameNotFoundException e) {
//			
//			throw new BizException("ECOM007"); // 사용자ID 또는 비밀번호 오류
//		} catch (AuthenticationException e) {
//			
//			throw new SysException(e, "ECOM008", null); // 로그인 처리 중 오류
//		} catch (BaseException e) {
//			throw e;
//		}
//				
//		log.debug("session user id {}", GEUtil.getSessionUserId());
//		
//		return rtnMsg;
//	}

//	@PostMapping("/join")
//	@ApiOperation(value = "[" + API_DIV.인증 + "-0200] 회원가입 완료 <Z완료>", notes = "[회원가입] <회원가입 완료> 버튼 https://zpl.io/blEKQOv")
//	public RtnMsg<Void> join(@RequestBody GEA0210 params) throws BaseException {
//
//		Map<String, Object> dbParam = om.convertValue(params, Map.class);
//		
//		// ID 중복 검사 ECOM013
//		Map<String, Object> result = dao.selectOne("USER_BASE.select", dbParam);
//		
//		if (null != result) {
//			throw new BizException("ECOM013"); //이미 동일한 ID가 있습니다.
//		}
//		
//		// ID에 영문 숫자만 가능
//		if (false == GEUtil.checkId(params.getUserId())) {
//			throw new BizException("ECOM024"); //사용자ID는 영문 또는 숫자만 사용 가능합니다
//		}
//		
//		// 패스워드 규칙 검사
//		if (false == GEUtil.checkPass(params.getPass())) {
//			throw new BizException("ECOM011"); //패스워드 규칙이 맞지 않습니다.
//		}
//		//가입일시
//		dbParam.put("joinDttm", GEUtil.getCurrentTimestamp());
//		// 패스워드 암호화
//		dbParam.put("pass", SHAEncUtil.getSHA512(params.getPass()));
//		// 등록자 수정자
//		dbParam.put(GEConstant.COL_REG_USER_ID, params.getUserId());
//		dbParam.put(GEConstant.COL_UPT_USER_ID, params.getUserId());
//		
//		// DB insert
//		this.authService.regUser(dbParam);
//		
//		RtnMsg<Void> rtnMsg = new RtnMsg<>();
//		
//		return rtnMsg;
//	}
	

	@GetMapping("/dupchkUserId")
	@ApiOperation(value = "[" + API_DIV.인증 + "-0300] 회원아이디 중복체크 <Z완료>", notes = "[회원가입] <회원아이디> 중복체크 https://zpl.io/2Gv619j")
	public RtnMsg<Void> dupchkUserId(@RequestParam(name = "userId", required = true) String userId) throws BaseException {
		
		Map<String, Object> dbParam = new HashMap<String, Object>();
		
		dbParam.put("userId", userId);
		
		// ID 중복 검사 ECOM013
		Map<String, Object> result = dao.selectOne("USER_BASE.select", dbParam);
		
		if (null != result) {
			throw new BizException("ECOM013"); //이미 동일한 ID가 있습니다.
		}
		
		RtnMsg<Void> rtnMsg = new RtnMsg<>();
		
		return rtnMsg;
	}
	
//	@PostMapping("/findUserId")
//	@ApiOperation(value = "[" + API_DIV.인증 + "-0700] 아이디 찾기 <개발중>", notes = "[마이페이지] 아이디 찾기")
//	public RtnMsg<GEA0720> findUserId(@Valid @RequestBody GEA0710 params) throws BaseException {
//		
//		RtnMsg<GEA0720> rtnMsg = new RtnMsg<>();
//		
//		GEA0720 rtnData = this.dao.selectOne("GEA.GEA0720", params);
//		
//		// 결과가 없는 경우
//		if (null == rtnData) {
//			throw new BizException("ECOM021"); // 가입된 정보가 없습니다
//		}
//		
//		rtnMsg.setRtnData(rtnData);
//		
//		return rtnMsg;
//	}
	
//	@PostMapping("/joinSns")
//	@ApiOperation(value = "[" + API_DIV.인증 +"-1100] SNS 신규 가입 <개발중>", notes = "[회원가입] SNS회원 가입")
//	public RtnMsg<GEA0620> joinSns(@Valid @RequestBody GEA1110 params) throws BaseException {
//		
//		RtnMsg<GEA0620> rtnMsg = new RtnMsg<>();
//
//		try {
//		
//			Map<String, Object> dbParams = om.convertValue(params, Map.class);
//			
//			GEA0620 rtnData = null;
//			
//			// 결과가 없는 경우 신규 가입
//			//가입일시
//			dbParams.put("joinDttm", GEUtil.getCurrentTimestamp());
//			// 등록자 수정자
//			dbParams.put(GEConstant.COL_REG_USER_ID, params.getUserId());
//			dbParams.put(GEConstant.COL_UPT_USER_ID, params.getUserId());
//			
//			// email 주소가 없는 경우 대체
//			if (false == dbParams.containsKey("emal") || null == dbParams.get("emal")) {
//				dbParams.put("emal", params.getUserId());
//			}
//			
//			// DB insert
//			this.authService.regUser(dbParams);
//			
//			// 다시 조회
//			rtnData = dao.selectOne("GEA.GEA0620", dbParams);
//			
//			String[] roles = new String[] {ROLE.일반사용자};
//			
//			List<GrantedAuthority> grantedAuths = new ArrayList<>();
//			
//			grantedAuths.add(new SimpleGrantedAuthority(ROLE.일반사용자));
//
//			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(params.getUserId(), params.getClientId(), grantedAuths);
//			SecurityContextHolder.getContext().setAuthentication(this.authenticationProvider.authenticate(authToken));
//			
//			// token 발행
//			String token = jwtTokenProvider.issueToken(params.getUserId(), params.getClientId(), roles);
//	
//			rtnData.setAccToken(token);
//			
//			rtnMsg.setRtnData(rtnData);
//			
//		} catch (UsernameNotFoundException e) {
//			
//			throw new BizException("ECOM007"); // 사용자ID 또는 비밀번호 오류
//		} catch (AuthenticationException e) {
//			
//			throw new SysException(e, "ECOM008", null); // 로그인 처리 중 오류
//		} catch (BaseException e) {
//			throw e;
//		}
//				
//		log.debug("session user id {}", GEUtil.getSessionUserId());
//		
//		return rtnMsg;
//	}
}
