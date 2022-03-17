package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ts_fmenu_spec Value Object
 */
public class TsFmenuSpecVo implements Serializable {

	/* guar_no 보호자_번호 numeric(null) */
	private double guarNo;

	/* fmenu_seq 식단표_순번 numeric(null) */
	private double fmenuSeq;

	/* fmenu_spec_seq 식단표_상세_순번 numeric(null) */
	private double fmenuSpecSeq;

	/* food_no 식품_번호 character varying(20) */
	private String foodNo;

	/* qty 수량 numeric(null) */
	private double qty;

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
	 * Get fmenu_seq 식단표_순번 numeric(null)
	 * @Return double fmenuSeq
	 */
	public double getFmenuSeq() {
		return this.fmenuSeq;
	}
	
	/**
	 * Set fmenu_seq 식단표_순번 numeric(null)
	 * @Param double fmenuSeq
	 */
	public void setFmenuSeq(double fmenuSeq) {
		this.fmenuSeq = fmenuSeq;
	}
	/**
	 * Get fmenu_spec_seq 식단표_상세_순번 numeric(null)
	 * @Return double fmenuSpecSeq
	 */
	public double getFmenuSpecSeq() {
		return this.fmenuSpecSeq;
	}
	
	/**
	 * Set fmenu_spec_seq 식단표_상세_순번 numeric(null)
	 * @Param double fmenuSpecSeq
	 */
	public void setFmenuSpecSeq(double fmenuSpecSeq) {
		this.fmenuSpecSeq = fmenuSpecSeq;
	}
	/**
	 * Get food_no 식품_번호 character varying(20)
	 * @Return String foodNo
	 */
	public String getFoodNo() {
		return this.foodNo;
	}
	
	/**
	 * Set food_no 식품_번호 character varying(20)
	 * @Param String foodNo
	 */
	public void setFoodNo(String foodNo) {
		this.foodNo = foodNo;
	}
	/**
	 * Get qty 수량 numeric(null)
	 * @Return double qty
	 */
	public double getQty() {
		return this.qty;
	}
	
	/**
	 * Set qty 수량 numeric(null)
	 * @Param double qty
	 */
	public void setQty(double qty) {
		this.qty = qty;
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
