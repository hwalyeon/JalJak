package kr.co.seculink.domain;

public class GEConstant {

	public static class API_DIV {
		
		public static final String 인증 = "GEA";
		public static final String 마이페이지 = "GE1";
		public static final String AS = "GE2";
		public static final String COMMON = "GEC";
		public static final String ADMIN = "ADMIN";
		public static final String HOME = "GEH";
		public static final String A스토리 = "GE3";
		public static final String AR = "GAR";
		public static final String 크롤링 = "GE4";
	}
	
	public static class RTN_CD {
		
		public static final String 정상 = "00";
		public static final String 시스템오류 = "99";
		public static final String 파라메터오류 = "91";
		public static final String JSON오류 = "92";
		public static final String METHOD오류 = "93";
	}
	
	public static class ROLE {
		
		public static final String 일반사용자_시큐리티 = "USER";
		public static final String 일반사용자 = "ROLE_" + 일반사용자_시큐리티;
		public static final String 어드민_시큐리티 = "ADMIN";
		public static final String 어드민 = "ROLE_" + 어드민_시큐리티;
		public static final String 시스템_시큐리티 = "SYSTEM";
		public static final String 시스템 = "ROLE_" + 시스템_시큐리티;
		
	}
	
	public static class FILE_TYPE {
		public static final String 사용자_사진 = "userPhot";
		public static final String 마이전자_영수증 = "myPrdtBill";
		public static final String 문의_사진 = "qPhot";
		public static final String AS_사진 = "asPhot";
	}
	
	public static final String COL_REG_USER_ID = "regUserId";
	public static final String COL_UPT_USER_ID = "uptUserId";
	public static final String BEAN_TRX_MANAGER_NAME = "transactionManager";
	public static final String USER_ANONYMOUS = "anonymous";
	public static final String USER_BATCH = "system-batch";
	public static final String IS_BATCH_KEY = "_IS_BATCH_KEY_";
}
