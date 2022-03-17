package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ts_alam_detl Value Object
 */
public class TsAlamDetlVo implements Serializable {

	/* alam_no 알림_번호 numeric(null) */
	private double alamNo;

	/* alam_chnl_cd 알림_채널_코드 character varying(20) */
	private String alamChnlCd;

	/* alam_type_cd 알림_유형_코드 character varying(20) */
	private String alamTypeCd;

	/* alam_titl 알림_제목 character varying(200) */
	private String alamTitl;

	/* alam_cntn 알림_내용 text(null) */
	private String alamCntn;

	/* send_yn 발송_여부 character(1) */
	private String sendYn;

	/* send_dttm 발송_일시 character(14) */
	private String sendDttm;

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
	 * Get alam_no 알림_번호 numeric(null)
	 * @Return double alamNo
	 */
	public double getAlamNo() {
		return this.alamNo;
	}
	
	/**
	 * Set alam_no 알림_번호 numeric(null)
	 * @Param double alamNo
	 */
	public void setAlamNo(double alamNo) {
		this.alamNo = alamNo;
	}
	/**
	 * Get alam_chnl_cd 알림_채널_코드 character varying(20)
	 * @Return String alamChnlCd
	 */
	public String getAlamChnlCd() {
		return this.alamChnlCd;
	}
	
	/**
	 * Set alam_chnl_cd 알림_채널_코드 character varying(20)
	 * @Param String alamChnlCd
	 */
	public void setAlamChnlCd(String alamChnlCd) {
		this.alamChnlCd = alamChnlCd;
	}
	/**
	 * Get alam_type_cd 알림_유형_코드 character varying(20)
	 * @Return String alamTypeCd
	 */
	public String getAlamTypeCd() {
		return this.alamTypeCd;
	}
	
	/**
	 * Set alam_type_cd 알림_유형_코드 character varying(20)
	 * @Param String alamTypeCd
	 */
	public void setAlamTypeCd(String alamTypeCd) {
		this.alamTypeCd = alamTypeCd;
	}
	/**
	 * Get alam_titl 알림_제목 character varying(200)
	 * @Return String alamTitl
	 */
	public String getAlamTitl() {
		return this.alamTitl;
	}
	
	/**
	 * Set alam_titl 알림_제목 character varying(200)
	 * @Param String alamTitl
	 */
	public void setAlamTitl(String alamTitl) {
		this.alamTitl = alamTitl;
	}
	/**
	 * Get alam_cntn 알림_내용 text(null)
	 * @Return String alamCntn
	 */
	public String getAlamCntn() {
		return this.alamCntn;
	}
	
	/**
	 * Set alam_cntn 알림_내용 text(null)
	 * @Param String alamCntn
	 */
	public void setAlamCntn(String alamCntn) {
		this.alamCntn = alamCntn;
	}
	/**
	 * Get send_yn 발송_여부 character(1)
	 * @Return String sendYn
	 */
	public String getSendYn() {
		return this.sendYn;
	}
	
	/**
	 * Set send_yn 발송_여부 character(1)
	 * @Param String sendYn
	 */
	public void setSendYn(String sendYn) {
		this.sendYn = sendYn;
	}
	/**
	 * Get send_dttm 발송_일시 character(14)
	 * @Return String sendDttm
	 */
	public String getSendDttm() {
		return this.sendDttm;
	}
	
	/**
	 * Set send_dttm 발송_일시 character(14)
	 * @Param String sendDttm
	 */
	public void setSendDttm(String sendDttm) {
		this.sendDttm = sendDttm;
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
