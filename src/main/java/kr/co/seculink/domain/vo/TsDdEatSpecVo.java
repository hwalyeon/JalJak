package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ts_dd_eat_spec Value Object
 */
public class TsDdEatSpecVo implements Serializable {

	/* stdt_no 학생_번호 numeric(null) */
	private double stdtNo;

	/* gfix_dt 성장비만지수_일자 character(8) */
	private String gfixDt;

	/* nutr_cd 영양소_코드 character varying(20) */
	private String nutrCd;

	/* avg_eat_qty 평균_섭취_량 numeric(null) */
	private double avgEatQty;

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
	 * Get stdt_no 학생_번호 numeric(null)
	 * @Return double stdtNo
	 */
	public double getStdtNo() {
		return this.stdtNo;
	}
	
	/**
	 * Set stdt_no 학생_번호 numeric(null)
	 * @Param double stdtNo
	 */
	public void setStdtNo(double stdtNo) {
		this.stdtNo = stdtNo;
	}
	/**
	 * Get gfix_dt 성장비만지수_일자 character(8)
	 * @Return String gfixDt
	 */
	public String getGfixDt() {
		return this.gfixDt;
	}
	
	/**
	 * Set gfix_dt 성장비만지수_일자 character(8)
	 * @Param String gfixDt
	 */
	public void setGfixDt(String gfixDt) {
		this.gfixDt = gfixDt;
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
	 * Get avg_eat_qty 평균_섭취_량 numeric(null)
	 * @Return double avgEatQty
	 */
	public double getAvgEatQty() {
		return this.avgEatQty;
	}
	
	/**
	 * Set avg_eat_qty 평균_섭취_량 numeric(null)
	 * @Param double avgEatQty
	 */
	public void setAvgEatQty(double avgEatQty) {
		this.avgEatQty = avgEatQty;
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
