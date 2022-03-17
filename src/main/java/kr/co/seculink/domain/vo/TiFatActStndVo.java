package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ti_fat_act_stnd Value Object
 */
public class TiFatActStndVo implements Serializable {

	/* curr_fat_judg_cd 현재_비만_판정_코드 character varying(20) */
	private String currFatJudgCd;

	/* prdt_fat_judg_cd 예측_비만_판정_코드 character varying(20) */
	private String prdtFatJudgCd;

	/* pal_cd 신체활동수준_코드 character varying(20) */
	private String palCd;

	/* fat_act_rmrk 비만_활동_비고 text(null) */
	private String fatActRmrk;

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
	 * Get curr_fat_judg_cd 현재_비만_판정_코드 character varying(20)
	 * @Return String currFatJudgCd
	 */
	public String getCurrFatJudgCd() {
		return this.currFatJudgCd;
	}
	
	/**
	 * Set curr_fat_judg_cd 현재_비만_판정_코드 character varying(20)
	 * @Param String currFatJudgCd
	 */
	public void setCurrFatJudgCd(String currFatJudgCd) {
		this.currFatJudgCd = currFatJudgCd;
	}
	/**
	 * Get prdt_fat_judg_cd 예측_비만_판정_코드 character varying(20)
	 * @Return String prdtFatJudgCd
	 */
	public String getPrdtFatJudgCd() {
		return this.prdtFatJudgCd;
	}
	
	/**
	 * Set prdt_fat_judg_cd 예측_비만_판정_코드 character varying(20)
	 * @Param String prdtFatJudgCd
	 */
	public void setPrdtFatJudgCd(String prdtFatJudgCd) {
		this.prdtFatJudgCd = prdtFatJudgCd;
	}
	/**
	 * Get pal_cd 신체활동수준_코드 character varying(20)
	 * @Return String palCd
	 */
	public String getPalCd() {
		return this.palCd;
	}
	
	/**
	 * Set pal_cd 신체활동수준_코드 character varying(20)
	 * @Param String palCd
	 */
	public void setPalCd(String palCd) {
		this.palCd = palCd;
	}
	/**
	 * Get fat_act_rmrk 비만_활동_비고 text(null)
	 * @Return String fatActRmrk
	 */
	public String getFatActRmrk() {
		return this.fatActRmrk;
	}
	
	/**
	 * Set fat_act_rmrk 비만_활동_비고 text(null)
	 * @Param String fatActRmrk
	 */
	public void setFatActRmrk(String fatActRmrk) {
		this.fatActRmrk = fatActRmrk;
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
