package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ti_term_base Value Object
 */
public class TiTermBaseVo implements Serializable {

	/* term_div_cd 약관_구분_코드 character varying(20) */
	private String termDivCd;

	/* term_ver 약관_버전 character varying(20) */
	private String termVer;

	/* aply_strt_dt 적용_시작_일자 character(8) */
	private String aplyStrtDt;

	/* term_cntn 약관_내용 text(null) */
	private String termCntn;

	/* essn_yn 필수_여부 character(1) */
	private String essnYn;

	/* sort_ord 정렬_순서 numeric(null) */
	private double sortOrd;

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
	 * Get term_ver 약관_버전 character varying(20)
	 * @Return String termVer
	 */
	public String getTermVer() {
		return this.termVer;
	}
	
	/**
	 * Set term_ver 약관_버전 character varying(20)
	 * @Param String termVer
	 */
	public void setTermVer(String termVer) {
		this.termVer = termVer;
	}
	/**
	 * Get aply_strt_dt 적용_시작_일자 character(8)
	 * @Return String aplyStrtDt
	 */
	public String getAplyStrtDt() {
		return this.aplyStrtDt;
	}
	
	/**
	 * Set aply_strt_dt 적용_시작_일자 character(8)
	 * @Param String aplyStrtDt
	 */
	public void setAplyStrtDt(String aplyStrtDt) {
		this.aplyStrtDt = aplyStrtDt;
	}
	/**
	 * Get term_cntn 약관_내용 text(null)
	 * @Return String termCntn
	 */
	public String getTermCntn() {
		return this.termCntn;
	}
	
	/**
	 * Set term_cntn 약관_내용 text(null)
	 * @Param String termCntn
	 */
	public void setTermCntn(String termCntn) {
		this.termCntn = termCntn;
	}
	/**
	 * Get essn_yn 필수_여부 character(1)
	 * @Return String essnYn
	 */
	public String getEssnYn() {
		return this.essnYn;
	}
	
	/**
	 * Set essn_yn 필수_여부 character(1)
	 * @Param String essnYn
	 */
	public void setEssnYn(String essnYn) {
		this.essnYn = essnYn;
	}
	/**
	 * Get sort_ord 정렬_순서 numeric(null)
	 * @Return double sortOrd
	 */
	public double getSortOrd() {
		return this.sortOrd;
	}
	
	/**
	 * Set sort_ord 정렬_순서 numeric(null)
	 * @Param double sortOrd
	 */
	public void setSortOrd(double sortOrd) {
		this.sortOrd = sortOrd;
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
