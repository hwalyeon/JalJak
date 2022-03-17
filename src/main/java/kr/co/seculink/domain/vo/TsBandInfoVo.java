package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ts_band_info Value Object
 */
public class TsBandInfoVo implements Serializable {

	/* band_id 밴드_ID character varying(20) */
	private String bandId;

	/* band_mdl_cd 밴드_모델_코드 character varying(20) */
	private String bandMdlCd;

	/* tel_no 전화_번호 character varying(20) */
	private String telNo;

	/* blth_id 블루투스_ID character varying(40) */
	private String blthId;

	/* guar_tel_no_1 보호자_전화_번호_1 character varying(20) */
	private String guarTelNo1;

	/* guar_tel_no_2 보호자_전화_번호_2 character varying(20) */
	private String guarTelNo2;

	/* api_url_gram_no API_URL_전문_번호 numeric(null) */
	private double apiUrlGramNo;

	/* api_url_dttm API_URL_일시 character(14) */
	private String apiUrlDttm;

	/* open_gram_no 개통_전문_번호 numeric(null) */
	private double openGramNo;

	/* band_open_stat_cd 밴드_개통_상태_코드 character varying(20) */
	private String bandOpenStatCd;

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
	 * Get band_mdl_cd 밴드_모델_코드 character varying(20)
	 * @Return String bandMdlCd
	 */
	public String getBandMdlCd() {
		return this.bandMdlCd;
	}
	
	/**
	 * Set band_mdl_cd 밴드_모델_코드 character varying(20)
	 * @Param String bandMdlCd
	 */
	public void setBandMdlCd(String bandMdlCd) {
		this.bandMdlCd = bandMdlCd;
	}
	/**
	 * Get tel_no 전화_번호 character varying(20)
	 * @Return String telNo
	 */
	public String getTelNo() {
		return this.telNo;
	}
	
	/**
	 * Set tel_no 전화_번호 character varying(20)
	 * @Param String telNo
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	/**
	 * Get blth_id 블루투스_ID character varying(40)
	 * @Return String blthId
	 */
	public String getBlthId() {
		return this.blthId;
	}
	
	/**
	 * Set blth_id 블루투스_ID character varying(40)
	 * @Param String blthId
	 */
	public void setBlthId(String blthId) {
		this.blthId = blthId;
	}
	/**
	 * Get guar_tel_no_1 보호자_전화_번호_1 character varying(20)
	 * @Return String guarTelNo1
	 */
	public String getGuarTelNo1() {
		return this.guarTelNo1;
	}
	
	/**
	 * Set guar_tel_no_1 보호자_전화_번호_1 character varying(20)
	 * @Param String guarTelNo1
	 */
	public void setGuarTelNo1(String guarTelNo1) {
		this.guarTelNo1 = guarTelNo1;
	}
	/**
	 * Get guar_tel_no_2 보호자_전화_번호_2 character varying(20)
	 * @Return String guarTelNo2
	 */
	public String getGuarTelNo2() {
		return this.guarTelNo2;
	}
	
	/**
	 * Set guar_tel_no_2 보호자_전화_번호_2 character varying(20)
	 * @Param String guarTelNo2
	 */
	public void setGuarTelNo2(String guarTelNo2) {
		this.guarTelNo2 = guarTelNo2;
	}
	/**
	 * Get api_url_gram_no API_URL_전문_번호 numeric(null)
	 * @Return double apiUrlGramNo
	 */
	public double getApiUrlGramNo() {
		return this.apiUrlGramNo;
	}
	
	/**
	 * Set api_url_gram_no API_URL_전문_번호 numeric(null)
	 * @Param double apiUrlGramNo
	 */
	public void setApiUrlGramNo(double apiUrlGramNo) {
		this.apiUrlGramNo = apiUrlGramNo;
	}
	/**
	 * Get api_url_dttm API_URL_일시 character(14)
	 * @Return String apiUrlDttm
	 */
	public String getApiUrlDttm() {
		return this.apiUrlDttm;
	}
	
	/**
	 * Set api_url_dttm API_URL_일시 character(14)
	 * @Param String apiUrlDttm
	 */
	public void setApiUrlDttm(String apiUrlDttm) {
		this.apiUrlDttm = apiUrlDttm;
	}
	/**
	 * Get open_gram_no 개통_전문_번호 numeric(null)
	 * @Return double openGramNo
	 */
	public double getOpenGramNo() {
		return this.openGramNo;
	}
	
	/**
	 * Set open_gram_no 개통_전문_번호 numeric(null)
	 * @Param double openGramNo
	 */
	public void setOpenGramNo(double openGramNo) {
		this.openGramNo = openGramNo;
	}
	/**
	 * Get band_open_stat_cd 밴드_개통_상태_코드 character varying(20)
	 * @Return String bandOpenStatCd
	 */
	public String getBandOpenStatCd() {
		return this.bandOpenStatCd;
	}
	
	/**
	 * Set band_open_stat_cd 밴드_개통_상태_코드 character varying(20)
	 * @Param String bandOpenStatCd
	 */
	public void setBandOpenStatCd(String bandOpenStatCd) {
		this.bandOpenStatCd = bandOpenStatCd;
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
