package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ts_cs_info Value Object
 */
public class TsCsInfoVo implements Serializable {

	/* reg_no 등록_번호 numeric(null) */
	private double regNo;

	/* cs_tel_no 고객지원_전화_번호 character varying(20) */
	private String csTelNo;

	/* cs_mail_addr 고객지원_메일_주소 character varying(100) */
	private String csMailAddr;

	/* cs_url 고객지원_URL character varying(200) */
	private String csUrl;

	/* band_hpge_url 밴드_홈페이지_URL character varying(200) */
	private String bandHpgeUrl;

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
	 * Get reg_no 등록_번호 numeric(null)
	 * @Return double regNo
	 */
	public double getRegNo() {
		return this.regNo;
	}
	
	/**
	 * Set reg_no 등록_번호 numeric(null)
	 * @Param double regNo
	 */
	public void setRegNo(double regNo) {
		this.regNo = regNo;
	}
	/**
	 * Get cs_tel_no 고객지원_전화_번호 character varying(20)
	 * @Return String csTelNo
	 */
	public String getCsTelNo() {
		return this.csTelNo;
	}
	
	/**
	 * Set cs_tel_no 고객지원_전화_번호 character varying(20)
	 * @Param String csTelNo
	 */
	public void setCsTelNo(String csTelNo) {
		this.csTelNo = csTelNo;
	}
	/**
	 * Get cs_mail_addr 고객지원_메일_주소 character varying(100)
	 * @Return String csMailAddr
	 */
	public String getCsMailAddr() {
		return this.csMailAddr;
	}
	
	/**
	 * Set cs_mail_addr 고객지원_메일_주소 character varying(100)
	 * @Param String csMailAddr
	 */
	public void setCsMailAddr(String csMailAddr) {
		this.csMailAddr = csMailAddr;
	}
	/**
	 * Get cs_url 고객지원_URL character varying(200)
	 * @Return String csUrl
	 */
	public String getCsUrl() {
		return this.csUrl;
	}
	
	/**
	 * Set cs_url 고객지원_URL character varying(200)
	 * @Param String csUrl
	 */
	public void setCsUrl(String csUrl) {
		this.csUrl = csUrl;
	}
	/**
	 * Get band_hpge_url 밴드_홈페이지_URL character varying(200)
	 * @Return String bandHpgeUrl
	 */
	public String getBandHpgeUrl() {
		return this.bandHpgeUrl;
	}
	
	/**
	 * Set band_hpge_url 밴드_홈페이지_URL character varying(200)
	 * @Param String bandHpgeUrl
	 */
	public void setBandHpgeUrl(String bandHpgeUrl) {
		this.bandHpgeUrl = bandHpgeUrl;
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
