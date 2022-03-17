package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ts_stdt_cbee_hist Value Object
 */
public class TsStdtCbeeHistVo implements Serializable {

	/* cbee_hist_no 캐시비_이력_번호 numeric(null) */
	private double cbeeHistNo;

	/* stdt_no 학생_번호 numeric(null) */
	private double stdtNo;

	/* cbee_use_cd 캐시비_사용_코드 character varying(20) */
	private String cbeeUseCd;

	/* occr_dttm 발생_일시 character(14) */
	private String occrDttm;

	/* use_cbee_amt 사용_캐시비_금액 numeric(null) */
	private double useCbeeAmt;

	/* cbee_bal 캐시비_잔액 numeric(null) */
	private double cbeeBal;

	/* gram_no 전문_번호 numeric(null) */
	private double gramNo;

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
	 * Get cbee_hist_no 캐시비_이력_번호 numeric(null)
	 * @Return double cbeeHistNo
	 */
	public double getCbeeHistNo() {
		return this.cbeeHistNo;
	}
	
	/**
	 * Set cbee_hist_no 캐시비_이력_번호 numeric(null)
	 * @Param double cbeeHistNo
	 */
	public void setCbeeHistNo(double cbeeHistNo) {
		this.cbeeHistNo = cbeeHistNo;
	}
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
	 * Get cbee_use_cd 캐시비_사용_코드 character varying(20)
	 * @Return String cbeeUseCd
	 */
	public String getCbeeUseCd() {
		return this.cbeeUseCd;
	}
	
	/**
	 * Set cbee_use_cd 캐시비_사용_코드 character varying(20)
	 * @Param String cbeeUseCd
	 */
	public void setCbeeUseCd(String cbeeUseCd) {
		this.cbeeUseCd = cbeeUseCd;
	}
	/**
	 * Get occr_dttm 발생_일시 character(14)
	 * @Return String occrDttm
	 */
	public String getOccrDttm() {
		return this.occrDttm;
	}
	
	/**
	 * Set occr_dttm 발생_일시 character(14)
	 * @Param String occrDttm
	 */
	public void setOccrDttm(String occrDttm) {
		this.occrDttm = occrDttm;
	}
	/**
	 * Get use_cbee_amt 사용_캐시비_금액 numeric(null)
	 * @Return double useCbeeAmt
	 */
	public double getUseCbeeAmt() {
		return this.useCbeeAmt;
	}
	
	/**
	 * Set use_cbee_amt 사용_캐시비_금액 numeric(null)
	 * @Param double useCbeeAmt
	 */
	public void setUseCbeeAmt(double useCbeeAmt) {
		this.useCbeeAmt = useCbeeAmt;
	}
	/**
	 * Get cbee_bal 캐시비_잔액 numeric(null)
	 * @Return double cbeeBal
	 */
	public double getCbeeBal() {
		return this.cbeeBal;
	}
	
	/**
	 * Set cbee_bal 캐시비_잔액 numeric(null)
	 * @Param double cbeeBal
	 */
	public void setCbeeBal(double cbeeBal) {
		this.cbeeBal = cbeeBal;
	}
	/**
	 * Get gram_no 전문_번호 numeric(null)
	 * @Return double gramNo
	 */
	public double getGramNo() {
		return this.gramNo;
	}
	
	/**
	 * Set gram_no 전문_번호 numeric(null)
	 * @Param double gramNo
	 */
	public void setGramNo(double gramNo) {
		this.gramNo = gramNo;
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
