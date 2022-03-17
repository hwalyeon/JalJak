package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * tm_guar_base Value Object
 */
public class TmGuarBaseVo implements Serializable {

	/* guar_no 보호자_번호 numeric(null) */
	private double guarNo;

	/* guar_nm 보호자_명 character varying(40) */
	private String guarNm;

	/* guar_tel_no 보호자_전화_번호 character varying(20) */
	private String guarTelNo;

	/* guar_pw 보호자_비밀번호 character varying(400) */
	private String guarPw;

	/* self_cert_dttm 본인_인증_일시 character(14) */
	private String selfCertDttm;

	/* auto_login_yn 자동_로그인_여부 character(1) */
	private String autoLoginYn;

	/* devc_cert_val 장치_인증_값 character varying(1000) */
	private String devcCertVal;

	/* race_div_cd 인종_구분_코드 character varying(20) */
	private String raceDivCd;

	/* sex_cd 성별_코드 character varying(20) */
	private String sexCd;

	/* hght_val 키_값 numeric(null) */
	private double hghtVal;

	/* wght_val 체중_값 numeric(null) */
	private double wghtVal;

	/* bmi_val BMI_값 numeric(null) */
	private double bmiVal;

	/* spos_no 배우자_번호 numeric(null) */
	private double sposNo;

	/* dzone_alam_yn 위험지역_알림_여부 character(1) */
	private String dzoneAlamYn;

	/* szone_alam_yn 세 이프존_알림_여부 character(1) */
	private String szoneAlamYn;

	/* fall_alam_yn 낙상_알림_여부 character(1) */
	private String fallAlamYn;

	/* hbit_abnm_alam_yn 심박_이상_알림_여부 character(1) */
	private String hbitAbnmAlamYn;

	/* temp_abnm_alam_yn 체온_이상_알림_여부 character(1) */
	private String tempAbnmAlamYn;

	/* body_hist_alam_yn 신체_기록_알림_여부 character(1) */
	private String bodyHistAlamYn;

	/* meal_noin_alam_yn 식사_미입력_알림_여부 character(1) */
	private String mealNoinAlamYn;

	/* excs_noin_alam_yn 운동_미입력_알림_여부 character(1) */
	private String excsNoinAlamYn;

	/* cbee_use_alam_yn 캐시비_사용_알림_여부 character(1) */
	private String cbeeUseAlamYn;

	/* batr_lack_alam_yn 배터리_부족_알림_여부 character(1) */
	private String batrLackAlamYn;

	/* ltrm_nuse_alam_yn 장기_미사용_알림_여부 character(1) */
	private String ltrmNuseAlamYn;

	/* entr_dt 가입_일자 character(8) */
	private String entrDt;

	/* rels_dt 해지_일자 character(8) */
	private String relsDt;

	/* rels_resn_cd 해지_사유_코드 character varying(20) */
	private String relsResnCd;

	/* rels_resn_cntn 해지_사유_내용 character varying(4000) */
	private String relsResnCntn;

	/* entr_stat_cd 가입_상태_코드 character varying(20) */
	private String entrStatCd;

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
	 * Get guar_nm 보호자_명 character varying(40)
	 * @Return String guarNm
	 */
	public String getGuarNm() {
		return this.guarNm;
	}
	
	/**
	 * Set guar_nm 보호자_명 character varying(40)
	 * @Param String guarNm
	 */
	public void setGuarNm(String guarNm) {
		this.guarNm = guarNm;
	}
	/**
	 * Get guar_tel_no 보호자_전화_번호 character varying(20)
	 * @Return String guarTelNo
	 */
	public String getGuarTelNo() {
		return this.guarTelNo;
	}
	
	/**
	 * Set guar_tel_no 보호자_전화_번호 character varying(20)
	 * @Param String guarTelNo
	 */
	public void setGuarTelNo(String guarTelNo) {
		this.guarTelNo = guarTelNo;
	}
	/**
	 * Get guar_pw 보호자_비밀번호 character varying(400)
	 * @Return String guarPw
	 */
	public String getGuarPw() {
		return this.guarPw;
	}
	
	/**
	 * Set guar_pw 보호자_비밀번호 character varying(400)
	 * @Param String guarPw
	 */
	public void setGuarPw(String guarPw) {
		this.guarPw = guarPw;
	}
	/**
	 * Get self_cert_dttm 본인_인증_일시 character(14)
	 * @Return String selfCertDttm
	 */
	public String getSelfCertDttm() {
		return this.selfCertDttm;
	}
	
	/**
	 * Set self_cert_dttm 본인_인증_일시 character(14)
	 * @Param String selfCertDttm
	 */
	public void setSelfCertDttm(String selfCertDttm) {
		this.selfCertDttm = selfCertDttm;
	}
	/**
	 * Get auto_login_yn 자동_로그인_여부 character(1)
	 * @Return String autoLoginYn
	 */
	public String getAutoLoginYn() {
		return this.autoLoginYn;
	}
	
	/**
	 * Set auto_login_yn 자동_로그인_여부 character(1)
	 * @Param String autoLoginYn
	 */
	public void setAutoLoginYn(String autoLoginYn) {
		this.autoLoginYn = autoLoginYn;
	}
	/**
	 * Get devc_cert_val 장치_인증_값 character varying(1000)
	 * @Return String devcCertVal
	 */
	public String getDevcCertVal() {
		return this.devcCertVal;
	}
	
	/**
	 * Set devc_cert_val 장치_인증_값 character varying(1000)
	 * @Param String devcCertVal
	 */
	public void setDevcCertVal(String devcCertVal) {
		this.devcCertVal = devcCertVal;
	}
	/**
	 * Get race_div_cd 인종_구분_코드 character varying(20)
	 * @Return String raceDivCd
	 */
	public String getRaceDivCd() {
		return this.raceDivCd;
	}
	
	/**
	 * Set race_div_cd 인종_구분_코드 character varying(20)
	 * @Param String raceDivCd
	 */
	public void setRaceDivCd(String raceDivCd) {
		this.raceDivCd = raceDivCd;
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
	 * Get hght_val 키_값 numeric(null)
	 * @Return double hghtVal
	 */
	public double getHghtVal() {
		return this.hghtVal;
	}
	
	/**
	 * Set hght_val 키_값 numeric(null)
	 * @Param double hghtVal
	 */
	public void setHghtVal(double hghtVal) {
		this.hghtVal = hghtVal;
	}
	/**
	 * Get wght_val 체중_값 numeric(null)
	 * @Return double wghtVal
	 */
	public double getWghtVal() {
		return this.wghtVal;
	}
	
	/**
	 * Set wght_val 체중_값 numeric(null)
	 * @Param double wghtVal
	 */
	public void setWghtVal(double wghtVal) {
		this.wghtVal = wghtVal;
	}
	/**
	 * Get bmi_val BMI_값 numeric(null)
	 * @Return double bmiVal
	 */
	public double getBmiVal() {
		return this.bmiVal;
	}
	
	/**
	 * Set bmi_val BMI_값 numeric(null)
	 * @Param double bmiVal
	 */
	public void setBmiVal(double bmiVal) {
		this.bmiVal = bmiVal;
	}
	/**
	 * Get spos_no 배우자_번호 numeric(null)
	 * @Return double sposNo
	 */
	public double getSposNo() {
		return this.sposNo;
	}
	
	/**
	 * Set spos_no 배우자_번호 numeric(null)
	 * @Param double sposNo
	 */
	public void setSposNo(double sposNo) {
		this.sposNo = sposNo;
	}
	/**
	 * Get dzone_alam_yn 위험지역_알림_여부 character(1)
	 * @Return String dzoneAlamYn
	 */
	public String getDzoneAlamYn() {
		return this.dzoneAlamYn;
	}
	
	/**
	 * Set dzone_alam_yn 위험지역_알림_여부 character(1)
	 * @Param String dzoneAlamYn
	 */
	public void setDzoneAlamYn(String dzoneAlamYn) {
		this.dzoneAlamYn = dzoneAlamYn;
	}
	/**
	 * Get szone_alam_yn 세이프존_알림_여부 character(1)
	 * @Return String szoneAlamYn
	 */
	public String getSzoneAlamYn() {
		return this.szoneAlamYn;
	}
	
	/**
	 * Set szone_alam_yn 세이프존_알림_여부 character(1)
	 * @Param String szoneAlamYn
	 */
	public void setSzoneAlamYn(String szoneAlamYn) {
		this.szoneAlamYn = szoneAlamYn;
	}
	/**
	 * Get fall_alam_yn 낙상_알림_여부 character(1)
	 * @Return String fallAlamYn
	 */
	public String getFallAlamYn() {
		return this.fallAlamYn;
	}
	
	/**
	 * Set fall_alam_yn 낙상_알림_여부 character(1)
	 * @Param String fallAlamYn
	 */
	public void setFallAlamYn(String fallAlamYn) {
		this.fallAlamYn = fallAlamYn;
	}
	/**
	 * Get hbit_abnm_alam_yn 심박_이상_알림_여부 character(1)
	 * @Return String hbitAbnmAlamYn
	 */
	public String getHbitAbnmAlamYn() {
		return this.hbitAbnmAlamYn;
	}
	
	/**
	 * Set hbit_abnm_alam_yn 심박_이상_알림_여부 character(1)
	 * @Param String hbitAbnmAlamYn
	 */
	public void setHbitAbnmAlamYn(String hbitAbnmAlamYn) {
		this.hbitAbnmAlamYn = hbitAbnmAlamYn;
	}
	/**
	 * Get temp_abnm_alam_yn 체온_이상_알림_여부 character(1)
	 * @Return String tempAbnmAlamYn
	 */
	public String getTempAbnmAlamYn() {
		return this.tempAbnmAlamYn;
	}
	
	/**
	 * Set temp_abnm_alam_yn 체온_이상_알림_여부 character(1)
	 * @Param String tempAbnmAlamYn
	 */
	public void setTempAbnmAlamYn(String tempAbnmAlamYn) {
		this.tempAbnmAlamYn = tempAbnmAlamYn;
	}
	/**
	 * Get body_hist_alam_yn 신체_기록_알림_여부 character(1)
	 * @Return String bodyHistAlamYn
	 */
	public String getBodyHistAlamYn() {
		return this.bodyHistAlamYn;
	}
	
	/**
	 * Set body_hist_alam_yn 신체_기록_알림_여부 character(1)
	 * @Param String bodyHistAlamYn
	 */
	public void setBodyHistAlamYn(String bodyHistAlamYn) {
		this.bodyHistAlamYn = bodyHistAlamYn;
	}
	/**
	 * Get meal_noin_alam_yn 식사_미입력_알림_여부 character(1)
	 * @Return String mealNoinAlamYn
	 */
	public String getMealNoinAlamYn() {
		return this.mealNoinAlamYn;
	}
	
	/**
	 * Set meal_noin_alam_yn 식사_미입력_알림_여부 character(1)
	 * @Param String mealNoinAlamYn
	 */
	public void setMealNoinAlamYn(String mealNoinAlamYn) {
		this.mealNoinAlamYn = mealNoinAlamYn;
	}
	/**
	 * Get excs_noin_alam_yn 운동_미입력_알림_여부 character(1)
	 * @Return String excsNoinAlamYn
	 */
	public String getExcsNoinAlamYn() {
		return this.excsNoinAlamYn;
	}
	
	/**
	 * Set excs_noin_alam_yn 운동_미입력_알림_여부 character(1)
	 * @Param String excsNoinAlamYn
	 */
	public void setExcsNoinAlamYn(String excsNoinAlamYn) {
		this.excsNoinAlamYn = excsNoinAlamYn;
	}
	/**
	 * Get cbee_use_alam_yn 캐시비_사용_알림_여부 character(1)
	 * @Return String cbeeUseAlamYn
	 */
	public String getCbeeUseAlamYn() {
		return this.cbeeUseAlamYn;
	}
	
	/**
	 * Set cbee_use_alam_yn 캐시비_사용_알림_여부 character(1)
	 * @Param String cbeeUseAlamYn
	 */
	public void setCbeeUseAlamYn(String cbeeUseAlamYn) {
		this.cbeeUseAlamYn = cbeeUseAlamYn;
	}
	/**
	 * Get batr_lack_alam_yn 배터리_부족_알림_여부 character(1)
	 * @Return String batrLackAlamYn
	 */
	public String getBatrLackAlamYn() {
		return this.batrLackAlamYn;
	}
	
	/**
	 * Set batr_lack_alam_yn 배터리_부족_알림_여부 character(1)
	 * @Param String batrLackAlamYn
	 */
	public void setBatrLackAlamYn(String batrLackAlamYn) {
		this.batrLackAlamYn = batrLackAlamYn;
	}
	/**
	 * Get ltrm_nuse_alam_yn 장기_미사용_알림_여부 character(1)
	 * @Return String ltrmNuseAlamYn
	 */
	public String getLtrmNuseAlamYn() {
		return this.ltrmNuseAlamYn;
	}
	
	/**
	 * Set ltrm_nuse_alam_yn 장기_미사용_알림_여부 character(1)
	 * @Param String ltrmNuseAlamYn
	 */
	public void setLtrmNuseAlamYn(String ltrmNuseAlamYn) {
		this.ltrmNuseAlamYn = ltrmNuseAlamYn;
	}
	/**
	 * Get entr_dt 가입_일자 character(8)
	 * @Return String entrDt
	 */
	public String getEntrDt() {
		return this.entrDt;
	}
	
	/**
	 * Set entr_dt 가입_일자 character(8)
	 * @Param String entrDt
	 */
	public void setEntrDt(String entrDt) {
		this.entrDt = entrDt;
	}
	/**
	 * Get rels_dt 해지_일자 character(8)
	 * @Return String relsDt
	 */
	public String getRelsDt() {
		return this.relsDt;
	}
	
	/**
	 * Set rels_dt 해지_일자 character(8)
	 * @Param String relsDt
	 */
	public void setRelsDt(String relsDt) {
		this.relsDt = relsDt;
	}
	/**
	 * Get rels_resn_cd 해지_사유_코드 character varying(20)
	 * @Return String relsResnCd
	 */
	public String getRelsResnCd() {
		return this.relsResnCd;
	}
	
	/**
	 * Set rels_resn_cd 해지_사유_코드 character varying(20)
	 * @Param String relsResnCd
	 */
	public void setRelsResnCd(String relsResnCd) {
		this.relsResnCd = relsResnCd;
	}
	/**
	 * Get rels_resn_cntn 해지_사유_내용 character varying(4000)
	 * @Return String relsResnCntn
	 */
	public String getRelsResnCntn() {
		return this.relsResnCntn;
	}
	
	/**
	 * Set rels_resn_cntn 해지_사유_내용 character varying(4000)
	 * @Param String relsResnCntn
	 */
	public void setRelsResnCntn(String relsResnCntn) {
		this.relsResnCntn = relsResnCntn;
	}
	/**
	 * Get entr_stat_cd 가입_상태_코드 character varying(20)
	 * @Return String entrStatCd
	 */
	public String getEntrStatCd() {
		return this.entrStatCd;
	}
	
	/**
	 * Set entr_stat_cd 가입_상태_코드 character varying(20)
	 * @Param String entrStatCd
	 */
	public void setEntrStatCd(String entrStatCd) {
		this.entrStatCd = entrStatCd;
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
