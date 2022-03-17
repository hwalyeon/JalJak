package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ti_dd_pal_blck Value Object
 */
public class TiDdPalBlckVo implements Serializable {

	/* curr_fat_judg_cd 현재_비만_판정_코드 character varying(20) */
	private String currFatJudgCd;

	/* prdt_fat_judg_cd 예측_비만_판정_코드 character varying(20) */
	private String prdtFatJudgCd;

	/* sex_cd 성별_코드 character varying(20) */
	private String sexCd;

	/* age_ycnt 나이_년수 numeric(null) */
	private double ageYcnt;

	/* pal_cd 신체활동수준_코드 character varying(20) */
	private String palCd;

	/* cal_qty_fr 칼로리_량_FROM numeric(null) */
	private double calQtyFr;

	/* cal_qty_to 칼로리_량_TO numeric(null) */
	private double calQtyTo;

	/* dd_cal_qty 일일_칼로리_량 numeric(null) */
	private double ddCalQty;

	/* nutr_cd 영양소_코드 character varying(20) */
	private String nutrCd;

	/* nutr_stat_cd 영양섭취_상태_코드 character varying(20) */
	private String nutrStatCd;

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
	 * Get sex_cd 성별_코드 character varying(20)
	 * @Return String sexCd
	 */
	public String getSexCd() {
		return this.sexCd;
	}
	
	/**
	 * Set sex_cd 성별_코드 character varying(20)
	 * @Param String sexCd
	 */
	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}
	/**
	 * Get age_ycnt 나이_년수 numeric(null)
	 * @Return double ageYcnt
	 */
	public double getAgeYcnt() {
		return this.ageYcnt;
	}
	
	/**
	 * Set age_ycnt 나이_년수 numeric(null)
	 * @Param double ageYcnt
	 */
	public void setAgeYcnt(double ageYcnt) {
		this.ageYcnt = ageYcnt;
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
	 * Get cal_qty_fr 칼로리_량_FROM numeric(null)
	 * @Return double calQtyFr
	 */
	public double getCalQtyFr() {
		return this.calQtyFr;
	}
	
	/**
	 * Set cal_qty_fr 칼로리_량_FROM numeric(null)
	 * @Param double calQtyFr
	 */
	public void setCalQtyFr(double calQtyFr) {
		this.calQtyFr = calQtyFr;
	}
	/**
	 * Get cal_qty_to 칼로리_량_TO numeric(null)
	 * @Return double calQtyTo
	 */
	public double getCalQtyTo() {
		return this.calQtyTo;
	}
	
	/**
	 * Set cal_qty_to 칼로리_량_TO numeric(null)
	 * @Param double calQtyTo
	 */
	public void setCalQtyTo(double calQtyTo) {
		this.calQtyTo = calQtyTo;
	}
	/**
	 * Get dd_cal_qty 일일_칼로리_량 numeric(null)
	 * @Return double ddCalQty
	 */
	public double getDdCalQty() {
		return this.ddCalQty;
	}
	
	/**
	 * Set dd_cal_qty 일일_칼로리_량 numeric(null)
	 * @Param double ddCalQty
	 */
	public void setDdCalQty(double ddCalQty) {
		this.ddCalQty = ddCalQty;
	}
	/**
	 * Get nutr_cd 영양소_코드 character varying(20)
	 * @Return String nutrCd
	 */
	public String getNutrCd() {
		return this.nutrCd;
	}
	
	/**
	 * Set nutr_cd 영양소_코드 character varying(20)
	 * @Param String nutrCd
	 */
	public void setNutrCd(String nutrCd) {
		this.nutrCd = nutrCd;
	}
	/**
	 * Get nutr_stat_cd 영양섭취_상태_코드 character varying(20)
	 * @Return String nutrStatCd
	 */
	public String getNutrStatCd() {
		return this.nutrStatCd;
	}
	
	/**
	 * Set nutr_stat_cd 영양섭취_상태_코드 character varying(20)
	 * @Param String nutrStatCd
	 */
	public void setNutrStatCd(String nutrStatCd) {
		this.nutrStatCd = nutrStatCd;
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
