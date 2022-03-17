package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ti_grow_judg_stnd Value Object
 */
public class TiGrowJudgStndVo implements Serializable {

	/* grow_judg_cd 성장_판정_코드 character varying(20) */
	private String growJudgCd;

	/* gidx_fr 성장지수_FROM numeric(null) */
	private double gidxFr;

	/* gidx_to 성장지수_TO numeric(null) */
	private double gidxTo;

	/* smry_cntn 요약_내용 character varying(1000) */
	private String smryCntn;

	/* spec_cntn 상세_내용 character varying(4000) */
	private String specCntn;

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
	 * Get grow_judg_cd 성장_판정_코드 character varying(20)
	 * @Return String growJudgCd
	 */
	public String getGrowJudgCd() {
		return this.growJudgCd;
	}
	
	/**
	 * Set grow_judg_cd 성장_판정_코드 character varying(20)
	 * @Param String growJudgCd
	 */
	public void setGrowJudgCd(String growJudgCd) {
		this.growJudgCd = growJudgCd;
	}
	/**
	 * Get gidx_fr 성장지수_FROM numeric(null)
	 * @Return double gidxFr
	 */
	public double getGidxFr() {
		return this.gidxFr;
	}
	
	/**
	 * Set gidx_fr 성장지수_FROM numeric(null)
	 * @Param double gidxFr
	 */
	public void setGidxFr(double gidxFr) {
		this.gidxFr = gidxFr;
	}
	/**
	 * Get gidx_to 성장지수_TO numeric(null)
	 * @Return double gidxTo
	 */
	public double getGidxTo() {
		return this.gidxTo;
	}
	
	/**
	 * Set gidx_to 성장지수_TO numeric(null)
	 * @Param double gidxTo
	 */
	public void setGidxTo(double gidxTo) {
		this.gidxTo = gidxTo;
	}
	/**
	 * Get smry_cntn 요약_내용 character varying(1000)
	 * @Return String smryCntn
	 */
	public String getSmryCntn() {
		return this.smryCntn;
	}
	
	/**
	 * Set smry_cntn 요약_내용 character varying(1000)
	 * @Param String smryCntn
	 */
	public void setSmryCntn(String smryCntn) {
		this.smryCntn = smryCntn;
	}
	/**
	 * Get spec_cntn 상세_내용 character varying(4000)
	 * @Return String specCntn
	 */
	public String getSpecCntn() {
		return this.specCntn;
	}
	
	/**
	 * Set spec_cntn 상세_내용 character varying(4000)
	 * @Param String specCntn
	 */
	public void setSpecCntn(String specCntn) {
		this.specCntn = specCntn;
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
