package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ti_nutr_info Value Object
 */
public class TiNutrInfoVo implements Serializable {

	/* nutr_cd 영양소_코드 character varying(20) */
	private String nutrCd;

	/* nutr_nm 영양소_명 character varying(40) */
	private String nutrNm;

	/* nutr_unit_cd 영양소_단위_코드 character varying(20) */
	private String nutrUnitCd;

	/* gfix_div_cd 성장비만지수_구분_코드 character varying(20) */
	private String gfixDivCd;

	/* sort_ord 정렬_순서 numeric(null) */
	private double sortOrd;

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
	 * Get nutr_nm 영양소_명 character varying(40)
	 * @Return String nutrNm
	 */
	public String getNutrNm() {
		return this.nutrNm;
	}
	
	/**
	 * Set nutr_nm 영양소_명 character varying(40)
	 * @Param String nutrNm
	 */
	public void setNutrNm(String nutrNm) {
		this.nutrNm = nutrNm;
	}
	/**
	 * Get nutr_unit_cd 영양소_단위_코드 character varying(20)
	 * @Return String nutrUnitCd
	 */
	public String getNutrUnitCd() {
		return this.nutrUnitCd;
	}
	
	/**
	 * Set nutr_unit_cd 영양소_단위_코드 character varying(20)
	 * @Param String nutrUnitCd
	 */
	public void setNutrUnitCd(String nutrUnitCd) {
		this.nutrUnitCd = nutrUnitCd;
	}
	/**
	 * Get gfix_div_cd 성장비만지수_구분_코드 character varying(20)
	 * @Return String gfixDivCd
	 */
	public String getGfixDivCd() {
		return this.gfixDivCd;
	}
	
	/**
	 * Set gfix_div_cd 성장비만지수_구분_코드 character varying(20)
	 * @Param String gfixDivCd
	 */
	public void setGfixDivCd(String gfixDivCd) {
		this.gfixDivCd = gfixDivCd;
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
