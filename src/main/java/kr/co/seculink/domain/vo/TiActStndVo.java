package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ti_act_stnd Value Object
 */
public class TiActStndVo implements Serializable {

	/* act_cd 활동_코드 character varying(20) */
	private String actCd;

	/* act_clss_cd 활동_분류_코드 character varying(20) */
	private String actClssCd;

	/* act_nm 활동_명 character varying(40) */
	private String actNm;

	/* act_desc 활동_설명 character varying(1000) */
	private String actDesc;

	/* met_val MET_값 numeric(null) */
	private double metVal;

	/* met_min_cfct MET_분당_환산계수 numeric(null) */
	private double metMinCfct;

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
	 * Get act_cd 활동_코드 character varying(20)
	 * @Return String actCd
	 */
	public String getActCd() {
		return this.actCd;
	}
	
	/**
	 * Set act_cd 활동_코드 character varying(20)
	 * @Param String actCd
	 */
	public void setActCd(String actCd) {
		this.actCd = actCd;
	}
	/**
	 * Get act_clss_cd 활동_분류_코드 character varying(20)
	 * @Return String actClssCd
	 */
	public String getActClssCd() {
		return this.actClssCd;
	}
	
	/**
	 * Set act_clss_cd 활동_분류_코드 character varying(20)
	 * @Param String actClssCd
	 */
	public void setActClssCd(String actClssCd) {
		this.actClssCd = actClssCd;
	}
	/**
	 * Get act_nm 활동_명 character varying(40)
	 * @Return String actNm
	 */
	public String getActNm() {
		return this.actNm;
	}
	
	/**
	 * Set act_nm 활동_명 character varying(40)
	 * @Param String actNm
	 */
	public void setActNm(String actNm) {
		this.actNm = actNm;
	}
	/**
	 * Get act_desc 활동_설명 character varying(1000)
	 * @Return String actDesc
	 */
	public String getActDesc() {
		return this.actDesc;
	}
	
	/**
	 * Set act_desc 활동_설명 character varying(1000)
	 * @Param String actDesc
	 */
	public void setActDesc(String actDesc) {
		this.actDesc = actDesc;
	}
	/**
	 * Get met_val MET_값 numeric(null)
	 * @Return double metVal
	 */
	public double getMetVal() {
		return this.metVal;
	}
	
	/**
	 * Set met_val MET_값 numeric(null)
	 * @Param double metVal
	 */
	public void setMetVal(double metVal) {
		this.metVal = metVal;
	}
	/**
	 * Get met_min_cfct MET_분당_환산계수 numeric(null)
	 * @Return double metMinCfct
	 */
	public double getMetMinCfct() {
		return this.metMinCfct;
	}
	
	/**
	 * Set met_min_cfct MET_분당_환산계수 numeric(null)
	 * @Param double metMinCfct
	 */
	public void setMetMinCfct(double metMinCfct) {
		this.metMinCfct = metMinCfct;
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
