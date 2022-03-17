package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ts_fmenu_base Value Object
 */
public class TsFmenuBaseVo implements Serializable {

	/* guar_no 보호자_번호 numeric(null) */
	private double guarNo;

	/* fmenu_seq 식단표_순번 numeric(null) */
	private double fmenuSeq;

	/* fmenu_nm 식단표_명 character varying(100) */
	private String fmenuNm;

	/* mmel_yn 아침_여부 character(1) */
	private String mmelYn;

	/* amel_yn 점심_여부 character(1) */
	private String amelYn;

	/* emel_yn 저녁_여부 character(1) */
	private String emelYn;

	/* smel_yn 간식_여부 character(1) */
	private String smelYn;

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
	 * Get fmenu_nm 식단표_명 character varying(100)
	 * @Return String fmenuNm
	 */
	public String getFmenuNm() {
		return this.fmenuNm;
	}
	
	/**
	 * Set fmenu_nm 식단표_명 character varying(100)
	 * @Param String fmenuNm
	 */
	public void setFmenuNm(String fmenuNm) {
		this.fmenuNm = fmenuNm;
	}
	/**
	 * Get mmel_yn 아침_여부 character(1)
	 * @Return String mmelYn
	 */
	public String getMmelYn() {
		return this.mmelYn;
	}
	
	/**
	 * Set mmel_yn 아침_여부 character(1)
	 * @Param String mmelYn
	 */
	public void setMmelYn(String mmelYn) {
		this.mmelYn = mmelYn;
	}
	/**
	 * Get amel_yn 점심_여부 character(1)
	 * @Return String amelYn
	 */
	public String getAmelYn() {
		return this.amelYn;
	}
	
	/**
	 * Set amel_yn 점심_여부 character(1)
	 * @Param String amelYn
	 */
	public void setAmelYn(String amelYn) {
		this.amelYn = amelYn;
	}
	/**
	 * Get emel_yn 저녁_여부 character(1)
	 * @Return String emelYn
	 */
	public String getEmelYn() {
		return this.emelYn;
	}
	
	/**
	 * Set emel_yn 저녁_여부 character(1)
	 * @Param String emelYn
	 */
	public void setEmelYn(String emelYn) {
		this.emelYn = emelYn;
	}
	/**
	 * Get smel_yn 간식_여부 character(1)
	 * @Return String smelYn
	 */
	public String getSmelYn() {
		return this.smelYn;
	}
	
	/**
	 * Set smel_yn 간식_여부 character(1)
	 * @Param String smelYn
	 */
	public void setSmelYn(String smelYn) {
		this.smelYn = smelYn;
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
