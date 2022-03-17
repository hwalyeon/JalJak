package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ts_blbd_base Value Object
 */
public class TsBlbdBaseVo implements Serializable {

	/* blbd_no 게시_번호 numeric(null) */
	private double blbdNo;

	/* blbd_strt_dt 게시_시작_일자 character(8) */
	private String blbdStrtDt;

	/* blbd_expr_dt 게시_만기_일자 character(8) */
	private String blbdExprDt;

	/* blbd_type_cd 게시_유형_코드 character varying(20) */
	private String blbdTypeCd;

	/* blbd_titl 게시_제목 character varying(100) */
	private String blbdTitl;

	/* blbd_cntn 게시_내용 text(null) */
	private String blbdCntn;

	/* srch_cnt 조회_건수 numeric(null) */
	private double srchCnt;

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
	 * Get blbd_no 게시_번호 numeric(null)
	 * @Return double blbdNo
	 */
	public double getBlbdNo() {
		return this.blbdNo;
	}
	
	/**
	 * Set blbd_no 게시_번호 numeric(null)
	 * @Param double blbdNo
	 */
	public void setBlbdNo(double blbdNo) {
		this.blbdNo = blbdNo;
	}
	/**
	 * Get blbd_strt_dt 게시_시작_일자 character(8)
	 * @Return String blbdStrtDt
	 */
	public String getBlbdStrtDt() {
		return this.blbdStrtDt;
	}
	
	/**
	 * Set blbd_strt_dt 게시_시작_일자 character(8)
	 * @Param String blbdStrtDt
	 */
	public void setBlbdStrtDt(String blbdStrtDt) {
		this.blbdStrtDt = blbdStrtDt;
	}
	/**
	 * Get blbd_expr_dt 게시_만기_일자 character(8)
	 * @Return String blbdExprDt
	 */
	public String getBlbdExprDt() {
		return this.blbdExprDt;
	}
	
	/**
	 * Set blbd_expr_dt 게시_만기_일자 character(8)
	 * @Param String blbdExprDt
	 */
	public void setBlbdExprDt(String blbdExprDt) {
		this.blbdExprDt = blbdExprDt;
	}
	/**
	 * Get blbd_type_cd 게시_유형_코드 character varying(20)
	 * @Return String blbdTypeCd
	 */
	public String getBlbdTypeCd() {
		return this.blbdTypeCd;
	}
	
	/**
	 * Set blbd_type_cd 게시_유형_코드 character varying(20)
	 * @Param String blbdTypeCd
	 */
	public void setBlbdTypeCd(String blbdTypeCd) {
		this.blbdTypeCd = blbdTypeCd;
	}
	/**
	 * Get blbd_titl 게시_제목 character varying(100)
	 * @Return String blbdTitl
	 */
	public String getBlbdTitl() {
		return this.blbdTitl;
	}
	
	/**
	 * Set blbd_titl 게시_제목 character varying(100)
	 * @Param String blbdTitl
	 */
	public void setBlbdTitl(String blbdTitl) {
		this.blbdTitl = blbdTitl;
	}
	/**
	 * Get blbd_cntn 게시_내용 text(null)
	 * @Return String blbdCntn
	 */
	public String getBlbdCntn() {
		return this.blbdCntn;
	}
	
	/**
	 * Set blbd_cntn 게시_내용 text(null)
	 * @Param String blbdCntn
	 */
	public void setBlbdCntn(String blbdCntn) {
		this.blbdCntn = blbdCntn;
	}
	/**
	 * Get srch_cnt 조회_건수 numeric(null)
	 * @Return double srchCnt
	 */
	public double getSrchCnt() {
		return this.srchCnt;
	}
	
	/**
	 * Set srch_cnt 조회_건수 numeric(null)
	 * @Param double srchCnt
	 */
	public void setSrchCnt(double srchCnt) {
		this.srchCnt = srchCnt;
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
