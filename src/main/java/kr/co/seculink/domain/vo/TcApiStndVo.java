package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * tc_api_stnd Value Object
 */
public class TcApiStndVo implements Serializable {

	/* svr_id 서버_ID character varying(20) */
	private String svrId;

	/* svr_nm 서버_명 character varying(100) */
	private String svrNm;

	/* api_url API_URL character varying(200) */
	private String apiUrl;

	/* gps_base_cycl GPS정기핑_기본_주기 numeric(null) */
	private double gpsBaseCycl;

	/* gps_next_cycl GPS정기핑_다음_주기 numeric(null) */
	private double gpsNextCycl;

	/* rdet_next_cycl 정기판정_다음_주기 numeric(null) */
	private double rdetNextCycl;

	/* rdet_base_cycl 정기판정_기본_주기 numeric(null) */
	private double rdetBaseCycl;

	/* led_slep_tcnt LED_슬립_시간 numeric(null) */
	private double ledSlepTcnt;

	/* msor_ssng_levl 모션센서_감지_레벨 numeric(null) */
	private double msorSsngLevl;

	/* hsor_isng_cycl 심박센서_내부감지_주기 numeric(null) */
	private double hsorIsngCycl;

	/* tsor_isng_cycl 체온센서_내부감지_주기 numeric(null) */
	private double tsorIsngCycl;

	/* tsor_evnt_minval 체온센서_이벤트_하한값 numeric(null) */
	private double tsorEvntMinval;

	/* tsor_evnt_maxval 체온센서_이벤트_상한값 numeric(null) */
	private double tsorEvntMaxval;

	/* hbitcnt_mdan_minval 심박수_중간값_하한값 numeric(null) */
	private double hbitcntMdanMinval;

	/* hbitcnt_mdan_maxval 심박수_중간값_상한값 numeric(null) */
	private double hbitcntMdanMaxval;

	/* msor_evnt_minval 모션센서_이벤트_하한값 numeric(null) */
	private double msorEvntMinval;

	/* msor_evnt_maxval 모션센서_이벤트_상한값 numeric(null) */
	private double msorEvntMaxval;

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
	 * Get svr_id 서버_ID character varying(20)
	 * @Return String svrId
	 */
	public String getSvrId() {
		return this.svrId;
	}
	
	/**
	 * Set svr_id 서버_ID character varying(20)
	 * @Param String svrId
	 */
	public void setSvrId(String svrId) {
		this.svrId = svrId;
	}
	/**
	 * Get svr_nm 서버_명 character varying(100)
	 * @Return String svrNm
	 */
	public String getSvrNm() {
		return this.svrNm;
	}
	
	/**
	 * Set svr_nm 서버_명 character varying(100)
	 * @Param String svrNm
	 */
	public void setSvrNm(String svrNm) {
		this.svrNm = svrNm;
	}
	/**
	 * Get api_url API_URL character varying(200)
	 * @Return String apiUrl
	 */
	public String getApiUrl() {
		return this.apiUrl;
	}
	
	/**
	 * Set api_url API_URL character varying(200)
	 * @Param String apiUrl
	 */
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	/**
	 * Get gps_base_cycl GPS정기핑_기본_주기 numeric(null)
	 * @Return double gpsBaseCycl
	 */
	public double getGpsBaseCycl() {
		return this.gpsBaseCycl;
	}
	
	/**
	 * Set gps_base_cycl GPS정기핑_기본_주기 numeric(null)
	 * @Param double gpsBaseCycl
	 */
	public void setGpsBaseCycl(double gpsBaseCycl) {
		this.gpsBaseCycl = gpsBaseCycl;
	}
	/**
	 * Get gps_next_cycl GPS정기핑_다음_주기 numeric(null)
	 * @Return double gpsNextCycl
	 */
	public double getGpsNextCycl() {
		return this.gpsNextCycl;
	}
	
	/**
	 * Set gps_next_cycl GPS정기핑_다음_주기 numeric(null)
	 * @Param double gpsNextCycl
	 */
	public void setGpsNextCycl(double gpsNextCycl) {
		this.gpsNextCycl = gpsNextCycl;
	}
	/**
	 * Get rdet_next_cycl 정기판정_다음_주기 numeric(null)
	 * @Return double rdetNextCycl
	 */
	public double getRdetNextCycl() {
		return this.rdetNextCycl;
	}
	
	/**
	 * Set rdet_next_cycl 정기판정_다음_주기 numeric(null)
	 * @Param double rdetNextCycl
	 */
	public void setRdetNextCycl(double rdetNextCycl) {
		this.rdetNextCycl = rdetNextCycl;
	}
	/**
	 * Get rdet_base_cycl 정기판정_기본_주기 numeric(null)
	 * @Return double rdetBaseCycl
	 */
	public double getRdetBaseCycl() {
		return this.rdetBaseCycl;
	}
	
	/**
	 * Set rdet_base_cycl 정기판정_기본_주기 numeric(null)
	 * @Param double rdetBaseCycl
	 */
	public void setRdetBaseCycl(double rdetBaseCycl) {
		this.rdetBaseCycl = rdetBaseCycl;
	}
	/**
	 * Get led_slep_tcnt LED_슬립_시간 numeric(null)
	 * @Return double ledSlepTcnt
	 */
	public double getLedSlepTcnt() {
		return this.ledSlepTcnt;
	}
	
	/**
	 * Set led_slep_tcnt LED_슬립_시간 numeric(null)
	 * @Param double ledSlepTcnt
	 */
	public void setLedSlepTcnt(double ledSlepTcnt) {
		this.ledSlepTcnt = ledSlepTcnt;
	}
	/**
	 * Get msor_ssng_levl 모션센서_감지_레벨 numeric(null)
	 * @Return double msorSsngLevl
	 */
	public double getMsorSsngLevl() {
		return this.msorSsngLevl;
	}
	
	/**
	 * Set msor_ssng_levl 모션센서_감지_레벨 numeric(null)
	 * @Param double msorSsngLevl
	 */
	public void setMsorSsngLevl(double msorSsngLevl) {
		this.msorSsngLevl = msorSsngLevl;
	}
	/**
	 * Get hsor_isng_cycl 심박센서_내부감지_주기 numeric(null)
	 * @Return double hsorIsngCycl
	 */
	public double getHsorIsngCycl() {
		return this.hsorIsngCycl;
	}
	
	/**
	 * Set hsor_isng_cycl 심박센서_내부감지_주기 numeric(null)
	 * @Param double hsorIsngCycl
	 */
	public void setHsorIsngCycl(double hsorIsngCycl) {
		this.hsorIsngCycl = hsorIsngCycl;
	}
	/**
	 * Get tsor_isng_cycl 체온센서_내부감지_주기 numeric(null)
	 * @Return double tsorIsngCycl
	 */
	public double getTsorIsngCycl() {
		return this.tsorIsngCycl;
	}
	
	/**
	 * Set tsor_isng_cycl 체온센서_내부감지_주기 numeric(null)
	 * @Param double tsorIsngCycl
	 */
	public void setTsorIsngCycl(double tsorIsngCycl) {
		this.tsorIsngCycl = tsorIsngCycl;
	}
	/**
	 * Get tsor_evnt_minval 체온센서_이벤트_하한값 numeric(null)
	 * @Return double tsorEvntMinval
	 */
	public double getTsorEvntMinval() {
		return this.tsorEvntMinval;
	}
	
	/**
	 * Set tsor_evnt_minval 체온센서_이벤트_하한값 numeric(null)
	 * @Param double tsorEvntMinval
	 */
	public void setTsorEvntMinval(double tsorEvntMinval) {
		this.tsorEvntMinval = tsorEvntMinval;
	}
	/**
	 * Get tsor_evnt_maxval 체온센서_이벤트_상한값 numeric(null)
	 * @Return double tsorEvntMaxval
	 */
	public double getTsorEvntMaxval() {
		return this.tsorEvntMaxval;
	}
	
	/**
	 * Set tsor_evnt_maxval 체온센서_이벤트_상한값 numeric(null)
	 * @Param double tsorEvntMaxval
	 */
	public void setTsorEvntMaxval(double tsorEvntMaxval) {
		this.tsorEvntMaxval = tsorEvntMaxval;
	}
	/**
	 * Get hbitcnt_mdan_minval 심박수_중간값_하한값 numeric(null)
	 * @Return double hbitcntMdanMinval
	 */
	public double getHbitcntMdanMinval() {
		return this.hbitcntMdanMinval;
	}
	
	/**
	 * Set hbitcnt_mdan_minval 심박수_중간값_하한값 numeric(null)
	 * @Param double hbitcntMdanMinval
	 */
	public void setHbitcntMdanMinval(double hbitcntMdanMinval) {
		this.hbitcntMdanMinval = hbitcntMdanMinval;
	}
	/**
	 * Get hbitcnt_mdan_maxval 심박수_중간값_상한값 numeric(null)
	 * @Return double hbitcntMdanMaxval
	 */
	public double getHbitcntMdanMaxval() {
		return this.hbitcntMdanMaxval;
	}
	
	/**
	 * Set hbitcnt_mdan_maxval 심박수_중간값_상한값 numeric(null)
	 * @Param double hbitcntMdanMaxval
	 */
	public void setHbitcntMdanMaxval(double hbitcntMdanMaxval) {
		this.hbitcntMdanMaxval = hbitcntMdanMaxval;
	}
	/**
	 * Get msor_evnt_minval 모션센서_이벤트_하한값 numeric(null)
	 * @Return double msorEvntMinval
	 */
	public double getMsorEvntMinval() {
		return this.msorEvntMinval;
	}
	
	/**
	 * Set msor_evnt_minval 모션센서_이벤트_하한값 numeric(null)
	 * @Param double msorEvntMinval
	 */
	public void setMsorEvntMinval(double msorEvntMinval) {
		this.msorEvntMinval = msorEvntMinval;
	}
	/**
	 * Get msor_evnt_maxval 모션센서_이벤트_상한값 numeric(null)
	 * @Return double msorEvntMaxval
	 */
	public double getMsorEvntMaxval() {
		return this.msorEvntMaxval;
	}
	
	/**
	 * Set msor_evnt_maxval 모션센서_이벤트_상한값 numeric(null)
	 * @Param double msorEvntMaxval
	 */
	public void setMsorEvntMaxval(double msorEvntMaxval) {
		this.msorEvntMaxval = msorEvntMaxval;
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
