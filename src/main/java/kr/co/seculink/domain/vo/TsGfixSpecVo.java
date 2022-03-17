package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ts_gfix_spec Value Object
 */
public class TsGfixSpecVo implements Serializable {

	/* stdt_no 학생_번호 numeric(null) */
	private double stdtNo;

	/* gfix_dt 성장비만지수_일자 character(8) */
	private String gfixDt;

	/* growfat_div_cd 성장비만_구분_코드 character varying(20) */
	private String growfatDivCd;

	/* nutr_cd 영양소_코드 character varying(20) */
	private String nutrCd;

	/* mesu_val_1 측정_값_1 numeric(null) */
	private double mesuVal1;

	/* mesu_val_2 측정_값_2 numeric(null) */
	private double mesuVal2;

	/* mesu_val_3 측정_값_3 numeric(null) */
	private double mesuVal3;

	/* 판단_근거_cntn 판단_근거_내용 character varying(4000) */
	private String 판단근거Cntn;

	/* certihigh_cntn 권고_사항 character varying(4000) */
	private String certihighCntn;

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
	 * Get growfat_div_cd 성장비만_구분_코드 character varying(20)
	 * @Return String growfatDivCd
	 */
	public String getGrowfatDivCd() {
		return this.growfatDivCd;
	}
	
	/**
	 * Set growfat_div_cd 성장비만_구분_코드 character varying(20)
	 * @Param String growfatDivCd
	 */
	public void setGrowfatDivCd(String growfatDivCd) {
		this.growfatDivCd = growfatDivCd;
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
	 * Get mesu_val_1 측정_값_1 numeric(null)
	 * @Return double mesuVal1
	 */
	public double getMesuVal1() {
		return this.mesuVal1;
	}
	
	/**
	 * Set mesu_val_1 측정_값_1 numeric(null)
	 * @Param double mesuVal1
	 */
	public void setMesuVal1(double mesuVal1) {
		this.mesuVal1 = mesuVal1;
	}
	/**
	 * Get mesu_val_2 측정_값_2 numeric(null)
	 * @Return double mesuVal2
	 */
	public double getMesuVal2() {
		return this.mesuVal2;
	}
	
	/**
	 * Set mesu_val_2 측정_값_2 numeric(null)
	 * @Param double mesuVal2
	 */
	public void setMesuVal2(double mesuVal2) {
		this.mesuVal2 = mesuVal2;
	}
	/**
	 * Get mesu_val_3 측정_값_3 numeric(null)
	 * @Return double mesuVal3
	 */
	public double getMesuVal3() {
		return this.mesuVal3;
	}
	
	/**
	 * Set mesu_val_3 측정_값_3 numeric(null)
	 * @Param double mesuVal3
	 */
	public void setMesuVal3(double mesuVal3) {
		this.mesuVal3 = mesuVal3;
	}
	/**
	 * Get 판단_근거_cntn 판단_근거_내용 character varying(4000)
	 * @Return String 판단근거Cntn
	 */
	public String get판단근거Cntn() {
		return this.판단근거Cntn;
	}
	
	/**
	 * Set 판단_근거_cntn 판단_근거_내용 character varying(4000)
	 * @Param String 판단근거Cntn
	 */
	public void set판단근거Cntn(String 판단근거Cntn) {
		this.판단근거Cntn = 판단근거Cntn;
	}
	/**
	 * Get certihigh_cntn 권고_사항 character varying(4000)
	 * @Return String certihighCntn
	 */
	public String getCertihighCntn() {
		return this.certihighCntn;
	}
	
	/**
	 * Set certihigh_cntn 권고_사항 character varying(4000)
	 * @Param String certihighCntn
	 */
	public void setCertihighCntn(String certihighCntn) {
		this.certihighCntn = certihighCntn;
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
