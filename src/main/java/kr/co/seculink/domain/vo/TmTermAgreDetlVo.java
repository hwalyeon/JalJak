package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * tm_term_agre_detl Value Object
 */
public class TmTermAgreDetlVo implements Serializable {

	/* guar_no 보호자_번호 numeric(null) */
	private double guarNo;

	/* term_div_cd 약관_구분_코드 character varying(20) */
	private String termDivCd;

	/* term_ver 약관_버전 numeric(null) */
	private double termVer;

	/* term_agre_yn 약관_동의_여부 character(1) */
	private String termAgreYn;

	/* term_agre_dttm 약관_동의_일시 character(14) */
	private String termAgreDttm;

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
	 * Get guar_no 보호자_번호 numeric(null)
	 * @Return double guarNo
	 */
	public double getGuarNo() {
		return this.guarNo;
	}
	
	/**
	 * Set guar_no 보호자_번호 numeric(null)
	 * @Param double guarNo
	 */
	public void setGuarNo(double guarNo) {
		this.guarNo = guarNo;
	}
	/**
	 * Get term_div_cd 약관_구분_코드 character varying(20)
	 * @Return String termDivCd
	 */
	public String getTermDivCd() {
		return this.termDivCd;
	}
	
	/**
	 * Set term_div_cd 약관_구분_코드 character varying(20)
	 * @Param String termDivCd
	 */
	public void setTermDivCd(String termDivCd) {
		this.termDivCd = termDivCd;
	}
	/**
	 * Get term_ver 약관_버전 numeric(null)
	 * @Return double termVer
	 */
	public double getTermVer() {
		return this.termVer;
	}
	
	/**
	 * Set term_ver 약관_버전 numeric(null)
	 * @Param double termVer
	 */
	public void setTermVer(double termVer) {
		this.termVer = termVer;
	}
	/**
	 * Get term_agre_yn 약관_동의_여부 character(1)
	 * @Return String termAgreYn
	 */
	public String getTermAgreYn() {
		return this.termAgreYn;
	}
	
	/**
	 * Set term_agre_yn 약관_동의_여부 character(1)
	 * @Param String termAgreYn
	 */
	public void setTermAgreYn(String termAgreYn) {
		this.termAgreYn = termAgreYn;
	}
	/**
	 * Get term_agre_dttm 약관_동의_일시 character(14)
	 * @Return String termAgreDttm
	 */
	public String getTermAgreDttm() {
		return this.termAgreDttm;
	}
	
	/**
	 * Set term_agre_dttm 약관_동의_일시 character(14)
	 * @Param String termAgreDttm
	 */
	public void setTermAgreDttm(String termAgreDttm) {
		this.termAgreDttm = termAgreDttm;
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
