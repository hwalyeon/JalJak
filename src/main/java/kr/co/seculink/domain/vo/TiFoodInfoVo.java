package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ti_food_info Value Object
 */
public class TiFoodInfoVo implements Serializable {

	/* food_no 식품_번호 numeric(null) */
	private double foodNo;

	/* food_lcls_nm 식품_대분류_명 character varying(100) */
	private String foodLclsNm;

	/* food_mcls_nm 식품_중분류_명 character varying(100) */
	private String foodMclsNm;

	/* food_nm 식품_명 character varying(100) */
	private String foodNm;

	/* otim_eat_qty 1회_섭취_용량 numeric(null) */
	private double otimEatQty;

	/* eat_unit_cd 섭취_단위_코드 character varying(20) */
	private String eatUnitCd;

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
	 * Get food_no 식품_번호 numeric(null)
	 * @Return double foodNo
	 */
	public double getFoodNo() {
		return this.foodNo;
	}
	
	/**
	 * Set food_no 식품_번호 numeric(null)
	 * @Param double foodNo
	 */
	public void setFoodNo(double foodNo) {
		this.foodNo = foodNo;
	}
	/**
	 * Get food_lcls_nm 식품_대분류_명 character varying(100)
	 * @Return String foodLclsNm
	 */
	public String getFoodLclsNm() {
		return this.foodLclsNm;
	}
	
	/**
	 * Set food_lcls_nm 식품_대분류_명 character varying(100)
	 * @Param String foodLclsNm
	 */
	public void setFoodLclsNm(String foodLclsNm) {
		this.foodLclsNm = foodLclsNm;
	}
	/**
	 * Get food_mcls_nm 식품_중분류_명 character varying(100)
	 * @Return String foodMclsNm
	 */
	public String getFoodMclsNm() {
		return this.foodMclsNm;
	}
	
	/**
	 * Set food_mcls_nm 식품_중분류_명 character varying(100)
	 * @Param String foodMclsNm
	 */
	public void setFoodMclsNm(String foodMclsNm) {
		this.foodMclsNm = foodMclsNm;
	}
	/**
	 * Get food_nm 식품_명 character varying(100)
	 * @Return String foodNm
	 */
	public String getFoodNm() {
		return this.foodNm;
	}
	
	/**
	 * Set food_nm 식품_명 character varying(100)
	 * @Param String foodNm
	 */
	public void setFoodNm(String foodNm) {
		this.foodNm = foodNm;
	}
	/**
	 * Get otim_eat_qty 1회_섭취_용량 numeric(null)
	 * @Return double otimEatQty
	 */
	public double getOtimEatQty() {
		return this.otimEatQty;
	}
	
	/**
	 * Set otim_eat_qty 1회_섭취_용량 numeric(null)
	 * @Param double otimEatQty
	 */
	public void setOtimEatQty(double otimEatQty) {
		this.otimEatQty = otimEatQty;
	}
	/**
	 * Get eat_unit_cd 섭취_단위_코드 character varying(20)
	 * @Return String eatUnitCd
	 */
	public String getEatUnitCd() {
		return this.eatUnitCd;
	}
	
	/**
	 * Set eat_unit_cd 섭취_단위_코드 character varying(20)
	 * @Param String eatUnitCd
	 */
	public void setEatUnitCd(String eatUnitCd) {
		this.eatUnitCd = eatUnitCd;
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
