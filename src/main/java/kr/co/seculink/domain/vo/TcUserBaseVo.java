package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * tc_user_base Value Object
 */
public class TcUserBaseVo implements Serializable {

	/* user_id 사용자_ID character varying(40) */
	private String userId;

	/* user_pw 사용자_암호 character varying(100) */
	private String userPw;

	/* user_nm 사용자_명 character varying(40) */
	private String userNm;

	/* blng_nm 소속_명 character varying(100) */
	private String blngNm;

	/* tel_no 전화_번호 character varying(20) */
	private String telNo;

	/* mtel_no 휴대폰_번호 character varying(20) */
	private String mtelNo;

	/* mail_addr 메일_주소 character varying(50) */
	private String mailAddr;

	/* entr_dt 가입_일자 character(8) */
	private String entrDt;

	/* rels_dt 해지_일자 character(8) */
	private String relsDt;

	/* use_yn 사용_여부 character(1) */
	private String useYn;

	/* reg_dt 등록_일자 character(8) */
	private String regDt;

	/* reg_tm 등록_시각 character(6) */
	private String regTm;

	/* reg_user_id 등록_사용자_ID character varying(20) */
	private String regUserId;

	/* upt_dt 수정_일자 character(8) */
	private String uptDt;

	/* upt_tm 수정_시각 character(6) */
	private String uptTm;

	/* upt_user_id 수정_사용자_ID character varying(20) */
	private String uptUserId;


	/**
	 * Get user_id 사용자_ID character varying(40)
	 * @Return String userId
	 */
	public String getUserId() {
		return this.userId;
	}
	
	/**
	 * Set user_id 사용자_ID character varying(40)
	 * @Param String userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * Get user_pw 사용자_암호 character varying(100)
	 * @Return String userPw
	 */
	public String getUserPw() {
		return this.userPw;
	}
	
	/**
	 * Set user_pw 사용자_암호 character varying(100)
	 * @Param String userPw
	 */
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	/**
	 * Get user_nm 사용자_명 character varying(40)
	 * @Return String userNm
	 */
	public String getUserNm() {
		return this.userNm;
	}
	
	/**
	 * Set user_nm 사용자_명 character varying(40)
	 * @Param String userNm
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	/**
	 * Get blng_nm 소속_명 character varying(100)
	 * @Return String blngNm
	 */
	public String getBlngNm() {
		return this.blngNm;
	}
	
	/**
	 * Set blng_nm 소속_명 character varying(100)
	 * @Param String blngNm
	 */
	public void setBlngNm(String blngNm) {
		this.blngNm = blngNm;
	}
	/**
	 * Get tel_no 전화_번호 character varying(20)
	 * @Return String telNo
	 */
	public String getTelNo() {
		return this.telNo;
	}
	
	/**
	 * Set tel_no 전화_번호 character varying(20)
	 * @Param String telNo
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	/**
	 * Get mtel_no 휴대폰_번호 character varying(20)
	 * @Return String mtelNo
	 */
	public String getMtelNo() {
		return this.mtelNo;
	}
	
	/**
	 * Set mtel_no 휴대폰_번호 character varying(20)
	 * @Param String mtelNo
	 */
	public void setMtelNo(String mtelNo) {
		this.mtelNo = mtelNo;
	}
	/**
	 * Get mail_addr 메일_주소 character varying(50)
	 * @Return String mailAddr
	 */
	public String getMailAddr() {
		return this.mailAddr;
	}
	
	/**
	 * Set mail_addr 메일_주소 character varying(50)
	 * @Param String mailAddr
	 */
	public void setMailAddr(String mailAddr) {
		this.mailAddr = mailAddr;
	}
	/**
	 * Get entr_dt 가입_일자 character(8)
	 * @Return String entrDt
	 */
	public String getEntrDt() {
		return this.entrDt;
	}
	
	/**
	 * Set entr_dt 가입_일자 character(8)
	 * @Param String entrDt
	 */
	public void setEntrDt(String entrDt) {
		this.entrDt = entrDt;
	}
	/**
	 * Get rels_dt 해지_일자 character(8)
	 * @Return String relsDt
	 */
	public String getRelsDt() {
		return this.relsDt;
	}
	
	/**
	 * Set rels_dt 해지_일자 character(8)
	 * @Param String relsDt
	 */
	public void setRelsDt(String relsDt) {
		this.relsDt = relsDt;
	}
	/**
	 * Get use_yn 사용_여부 character(1)
	 * @Return String useYn
	 */
	public String getUseYn() {
		return this.useYn;
	}
	
	/**
	 * Set use_yn 사용_여부 character(1)
	 * @Param String useYn
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	/**
	 * Get reg_dt 등록_일자 character(8)
	 * @Return String regDt
	 */
	public String getRegDt() {
		return this.regDt;
	}
	
	/**
	 * Set reg_dt 등록_일자 character(8)
	 * @Param String regDt
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	/**
	 * Get reg_tm 등록_시각 character(6)
	 * @Return String regTm
	 */
	public String getRegTm() {
		return this.regTm;
	}
	
	/**
	 * Set reg_tm 등록_시각 character(6)
	 * @Param String regTm
	 */
	public void setRegTm(String regTm) {
		this.regTm = regTm;
	}
	/**
	 * Get reg_user_id 등록_사용자_ID character varying(20)
	 * @Return String regUserId
	 */
	public String getRegUserId() {
		return this.regUserId;
	}
	
	/**
	 * Set reg_user_id 등록_사용자_ID character varying(20)
	 * @Param String regUserId
	 */
	public void setRegUserId(String regUserId) {
		this.regUserId = regUserId;
	}
	/**
	 * Get upt_dt 수정_일자 character(8)
	 * @Return String uptDt
	 */
	public String getUptDt() {
		return this.uptDt;
	}
	
	/**
	 * Set upt_dt 수정_일자 character(8)
	 * @Param String uptDt
	 */
	public void setUptDt(String uptDt) {
		this.uptDt = uptDt;
	}
	/**
	 * Get upt_tm 수정_시각 character(6)
	 * @Return String uptTm
	 */
	public String getUptTm() {
		return this.uptTm;
	}
	
	/**
	 * Set upt_tm 수정_시각 character(6)
	 * @Param String uptTm
	 */
	public void setUptTm(String uptTm) {
		this.uptTm = uptTm;
	}
	/**
	 * Get upt_user_id 수정_사용자_ID character varying(20)
	 * @Return String uptUserId
	 */
	public String getUptUserId() {
		return this.uptUserId;
	}
	
	/**
	 * Set upt_user_id 수정_사용자_ID character varying(20)
	 * @Param String uptUserId
	 */
	public void setUptUserId(String uptUserId) {
		this.uptUserId = uptUserId;
	}

} // end of class
