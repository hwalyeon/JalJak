package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ts_dd_eat_hist Value Object
 */
public class TsDdEatHistVo implements Serializable {

	/* stnd_dt 기준_일자 numeric(null) */
	private double stndDt;

	/* stdt_no 학생_번호 numeric(null) */
	private double stdtNo;

	/* eat_seq 섭취_순번 numeric(null) */
	private double eatSeq;

	/* guar_no 보호자_번호 numeric(null) */
	private double guarNo;

	/* fmenu_seq 식단표_순번 numeric(null) */
	private double fmenuSeq;

	/* eat_qty 섭취_용량 numeric(null) */
	private double eatQty;

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
	 * Get stnd_dt 기준_일자 numeric(null)
	 * @Return double stndDt
	 */
	public double getStndDt() {
		return this.stndDt;
	}
	
	/**
	 * Set stnd_dt 기준_일자 numeric(null)
	 * @Param double stndDt
	 */
	public void setStndDt(double stndDt) {
		this.stndDt = stndDt;
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
	 * Get eat_seq 섭취_순번 numeric(null)
	 * @Return double eatSeq
	 */
	public double getEatSeq() {
		return this.eatSeq;
	}
	
	/**
	 * Set eat_seq 섭취_순번 numeric(null)
	 * @Param double eatSeq
	 */
	public void setEatSeq(double eatSeq) {
		this.eatSeq = eatSeq;
	}
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
	 * Get eat_qty 섭취_용량 numeric(null)
	 * @Return double eatQty
	 */
	public double getEatQty() {
		return this.eatQty;
	}
	
	/**
	 * Set eat_qty 섭취_용량 numeric(null)
	 * @Param double eatQty
	 */
	public void setEatQty(double eatQty) {
		this.eatQty = eatQty;
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
