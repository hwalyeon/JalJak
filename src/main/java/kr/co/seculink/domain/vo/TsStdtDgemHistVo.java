package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ts_stdt_dgem_hist Value Object
 */
public class TsStdtDgemHistVo implements Serializable {

	/* stdt_no null numeric(null) */
	private double stdtNo;

	/* dgem_hist_seq null numeric(null) */
	private double dgemHistSeq;

	/* loc_hist_no null numeric(null) */
	private double locHistNo;

	/* curr_lat_val null numeric(null) */
	private double currLatVal;

	/* curr_lon_val null numeric(null) */
	private double currLonVal;

	/* loc_mesu_dttm null character(14) */
	private String locMesuDttm;

	/* dgem_idx null numeric(null) */
	private double dgemIdx;

	/* dgem_stat_cd null character varying(20) */
	private String dgemStatCd;

	/* act_div_cd null character varying(20) */
	private String actDivCd;

	/* hbit_stat_cd null character varying(20) */
	private String hbitStatCd;

	/* plc_clss_cd null character varying(20) */
	private String plcClssCd;

	/* temp_stat_cd null character varying(20) */
	private String tempStatCd;

	/* judg_no null numeric(null) */
	private double judgNo;

	/* rmrk null character varying(4000) */
	private String rmrk;

	/* reg_dt null character(8) */
	private String regDt;

	/* reg_tm null character(6) */
	private String regTm;

	/* reg_user_id null character varying(20) */
	private String regUserId;

	/* upt_dt null character(8) */
	private String uptDt;

	/* upt_tm null character(6) */
	private String uptTm;

	/* upt_user_id null character varying(20) */
	private String uptUserId;


	/**
	 * Get stdt_no null numeric(null)
	 * @Return double stdtNo
	 */
	public double getStdtNo() {
		return this.stdtNo;
	}
	
	/**
	 * Set stdt_no null numeric(null)
	 * @Param double stdtNo
	 */
	public void setStdtNo(double stdtNo) {
		this.stdtNo = stdtNo;
	}
	/**
	 * Get dgem_hist_seq null numeric(null)
	 * @Return double dgemHistSeq
	 */
	public double getDgemHistSeq() {
		return this.dgemHistSeq;
	}
	
	/**
	 * Set dgem_hist_seq null numeric(null)
	 * @Param double dgemHistSeq
	 */
	public void setDgemHistSeq(double dgemHistSeq) {
		this.dgemHistSeq = dgemHistSeq;
	}
	/**
	 * Get loc_hist_no null numeric(null)
	 * @Return double locHistNo
	 */
	public double getLocHistNo() {
		return this.locHistNo;
	}
	
	/**
	 * Set loc_hist_no null numeric(null)
	 * @Param double locHistNo
	 */
	public void setLocHistNo(double locHistNo) {
		this.locHistNo = locHistNo;
	}
	/**
	 * Get curr_lat_val null numeric(null)
	 * @Return double currLatVal
	 */
	public double getCurrLatVal() {
		return this.currLatVal;
	}
	
	/**
	 * Set curr_lat_val null numeric(null)
	 * @Param double currLatVal
	 */
	public void setCurrLatVal(double currLatVal) {
		this.currLatVal = currLatVal;
	}
	/**
	 * Get curr_lon_val null numeric(null)
	 * @Return double currLonVal
	 */
	public double getCurrLonVal() {
		return this.currLonVal;
	}
	
	/**
	 * Set curr_lon_val null numeric(null)
	 * @Param double currLonVal
	 */
	public void setCurrLonVal(double currLonVal) {
		this.currLonVal = currLonVal;
	}
	/**
	 * Get loc_mesu_dttm null character(14)
	 * @Return String locMesuDttm
	 */
	public String getLocMesuDttm() {
		return this.locMesuDttm;
	}
	
	/**
	 * Set loc_mesu_dttm null character(14)
	 * @Param String locMesuDttm
	 */
	public void setLocMesuDttm(String locMesuDttm) {
		this.locMesuDttm = locMesuDttm;
	}
	/**
	 * Get dgem_idx null numeric(null)
	 * @Return double dgemIdx
	 */
	public double getDgemIdx() {
		return this.dgemIdx;
	}
	
	/**
	 * Set dgem_idx null numeric(null)
	 * @Param double dgemIdx
	 */
	public void setDgemIdx(double dgemIdx) {
		this.dgemIdx = dgemIdx;
	}
	/**
	 * Get dgem_stat_cd null character varying(20)
	 * @Return String dgemStatCd
	 */
	public String getDgemStatCd() {
		return this.dgemStatCd;
	}
	
	/**
	 * Set dgem_stat_cd null character varying(20)
	 * @Param String dgemStatCd
	 */
	public void setDgemStatCd(String dgemStatCd) {
		this.dgemStatCd = dgemStatCd;
	}
	/**
	 * Get act_div_cd null character varying(20)
	 * @Return String actDivCd
	 */
	public String getActDivCd() {
		return this.actDivCd;
	}
	
	/**
	 * Set act_div_cd null character varying(20)
	 * @Param String actDivCd
	 */
	public void setActDivCd(String actDivCd) {
		this.actDivCd = actDivCd;
	}
	/**
	 * Get hbit_stat_cd null character varying(20)
	 * @Return String hbitStatCd
	 */
	public String getHbitStatCd() {
		return this.hbitStatCd;
	}
	
	/**
	 * Set hbit_stat_cd null character varying(20)
	 * @Param String hbitStatCd
	 */
	public void setHbitStatCd(String hbitStatCd) {
		this.hbitStatCd = hbitStatCd;
	}
	/**
	 * Get plc_clss_cd null character varying(20)
	 * @Return String plcClssCd
	 */
	public String getPlcClssCd() {
		return this.plcClssCd;
	}
	
	/**
	 * Set plc_clss_cd null character varying(20)
	 * @Param String plcClssCd
	 */
	public void setPlcClssCd(String plcClssCd) {
		this.plcClssCd = plcClssCd;
	}
	/**
	 * Get temp_stat_cd null character varying(20)
	 * @Return String tempStatCd
	 */
	public String getTempStatCd() {
		return this.tempStatCd;
	}
	
	/**
	 * Set temp_stat_cd null character varying(20)
	 * @Param String tempStatCd
	 */
	public void setTempStatCd(String tempStatCd) {
		this.tempStatCd = tempStatCd;
	}
	/**
	 * Get judg_no null numeric(null)
	 * @Return double judgNo
	 */
	public double getJudgNo() {
		return this.judgNo;
	}
	
	/**
	 * Set judg_no null numeric(null)
	 * @Param double judgNo
	 */
	public void setJudgNo(double judgNo) {
		this.judgNo = judgNo;
	}
	/**
	 * Get rmrk null character varying(4000)
	 * @Return String rmrk
	 */
	public String getRmrk() {
		return this.rmrk;
	}
	
	/**
	 * Set rmrk null character varying(4000)
	 * @Param String rmrk
	 */
	public void setRmrk(String rmrk) {
		this.rmrk = rmrk;
	}
	/**
	 * Get reg_dt null character(8)
	 * @Return String regDt
	 */
	public String getRegDt() {
		return this.regDt;
	}
	
	/**
	 * Set reg_dt null character(8)
	 * @Param String regDt
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	/**
	 * Get reg_tm null character(6)
	 * @Return String regTm
	 */
	public String getRegTm() {
		return this.regTm;
	}
	
	/**
	 * Set reg_tm null character(6)
	 * @Param String regTm
	 */
	public void setRegTm(String regTm) {
		this.regTm = regTm;
	}
	/**
	 * Get reg_user_id null character varying(20)
	 * @Return String regUserId
	 */
	public String getRegUserId() {
		return this.regUserId;
	}
	
	/**
	 * Set reg_user_id null character varying(20)
	 * @Param String regUserId
	 */
	public void setRegUserId(String regUserId) {
		this.regUserId = regUserId;
	}
	/**
	 * Get upt_dt null character(8)
	 * @Return String uptDt
	 */
	public String getUptDt() {
		return this.uptDt;
	}
	
	/**
	 * Set upt_dt null character(8)
	 * @Param String uptDt
	 */
	public void setUptDt(String uptDt) {
		this.uptDt = uptDt;
	}
	/**
	 * Get upt_tm null character(6)
	 * @Return String uptTm
	 */
	public String getUptTm() {
		return this.uptTm;
	}
	
	/**
	 * Set upt_tm null character(6)
	 * @Param String uptTm
	 */
	public void setUptTm(String uptTm) {
		this.uptTm = uptTm;
	}
	/**
	 * Get upt_user_id null character varying(20)
	 * @Return String uptUserId
	 */
	public String getUptUserId() {
		return this.uptUserId;
	}
	
	/**
	 * Set upt_user_id null character varying(20)
	 * @Param String uptUserId
	 */
	public void setUptUserId(String uptUserId) {
		this.uptUserId = uptUserId;
	}

} // end of class
