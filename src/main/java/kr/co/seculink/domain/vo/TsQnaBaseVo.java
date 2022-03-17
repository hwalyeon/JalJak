package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ts_qna_base Value Object
 */
public class TsQnaBaseVo implements Serializable {

	/* qna_no 질의응답_번호 numeric(null) */
	private double qnaNo;

	/* qust_guar_no 질문_보호자_번호 numeric(null) */
	private double qustGuarNo;

	/* qust_dt 질문_일자 character(8) */
	private String qustDt;

	/* qust_tm 질문_시각 character(6) */
	private String qustTm;

	/* qust_titl 질문_제목 character varying(200) */
	private String qustTitl;

	/* qust_cntn 질문_내용 text(null) */
	private String qustCntn;

	/* answer_user_id 답변_사용자_ID character varying(40) */
	private String answerUserId;

	/* answer_dt 답변_일자 character(8) */
	private String answerDt;

	/* answer_tm 답변_시각 character(6) */
	private String answerTm;

	/* answer_cntn 답변_내용 text(null) */
	private String answerCntn;

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
	 * Get qna_no 질의응답_번호 numeric(null)
	 * @Return double qnaNo
	 */
	public double getQnaNo() {
		return this.qnaNo;
	}
	
	/**
	 * Set qna_no 질의응답_번호 numeric(null)
	 * @Param double qnaNo
	 */
	public void setQnaNo(double qnaNo) {
		this.qnaNo = qnaNo;
	}
	/**
	 * Get qust_guar_no 질문_보호자_번호 numeric(null)
	 * @Return double qustGuarNo
	 */
	public double getQustGuarNo() {
		return this.qustGuarNo;
	}
	
	/**
	 * Set qust_guar_no 질문_보호자_번호 numeric(null)
	 * @Param double qustGuarNo
	 */
	public void setQustGuarNo(double qustGuarNo) {
		this.qustGuarNo = qustGuarNo;
	}
	/**
	 * Get qust_dt 질문_일자 character(8)
	 * @Return String qustDt
	 */
	public String getQustDt() {
		return this.qustDt;
	}
	
	/**
	 * Set qust_dt 질문_일자 character(8)
	 * @Param String qustDt
	 */
	public void setQustDt(String qustDt) {
		this.qustDt = qustDt;
	}
	/**
	 * Get qust_tm 질문_시각 character(6)
	 * @Return String qustTm
	 */
	public String getQustTm() {
		return this.qustTm;
	}
	
	/**
	 * Set qust_tm 질문_시각 character(6)
	 * @Param String qustTm
	 */
	public void setQustTm(String qustTm) {
		this.qustTm = qustTm;
	}
	/**
	 * Get qust_titl 질문_제목 character varying(200)
	 * @Return String qustTitl
	 */
	public String getQustTitl() {
		return this.qustTitl;
	}
	
	/**
	 * Set qust_titl 질문_제목 character varying(200)
	 * @Param String qustTitl
	 */
	public void setQustTitl(String qustTitl) {
		this.qustTitl = qustTitl;
	}
	/**
	 * Get qust_cntn 질문_내용 text(null)
	 * @Return String qustCntn
	 */
	public String getQustCntn() {
		return this.qustCntn;
	}
	
	/**
	 * Set qust_cntn 질문_내용 text(null)
	 * @Param String qustCntn
	 */
	public void setQustCntn(String qustCntn) {
		this.qustCntn = qustCntn;
	}
	/**
	 * Get answer_user_id 답변_사용자_ID character varying(40)
	 * @Return String answerUserId
	 */
	public String getAnswerUserId() {
		return this.answerUserId;
	}
	
	/**
	 * Set answer_user_id 답변_사용자_ID character varying(40)
	 * @Param String answerUserId
	 */
	public void setAnswerUserId(String answerUserId) {
		this.answerUserId = answerUserId;
	}
	/**
	 * Get answer_dt 답변_일자 character(8)
	 * @Return String answerDt
	 */
	public String getAnswerDt() {
		return this.answerDt;
	}
	
	/**
	 * Set answer_dt 답변_일자 character(8)
	 * @Param String answerDt
	 */
	public void setAnswerDt(String answerDt) {
		this.answerDt = answerDt;
	}
	/**
	 * Get answer_tm 답변_시각 character(6)
	 * @Return String answerTm
	 */
	public String getAnswerTm() {
		return this.answerTm;
	}
	
	/**
	 * Set answer_tm 답변_시각 character(6)
	 * @Param String answerTm
	 */
	public void setAnswerTm(String answerTm) {
		this.answerTm = answerTm;
	}
	/**
	 * Get answer_cntn 답변_내용 text(null)
	 * @Return String answerCntn
	 */
	public String getAnswerCntn() {
		return this.answerCntn;
	}
	
	/**
	 * Set answer_cntn 답변_내용 text(null)
	 * @Param String answerCntn
	 */
	public void setAnswerCntn(String answerCntn) {
		this.answerCntn = answerCntn;
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
