package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ti_fatp_qust_spec Value Object
 */
public class TiFatpQustSpecVo implements Serializable {

	/* qust_ver 설문_버전 numeric(null) */
	private double qustVer;

	/* qust_no 질문_번호 numeric(null) */
	private double qustNo;

	/* ans_seq 답변_순번 numeric(null) */
	private double ansSeq;

	/* ans_val 답변_값 character varying(20) */
	private String ansVal;

	/* ans_cntn 답변_내용 character varying(100) */
	private String ansCntn;

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
	 * Get qust_ver 설문_버전 numeric(null)
	 * @Return double qustVer
	 */
	public double getQustVer() {
		return this.qustVer;
	}
	
	/**
	 * Set qust_ver 설문_버전 numeric(null)
	 * @Param double qustVer
	 */
	public void setQustVer(double qustVer) {
		this.qustVer = qustVer;
	}
	/**
	 * Get qust_no 질문_번호 numeric(null)
	 * @Return double qustNo
	 */
	public double getQustNo() {
		return this.qustNo;
	}
	
	/**
	 * Set qust_no 질문_번호 numeric(null)
	 * @Param double qustNo
	 */
	public void setQustNo(double qustNo) {
		this.qustNo = qustNo;
	}
	/**
	 * Get ans_seq 답변_순번 numeric(null)
	 * @Return double ansSeq
	 */
	public double getAnsSeq() {
		return this.ansSeq;
	}
	
	/**
	 * Set ans_seq 답변_순번 numeric(null)
	 * @Param double ansSeq
	 */
	public void setAnsSeq(double ansSeq) {
		this.ansSeq = ansSeq;
	}
	/**
	 * Get ans_val 답변_값 character varying(20)
	 * @Return String ansVal
	 */
	public String getAnsVal() {
		return this.ansVal;
	}
	
	/**
	 * Set ans_val 답변_값 character varying(20)
	 * @Param String ansVal
	 */
	public void setAnsVal(String ansVal) {
		this.ansVal = ansVal;
	}
	/**
	 * Get ans_cntn 답변_내용 character varying(100)
	 * @Return String ansCntn
	 */
	public String getAnsCntn() {
		return this.ansCntn;
	}
	
	/**
	 * Set ans_cntn 답변_내용 character varying(100)
	 * @Param String ansCntn
	 */
	public void setAnsCntn(String ansCntn) {
		this.ansCntn = ansCntn;
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
