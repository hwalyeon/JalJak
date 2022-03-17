package kr.co.seculink.util;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import kr.co.seculink.web.model.cmon.SessionVO;
import kr.co.seculink.exception.SysException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GEUtil {

	/**
	 * SessionVo를 세션에서 취득 없는경우 null
	 * @return
	 */
	public static SessionVO getSessionVo() {
		
		SessionVO vo = null;
		
		try {
		
			vo = (SessionVO) SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		} catch (Exception e) {
			
		}
		
		return vo;
	}
	
	/**
	 * 세션에서 로그인된 userId를 취득 없는 경우 "" Batch인 경우 "SYSTEM"
	 * @return
	 */
	public static String getSessionUserId() {
		
		SessionVO vo = getSessionVo();
		
		if (null != vo) {
			return vo.getUserId();
		} else {
			return "";
		}
	}
	
	public static List<String> getRoleList() {
		
		List<String> rsltList = new ArrayList<>();
		
		SessionVO vo = getSessionVo();
		
		if (null != vo) {
			
			List<SimpleGrantedAuthority> roleList = vo.getRoleList();

			for ( SimpleGrantedAuthority role : roleList ) {
				rsltList.add(role.getAuthority());
			}
		}
		
		return rsltList;
	}
	
	/**
	 * 세션에서 로그인된 역할의 여부를 리턴
	 * */
	public static boolean hasRole(String roleCd) {
		
		SessionVO vo = getSessionVo();
		
		if (null != vo) {
			
			List<SimpleGrantedAuthority> roleList = vo.getRoleList();

			for ( SimpleGrantedAuthority role : roleList ) {

				if ( role.getAuthority().equals(roleCd) || "ADMIN".equals(role.getAuthority()) ) {
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * 세션에서 로그인된 역할의 어드민 여부를 리턴
	 * */
	public static boolean isAdmin() {
		
		SessionVO vo = getSessionVo();
		
		if (null != vo) {
			
			List<SimpleGrantedAuthority> roleList = vo.getRoleList();

			for ( SimpleGrantedAuthority role : roleList ) {

				if ( "ADMIN".equals(role.getAuthority()) ) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * request 취득
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		
		if (null == sra) {
			return null;
		}
		
		return sra.getRequest();
	}
	
	/**
	 * response 취득
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		
		if (null == sra) {
			return null;
		}
		
		return sra.getResponse();
	}
	
	/**
	 * Session 취득
	 * @param isCreate 세션이 없는 경우 생성 여부
	 * @return
	 */
	public static HttpSession getSession(boolean isCreate) {
		
		HttpServletRequest request = getRequest();
		
		if (null != request) {
			return request.getSession(isCreate);
		} else {
			return null;
		}
	}
	
	/**
	 * 세션 취득
	 * @return
	 */
	public static HttpSession getSession() {
		return getSession(false);
	}
	
	static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	
	/**
	 * 현재 일시 취득
	 * @return
	 */
	public static String getCurrentTimestamp() {
		
		LocalDateTime now = LocalDateTime.now();
		
		return DTF.format(now);
	}
	
	static DateTimeFormatter DTF_YEAR_MONTH = DateTimeFormatter.ofPattern("yyyyMM");
	
	/**
	 * 현재 년월 취득
	 * @return
	 */
	public static String getCurrentYearMonth() {
		
		LocalDateTime now = LocalDateTime.now();
		
		return DTF_YEAR_MONTH.format(now);
	}
	
	
	/**
	 * 현재 일시 취득 (포맷 지정)
	 * @param format
	 * @return
	 */
	public static String getCurrentTimestamp(String format) {
		
		LocalDateTime now = LocalDateTime.now();
		
		DateTimeFormatter DTF = DateTimeFormatter.ofPattern(format);
		
		return DTF.format(now);
	}
	
	/**
	 * str이 null 또는 "" 이 replaceStr로 변환 
	 * @param str
	 * @param replaceStr
	 * @return
	 */
	public static String emptyToString(String str, String replaceStr) {
		String rtnStr = str;
		
		if (XUtil.isEmpty(str)) {
			rtnStr = replaceStr;
		}
		
		return rtnStr;
	}
	
	/**
	 * str이 null 이면 ""로 변환
	 * @param str
	 * @return
	 */
	public static String emptyToString(String str) {
		return emptyToString(str, "");
	}
	
	public static boolean antmachers(String url, String... patterns) {
		
		AntPathMatcher pathMatcher = new AntPathMatcher();
		
		for (String pattern : patterns) {
			if (pathMatcher.match(pattern, url)) {
				
				log.trace("match!!! {} / {}", url, pattern);
				return true;
			} else {
				log.trace("not match!!! {} / {}", url, pattern);
			}
		}
		
		return false;
	}
	
	/**
	 * Password 규칙 검사
	 * @param pass
	 * @return
	 */
	public static boolean checkPass(String pass) {
		pass = emptyToString(pass);
		
		//8~20자리
		if (pass.length() < 8 || pass.length() > 20) {
			return false;
		}
		
		// 영문 포함
		if (false == pass.matches(".*[a-z|A-Z]+.*")) {
			return false;
		}
		
		// 숫자 포함
		if (false == pass.matches(".*[0-9]+.*")) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * ID 규칙 검사(직접 가입)
	 * @param pass
	 * @return
	 */
	public static boolean checkId(String id) {
		
		char chrInput;

		for (int i = 0, _max = id.length() ; i < _max ; i++) {

			chrInput = id.charAt(i); // 입력받은 텍스트에서 문자 하나하나 가져와서 체크

			if (chrInput >= 0x61 && chrInput <= 0x7A) {
				// 영문(소문자) OK!
			} else if (chrInput >=0x41 && chrInput <= 0x5A) {
				// 영문(대문자) OK!
			} else if (chrInput >= 0x30 && chrInput <= 0x39) {
				// 숫자 OK!
			} else {
				return false;   // 영문자도 아니고 숫자도 아님!
			}

		}

		return true;
	}
	
    /**
     * 입력된 문자열을 초성으로 변경
     * @param name
     * @return
     */
    public static String convertRtName( String name ) {

    	String rtName = "";
    	char epName;

    	try {

    		for (int i=0; i<name.length(); i++){

    			epName = name.charAt(i);
    			rtName = rtName + Direct(epName);

    		}

    	} catch (Exception e) {

			log.error("convertName err ", e);
		}

    	return rtName;
    }

    /**
     * 초성 추출
     * @param b
     * @return
     */
    public static String Direct(char b) {

           String chosung = null;

           int first = (b - 44032 ) / ( 21 * 28 );

            switch(first) {
                case 0:
                	chosung="ㄱ";
                    break;
                case 1:
                    chosung="ㄲ";
                    break;
                case 2:
                    chosung="ㄴ";
                    break;
                case 3:
                	chosung="ㄷ";
                    break;
                case 4:
                    chosung="ㄸ";
                    break;
                case 5:
                    chosung="ㄹ";
                    break;
                case 6:
                    chosung="ㅁ";
                    break;
                case 7:
                	chosung="ㅂ";
                	break;
                case 8:
                    chosung="ㅃ";
                    break;
                case 9:
                	chosung="ㅅ";
                    break;
                case 10:
                    chosung="ㅆ";
                    break;
                case 11:
                    chosung="ㅇ";
                    break;
                case 12:
                	chosung="ㅈ";
                    break;
                case 13:
                    chosung="ㅉ";
                    break;
                case 14:
                    chosung="ㅊ";
                    break;
                case 15:
                    chosung="ㅋ";
                    break;
                case 16:
                    chosung="ㅌ";
                    break;
                case 17:
                    chosung="ㅍ";
                    break;
                case 18:
                    chosung="ㅎ";
                    break;
                default: chosung=String.valueOf(b);
            }     
         return chosung;
    }
    
    /**
     * 임시 파일을 생성한다.
     * @return
     * @throws SysException
     */
    public static File getTempFile() throws SysException {
    	
    	try {
			return File.createTempFile("GEFILE", "GEFILE");
		} catch (IOException e) {
			throw new SysException(e, "ECOM009", null);
		}
    }

    
    public static boolean isEmpty(String str) {
    	if ( "".equals(emptyToString(str)) ) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
