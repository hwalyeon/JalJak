package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * td_judg_base Value Object
 */
public class TdJudgBaseVo implements Serializable {

	/* judg_no 판정_번호 numeric(null) */
	private double judgNo;

	/* evnt_div_cd 이벤트_구분_코드 character varying(20) */
	private String evntDivCd;

	/* band_id 밴드_ID character varying(20) */
	private String bandId;

	/* occr_dttm 발생_일시 character(18) */
	private String occrDttm;

	/* lat_val 위도_값 numeric(null) */
	private double latVal;

	/* lon_val 경도_값 numeric(null) */
	private double lonVal;

	/* hbit_stat_cd 심박_상태_코드 character varying(20) */
	private String hbitStatCd;

	/* min_hbit_cnt 최소_심박_수 numeric(null) */
	private double minHbitCnt;

	/* max_hbit_cnt 최대_심박_수 numeric(null) */
	private double maxHbitCnt;

	/* hbit_mdan 심박_중간값 numeric(null) */
	private double hbitMdan;

	/* abnm_hbit_cnt 이상_심박_수 numeric(null) */
	private double abnmHbitCnt;

	/* min1_hbit_cntn 1분_심박_내용 numeric(null) */
	private double min1HbitCntn;

	/* temp_val 체온_값 numeric(null) */
	private double tempVal;

	/* act_div_cd 활동_구분_코드 character varying(20) */
	private String actDivCd;

	/* cbee_bal 캐시비_잔액 numeric(null) */
	private double cbeeBal;

	/* accu_run_tcnt_cnt 누적_달리기_시간_수 numeric(null) */
	private double accuRunTcntCnt;

	/* accu_walk_tcnt_cnt 누적_걸음_시간_수 numeric(null) */
	private double accuWalkTcntCnt;

	/* accu_walk_cnt 누적_걸음_수 numeric(null) */
	private double accuWalkCnt;

	/* gram_no 전문_번호 numeric(null) */
	private double gramNo;

	/* alam_no 알림_번호 numeric(null) */
	private double alamNo;

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
	 * Get judg_no 판정_번호 numeric(null)
	 * @Return double judgNo
	 */
	public double getJudgNo() {
		return this.judgNo;
	}
	
	/**
	 * Set judg_no 판정_번호 numeric(null)
	 * @Param double judgNo
	 */
	public void setJudgNo(double judgNo) {
		this.judgNo = judgNo;
	}
	/**
	 * Get evnt_div_cd 이벤트_구분_코드 character varying(20)
	 * @Return String evntDivCd
	 */
	public String getEvntDivCd() {
		return this.evntDivCd;
	}
	
	/**
	 * Set evnt_div_cd 이벤트_구분_코드 character varying(20)
	 * @Param String evntDivCd
	 */
	public void setEvntDivCd(String evntDivCd) {
		this.evntDivCd = evntDivCd;
	}
	/**
	 * Get band_id 밴드_ID character varying(20)
	 * @Return String bandId
	 */
	public String getBandId() {
		return this.bandId;
	}
	
	/**
	 * Set band_id 밴드_ID character varying(20)
	 * @Param String bandId
	 */
	public void setBandId(String bandId) {
		this.bandId = bandId;
	}
	/**
	 * Get occr_dttm 발생_일시 character(18)
	 * @Return String occrDttm
	 */
	public String getOccrDttm() {
		return this.occrDttm;
	}
	
	/**
	 * Set occr_dttm 발생_일시 character(18)
	 * @Param String occrDttm
	 */
	public void setOccrDttm(String occrDttm) {
		this.occrDttm = occrDttm;
	}
	/**
	 * Get lat_val 위도_값 numeric(null)
	 * @Return double latVal
	 */
	public double getLatVal() {
		return this.latVal;
	}
	
	/**
	 * Set lat_val 위도_값 numeric(null)
	 * @Param double latVal
	 */
	public void setLatVal(double latVal) {
		this.latVal = latVal;
	}
	/**
	 * Get lon_val 경도_값 numeric(null)
	 * @Return double lonVal
	 */
	public double getLonVal() {
		return this.lonVal;
	}
	
	/**
	 * Set lon_val 경도_값 numeric(null)
	 * @Param double lonVal
	 */
	public void setLonVal(double lonVal) {
		this.lonVal = lonVal;
	}
	/**
	 * Get hbit_stat_cd 심박_상태_코드 character varying(20)
	 * @Return String hbitStatCd
	 */
	public String getHbitStatCd() {
		return this.hbitStatCd;
	}
	
	/**
	 * Set hbit_stat_cd 심박_상태_코드 character varying(20)
	 * @Param String hbitStatCd
	 */
	public void setHbitStatCd(String hbitStatCd) {
		this.hbitStatCd = hbitStatCd;
	}
	/**
	 * Get min_hbit_cnt 최소_심박_수 numeric(null)
	 * @Return double minHbitCnt
	 */
	public double getMinHbitCnt() {
		return this.minHbitCnt;
	}
	
	/**
	 * Set min_hbit_cnt 최소_심박_수 numeric(null)
	 * @Param double minHbitCnt
	 */
	public void setMinHbitCnt(double minHbitCnt) {
		this.minHbitCnt = minHbitCnt;
	}
	/**
	 * Get max_hbit_cnt 최대_심박_수 numeric(null)
	 * @Return double maxHbitCnt
	 */
	public double getMaxHbitCnt() {
		return this.maxHbitCnt;
	}
	
	/**
	 * Set max_hbit_cnt 최대_심박_수 numeric(null)
	 * @Param double maxHbitCnt
	 */
	public void setMaxHbitCnt(double maxHbitCnt) {
		this.maxHbitCnt = maxHbitCnt;
	}
	/**
	 * Get hbit_mdan 심박_중간값 numeric(null)
	 * @Return double hbitMdan
	 */
	public double getHbitMdan() {
		return this.hbitMdan;
	}
	
	/**
	 * Set hbit_mdan 심박_중간값 numeric(null)
	 * @Param double hbitMdan
	 */
	public void setHbitMdan(double hbitMdan) {
		this.hbitMdan = hbitMdan;
	}
	/**
	 * Get abnm_hbit_cnt 이상_심박_수 numeric(null)
	 * @Return double abnmHbitCnt
	 */
	public double getAbnmHbitCnt() {
		return this.abnmHbitCnt;
	}
	
	/**
	 * Set abnm_hbit_cnt 이상_심박_수 numeric(null)
	 * @Param double abnmHbitCnt
	 */
	public void setAbnmHbitCnt(double abnmHbitCnt) {
		this.abnmHbitCnt = abnmHbitCnt;
	}
	/**
	 * Get min1_hbit_cntn 1분_심박_내용 numeric(null)
	 * @Return double min1HbitCntn
	 */
	public double getMin1HbitCntn() {
		return this.min1HbitCntn;
	}
	
	/**
	 * Set min1_hbit_cntn 1분_심박_내용 numeric(null)
	 * @Param double min1HbitCntn
	 */
	public void setMin1HbitCntn(double min1HbitCntn) {
		this.min1HbitCntn = min1HbitCntn;
	}
	/**
	 * Get temp_val 체온_값 numeric(null)
	 * @Return double tempVal
	 */
	public double getTempVal() {
		return this.tempVal;
	}
	
	/**
	 * Set temp_val 체온_값 numeric(null)
	 * @Param double tempVal
	 */
	public void setTempVal(double tempVal) {
		this.tempVal = tempVal;
	}
	/**
	 * Get act_div_cd 활동_구분_코드 character varying(20)
	 * @Return String actDivCd
	 */
	public String getActDivCd() {
		return this.actDivCd;
	}
	
	/**
	 * Set act_div_cd 활동_구분_코드 character varying(20)
	 * @Param String actDivCd
	 */
	public void setActDivCd(String actDivCd) {
		this.actDivCd = actDivCd;
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
	 * Get accu_run_tcnt_cnt 누적_달리기_시간_수 numeric(null)
	 * @Return double accuRunTcntCnt
	 */
	public double getAccuRunTcntCnt() {
		return this.accuRunTcntCnt;
	}
	
	/**
	 * Set accu_run_tcnt_cnt 누적_달리기_시간_수 numeric(null)
	 * @Param double accuRunTcntCnt
	 */
	public void setAccuRunTcntCnt(double accuRunTcntCnt) {
		this.accuRunTcntCnt = accuRunTcntCnt;
	}
	/**
	 * Get accu_walk_tcnt_cnt 누적_걸음_시간_수 numeric(null)
	 * @Return double accuWalkTcntCnt
	 */
	public double getAccuWalkTcntCnt() {
		return this.accuWalkTcntCnt;
	}
	
	/**
	 * Set accu_walk_tcnt_cnt 누적_걸음_시간_수 numeric(null)
	 * @Param double accuWalkTcntCnt
	 */
	public void setAccuWalkTcntCnt(double accuWalkTcntCnt) {
		this.accuWalkTcntCnt = accuWalkTcntCnt;
	}
	/**
	 * Get accu_walk_cnt 누적_걸음_수 numeric(null)
	 * @Return double accuWalkCnt
	 */
	public double getAccuWalkCnt() {
		return this.accuWalkCnt;
	}
	
	/**
	 * Set accu_walk_cnt 누적_걸음_수 numeric(null)
	 * @Param double accuWalkCnt
	 */
	public void setAccuWalkCnt(double accuWalkCnt) {
		this.accuWalkCnt = accuWalkCnt;
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
