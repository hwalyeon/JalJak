package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ti_grow_stnd Value Object
 */
public class TiGrowStndVo implements Serializable {

	/* grow_stnd_ver 성장_기준_버전 character varying(500) */
	private String growStndVer;

	/* grow_stnd_no 성장_기준_번호 numeric(null) */
	private double growStndNo;

	/* sex_cd 성별_코드 character varying(20) */
	private String sexCd;

	/* age_ycnt 나이_년수 numeric(null) */
	private double ageYcnt;

	/* age_mcnt 나이_개월수 numeric(null) */
	private double ageMcnt;

	/* p3_gidx 백분위3_성장지수 numeric(null) */
	private double p3Gidx;

	/* p50_gidx 백분위50_성장지수 numeric(null) */
	private double p50Gidx;

	/* p97_gidx 백분위97_성장지수 numeric(null) */
	private double p97Gidx;

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
	 * Get grow_stnd_ver 성장_기준_버전 character varying(500)
	 * @Return String growStndVer
	 */
	public String getGrowStndVer() {
		return this.growStndVer;
	}
	
	/**
	 * Set grow_stnd_ver 성장_기준_버전 character varying(500)
	 * @Param String growStndVer
	 */
	public void setGrowStndVer(String growStndVer) {
		this.growStndVer = growStndVer;
	}
	/**
	 * Get grow_stnd_no 성장_기준_번호 numeric(null)
	 * @Return double growStndNo
	 */
	public double getGrowStndNo() {
		return this.growStndNo;
	}
	
	/**
	 * Set grow_stnd_no 성장_기준_번호 numeric(null)
	 * @Param double growStndNo
	 */
	public void setGrowStndNo(double growStndNo) {
		this.growStndNo = growStndNo;
	}
	/**
	 * Get sex_cd 성별_코드 character varying(20)
	 * @Return String sexCd
	 */
	public String getSexCd() {
		return this.sexCd;
	}
	
	/**
	 * Set sex_cd 성별_코드 character varying(20)
	 * @Param String sexCd
	 */
	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}
	/**
	 * Get age_ycnt 나이_년수 numeric(null)
	 * @Return double ageYcnt
	 */
	public double getAgeYcnt() {
		return this.ageYcnt;
	}
	
	/**
	 * Set age_ycnt 나이_년수 numeric(null)
	 * @Param double ageYcnt
	 */
	public void setAgeYcnt(double ageYcnt) {
		this.ageYcnt = ageYcnt;
	}
	/**
	 * Get age_mcnt 나이_개월수 numeric(null)
	 * @Return double ageMcnt
	 */
	public double getAgeMcnt() {
		return this.ageMcnt;
	}
	
	/**
	 * Set age_mcnt 나이_개월수 numeric(null)
	 * @Param double ageMcnt
	 */
	public void setAgeMcnt(double ageMcnt) {
		this.ageMcnt = ageMcnt;
	}
	/**
	 * Get p3_gidx 백분위3_성장지수 numeric(null)
	 * @Return double p3Gidx
	 */
	public double getP3Gidx() {
		return this.p3Gidx;
	}
	
	/**
	 * Set p3_gidx 백분위3_성장지수 numeric(null)
	 * @Param double p3Gidx
	 */
	public void setP3Gidx(double p3Gidx) {
		this.p3Gidx = p3Gidx;
	}
	/**
	 * Get p50_gidx 백분위50_성장지수 numeric(null)
	 * @Return double p50Gidx
	 */
	public double getP50Gidx() {
		return this.p50Gidx;
	}
	
	/**
	 * Set p50_gidx 백분위50_성장지수 numeric(null)
	 * @Param double p50Gidx
	 */
	public void setP50Gidx(double p50Gidx) {
		this.p50Gidx = p50Gidx;
	}
	/**
	 * Get p97_gidx 백분위97_성장지수 numeric(null)
	 * @Return double p97Gidx
	 */
	public double getP97Gidx() {
		return this.p97Gidx;
	}
	
	/**
	 * Set p97_gidx 백분위97_성장지수 numeric(null)
	 * @Param double p97Gidx
	 */
	public void setP97Gidx(double p97Gidx) {
		this.p97Gidx = p97Gidx;
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
