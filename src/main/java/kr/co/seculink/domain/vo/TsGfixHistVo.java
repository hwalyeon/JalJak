package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ts_gfix_hist Value Object
 */
public class TsGfixHistVo implements Serializable {

	/* stdt_no 학생_번호 numeric(null) */
	private double stdtNo;

	/* gfix_dt 성장비만지수_일자 character(8) */
	private String gfixDt;

	/* sex_cd 성별_코드 character varying(20) */
	private String sexCd;

	/* age_ycnt 나이_년수 numeric(null) */
	private double ageYcnt;

	/* age_mcnt 나이_월수 numeric(null) */
	private double ageMcnt;

	/* hght_val 키_값 numeric(null) */
	private double hghtVal;

	/* wght_val 몸무게_값 numeric(null) */
	private double wghtVal;

	/* bmi_val BMI_값 numeric(null) */
	private double bmiVal;

	/* wast_size 허리_사이즈 numeric(null) */
	private double wastSize;

	/* cal_eat_qty 칼로리_섭취_량 numeric(null) */
	private double calEatQty;

	/* cal_csum_qty 칼로리_소모_량 numeric(null) */
	private double calCsumQty;

	/* guar_hght_val_1 보호자_키_값_1 numeric(null) */
	private double guarHghtVal1;

	/* guar_wght_val_1 보호자_몸무게_값_1 numeric(null) */
	private double guarWghtVal1;

	/* guar_bmi_val_1 보호자_BMI_값_1 numeric(null) */
	private double guarBmiVal1;

	/* guar_hght_val_2 보호자_키_값_2 numeric(null) */
	private double guarHghtVal2;

	/* guar_wght_val_2 보호자_몸무게_값_2 numeric(null) */
	private double guarWghtVal2;

	/* guar_bmi_val_2 보호자_BMI_값_2 numeric(null) */
	private double guarBmiVal2;

	/* grow_stnd_ver 성장_기준_버전 character varying(20) */
	private String growStndVer;

	/* grow_stnd_no 성장_기준_번호 numeric(null) */
	private double growStndNo;

	/* grow_idx 성장_지수 numeric(null) */
	private double growIdx;

	/* grow_judg_cd 성장_판정_코드 character varying(20) */
	private String growJudgCd;

	/* grow_judg_desc 성장_판정_설명 character varying(4000) */
	private String growJudgDesc;

	/* grow_prdt_idx 성장_예측_지수 numeric(null) */
	private double growPrdtIdx;

	/* grow_prdt_cd 성장_예측_코드 character varying(20) */
	private String growPrdtCd;

	/* grow_prdt_desc 성장_예측_설명 character varying(4000) */
	private String growPrdtDesc;

	/* fat_stnd_ver 비만_기준_버전 character varying(20) */
	private String fatStndVer;

	/* fat_stnd_no 비만_기준_번호 numeric(null) */
	private double fatStndNo;

	/* fat_idx 비만_지수 numeric(null) */
	private double fatIdx;

	/* fat_judg_cd 비만_판정_코드 character varying(20) */
	private String fatJudgCd;

	/* fat_judg_desc 비만_판정_설명 character varying(4000) */
	private String fatJudgDesc;

	/* prdt_bmi_val 예측_BMI_값 numeric(null) */
	private double prdtBmiVal;

	/* prdt_fat_idx 예측_비만_지수 numeric(null) */
	private double prdtFatIdx;

	/* prdt_fat_judg_cd 예측_비만_판정_코드 character varying(20) */
	private String prdtFatJudgCd;

	/* prdt_fat_judg_desc 예측_비만_판정_설명 character varying(4000) */
	private String prdtFatJudgDesc;

	/* pal_val 신체활동수준_값 numeric(null) */
	private double palVal;

	/* rmrk 비고 character varying(4000) */
	private String rmrk;

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
	 * Get gfix_dt 성장비만지수_일자 character(8)
	 * @Return String gfixDt
	 */
	public String getGfixDt() {
		return this.gfixDt;
	}
	
	/**
	 * Set gfix_dt 성장비만지수_일자 character(8)
	 * @Param String gfixDt
	 */
	public void setGfixDt(String gfixDt) {
		this.gfixDt = gfixDt;
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
	 * Get age_mcnt 나이_월수 numeric(null)
	 * @Return double ageMcnt
	 */
	public double getAgeMcnt() {
		return this.ageMcnt;
	}
	
	/**
	 * Set age_mcnt 나이_월수 numeric(null)
	 * @Param double ageMcnt
	 */
	public void setAgeMcnt(double ageMcnt) {
		this.ageMcnt = ageMcnt;
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
	 * Get wght_val 몸무게_값 numeric(null)
	 * @Return double wghtVal
	 */
	public double getWghtVal() {
		return this.wghtVal;
	}
	
	/**
	 * Set wght_val 몸무게_값 numeric(null)
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
	 * Get wast_size 허리_사이즈 numeric(null)
	 * @Return double wastSize
	 */
	public double getWastSize() {
		return this.wastSize;
	}
	
	/**
	 * Set wast_size 허리_사이즈 numeric(null)
	 * @Param double wastSize
	 */
	public void setWastSize(double wastSize) {
		this.wastSize = wastSize;
	}
	/**
	 * Get cal_eat_qty 칼로리_섭취_량 numeric(null)
	 * @Return double calEatQty
	 */
	public double getCalEatQty() {
		return this.calEatQty;
	}
	
	/**
	 * Set cal_eat_qty 칼로리_섭취_량 numeric(null)
	 * @Param double calEatQty
	 */
	public void setCalEatQty(double calEatQty) {
		this.calEatQty = calEatQty;
	}
	/**
	 * Get cal_csum_qty 칼로리_소모_량 numeric(null)
	 * @Return double calCsumQty
	 */
	public double getCalCsumQty() {
		return this.calCsumQty;
	}
	
	/**
	 * Set cal_csum_qty 칼로리_소모_량 numeric(null)
	 * @Param double calCsumQty
	 */
	public void setCalCsumQty(double calCsumQty) {
		this.calCsumQty = calCsumQty;
	}
	/**
	 * Get guar_hght_val_1 보호자_키_값_1 numeric(null)
	 * @Return double guarHghtVal1
	 */
	public double getGuarHghtVal1() {
		return this.guarHghtVal1;
	}
	
	/**
	 * Set guar_hght_val_1 보호자_키_값_1 numeric(null)
	 * @Param double guarHghtVal1
	 */
	public void setGuarHghtVal1(double guarHghtVal1) {
		this.guarHghtVal1 = guarHghtVal1;
	}
	/**
	 * Get guar_wght_val_1 보호자_몸무게_값_1 numeric(null)
	 * @Return double guarWghtVal1
	 */
	public double getGuarWghtVal1() {
		return this.guarWghtVal1;
	}
	
	/**
	 * Set guar_wght_val_1 보호자_몸무게_값_1 numeric(null)
	 * @Param double guarWghtVal1
	 */
	public void setGuarWghtVal1(double guarWghtVal1) {
		this.guarWghtVal1 = guarWghtVal1;
	}
	/**
	 * Get guar_bmi_val_1 보호자_BMI_값_1 numeric(null)
	 * @Return double guarBmiVal1
	 */
	public double getGuarBmiVal1() {
		return this.guarBmiVal1;
	}
	
	/**
	 * Set guar_bmi_val_1 보호자_BMI_값_1 numeric(null)
	 * @Param double guarBmiVal1
	 */
	public void setGuarBmiVal1(double guarBmiVal1) {
		this.guarBmiVal1 = guarBmiVal1;
	}
	/**
	 * Get guar_hght_val_2 보호자_키_값_2 numeric(null)
	 * @Return double guarHghtVal2
	 */
	public double getGuarHghtVal2() {
		return this.guarHghtVal2;
	}
	
	/**
	 * Set guar_hght_val_2 보호자_키_값_2 numeric(null)
	 * @Param double guarHghtVal2
	 */
	public void setGuarHghtVal2(double guarHghtVal2) {
		this.guarHghtVal2 = guarHghtVal2;
	}
	/**
	 * Get guar_wght_val_2 보호자_몸무게_값_2 numeric(null)
	 * @Return double guarWghtVal2
	 */
	public double getGuarWghtVal2() {
		return this.guarWghtVal2;
	}
	
	/**
	 * Set guar_wght_val_2 보호자_몸무게_값_2 numeric(null)
	 * @Param double guarWghtVal2
	 */
	public void setGuarWghtVal2(double guarWghtVal2) {
		this.guarWghtVal2 = guarWghtVal2;
	}
	/**
	 * Get guar_bmi_val_2 보호자_BMI_값_2 numeric(null)
	 * @Return double guarBmiVal2
	 */
	public double getGuarBmiVal2() {
		return this.guarBmiVal2;
	}
	
	/**
	 * Set guar_bmi_val_2 보호자_BMI_값_2 numeric(null)
	 * @Param double guarBmiVal2
	 */
	public void setGuarBmiVal2(double guarBmiVal2) {
		this.guarBmiVal2 = guarBmiVal2;
	}
	/**
	 * Get grow_stnd_ver 성장_기준_버전 character varying(20)
	 * @Return String growStndVer
	 */
	public String getGrowStndVer() {
		return this.growStndVer;
	}
	
	/**
	 * Set grow_stnd_ver 성장_기준_버전 character varying(20)
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
	 * Get grow_idx 성장_지수 numeric(null)
	 * @Return double growIdx
	 */
	public double getGrowIdx() {
		return this.growIdx;
	}
	
	/**
	 * Set grow_idx 성장_지수 numeric(null)
	 * @Param double growIdx
	 */
	public void setGrowIdx(double growIdx) {
		this.growIdx = growIdx;
	}
	/**
	 * Get grow_judg_cd 성장_판정_코드 character varying(20)
	 * @Return String growJudgCd
	 */
	public String getGrowJudgCd() {
		return this.growJudgCd;
	}
	
	/**
	 * Set grow_judg_cd 성장_판정_코드 character varying(20)
	 * @Param String growJudgCd
	 */
	public void setGrowJudgCd(String growJudgCd) {
		this.growJudgCd = growJudgCd;
	}
	/**
	 * Get grow_judg_desc 성장_판정_설명 character varying(4000)
	 * @Return String growJudgDesc
	 */
	public String getGrowJudgDesc() {
		return this.growJudgDesc;
	}
	
	/**
	 * Set grow_judg_desc 성장_판정_설명 character varying(4000)
	 * @Param String growJudgDesc
	 */
	public void setGrowJudgDesc(String growJudgDesc) {
		this.growJudgDesc = growJudgDesc;
	}
	/**
	 * Get grow_prdt_idx 성장_예측_지수 numeric(null)
	 * @Return double growPrdtIdx
	 */
	public double getGrowPrdtIdx() {
		return this.growPrdtIdx;
	}
	
	/**
	 * Set grow_prdt_idx 성장_예측_지수 numeric(null)
	 * @Param double growPrdtIdx
	 */
	public void setGrowPrdtIdx(double growPrdtIdx) {
		this.growPrdtIdx = growPrdtIdx;
	}
	/**
	 * Get grow_prdt_cd 성장_예측_코드 character varying(20)
	 * @Return String growPrdtCd
	 */
	public String getGrowPrdtCd() {
		return this.growPrdtCd;
	}
	
	/**
	 * Set grow_prdt_cd 성장_예측_코드 character varying(20)
	 * @Param String growPrdtCd
	 */
	public void setGrowPrdtCd(String growPrdtCd) {
		this.growPrdtCd = growPrdtCd;
	}
	/**
	 * Get grow_prdt_desc 성장_예측_설명 character varying(4000)
	 * @Return String growPrdtDesc
	 */
	public String getGrowPrdtDesc() {
		return this.growPrdtDesc;
	}
	
	/**
	 * Set grow_prdt_desc 성장_예측_설명 character varying(4000)
	 * @Param String growPrdtDesc
	 */
	public void setGrowPrdtDesc(String growPrdtDesc) {
		this.growPrdtDesc = growPrdtDesc;
	}
	/**
	 * Get fat_stnd_ver 비만_기준_버전 character varying(20)
	 * @Return String fatStndVer
	 */
	public String getFatStndVer() {
		return this.fatStndVer;
	}
	
	/**
	 * Set fat_stnd_ver 비만_기준_버전 character varying(20)
	 * @Param String fatStndVer
	 */
	public void setFatStndVer(String fatStndVer) {
		this.fatStndVer = fatStndVer;
	}
	/**
	 * Get fat_stnd_no 비만_기준_번호 numeric(null)
	 * @Return double fatStndNo
	 */
	public double getFatStndNo() {
		return this.fatStndNo;
	}
	
	/**
	 * Set fat_stnd_no 비만_기준_번호 numeric(null)
	 * @Param double fatStndNo
	 */
	public void setFatStndNo(double fatStndNo) {
		this.fatStndNo = fatStndNo;
	}
	/**
	 * Get fat_idx 비만_지수 numeric(null)
	 * @Return double fatIdx
	 */
	public double getFatIdx() {
		return this.fatIdx;
	}
	
	/**
	 * Set fat_idx 비만_지수 numeric(null)
	 * @Param double fatIdx
	 */
	public void setFatIdx(double fatIdx) {
		this.fatIdx = fatIdx;
	}
	/**
	 * Get fat_judg_cd 비만_판정_코드 character varying(20)
	 * @Return String fatJudgCd
	 */
	public String getFatJudgCd() {
		return this.fatJudgCd;
	}
	
	/**
	 * Set fat_judg_cd 비만_판정_코드 character varying(20)
	 * @Param String fatJudgCd
	 */
	public void setFatJudgCd(String fatJudgCd) {
		this.fatJudgCd = fatJudgCd;
	}
	/**
	 * Get fat_judg_desc 비만_판정_설명 character varying(4000)
	 * @Return String fatJudgDesc
	 */
	public String getFatJudgDesc() {
		return this.fatJudgDesc;
	}
	
	/**
	 * Set fat_judg_desc 비만_판정_설명 character varying(4000)
	 * @Param String fatJudgDesc
	 */
	public void setFatJudgDesc(String fatJudgDesc) {
		this.fatJudgDesc = fatJudgDesc;
	}
	/**
	 * Get prdt_bmi_val 예측_BMI_값 numeric(null)
	 * @Return double prdtBmiVal
	 */
	public double getPrdtBmiVal() {
		return this.prdtBmiVal;
	}
	
	/**
	 * Set prdt_bmi_val 예측_BMI_값 numeric(null)
	 * @Param double prdtBmiVal
	 */
	public void setPrdtBmiVal(double prdtBmiVal) {
		this.prdtBmiVal = prdtBmiVal;
	}
	/**
	 * Get prdt_fat_idx 예측_비만_지수 numeric(null)
	 * @Return double prdtFatIdx
	 */
	public double getPrdtFatIdx() {
		return this.prdtFatIdx;
	}
	
	/**
	 * Set prdt_fat_idx 예측_비만_지수 numeric(null)
	 * @Param double prdtFatIdx
	 */
	public void setPrdtFatIdx(double prdtFatIdx) {
		this.prdtFatIdx = prdtFatIdx;
	}
	/**
	 * Get prdt_fat_judg_cd 예측_비만_판정_코드 character varying(20)
	 * @Return String prdtFatJudgCd
	 */
	public String getPrdtFatJudgCd() {
		return this.prdtFatJudgCd;
	}
	
	/**
	 * Set prdt_fat_judg_cd 예측_비만_판정_코드 character varying(20)
	 * @Param String prdtFatJudgCd
	 */
	public void setPrdtFatJudgCd(String prdtFatJudgCd) {
		this.prdtFatJudgCd = prdtFatJudgCd;
	}
	/**
	 * Get prdt_fat_judg_desc 예측_비만_판정_설명 character varying(4000)
	 * @Return String prdtFatJudgDesc
	 */
	public String getPrdtFatJudgDesc() {
		return this.prdtFatJudgDesc;
	}
	
	/**
	 * Set prdt_fat_judg_desc 예측_비만_판정_설명 character varying(4000)
	 * @Param String prdtFatJudgDesc
	 */
	public void setPrdtFatJudgDesc(String prdtFatJudgDesc) {
		this.prdtFatJudgDesc = prdtFatJudgDesc;
	}
	/**
	 * Get pal_val 신체활동수준_값 numeric(null)
	 * @Return double palVal
	 */
	public double getPalVal() {
		return this.palVal;
	}
	
	/**
	 * Set pal_val 신체활동수준_값 numeric(null)
	 * @Param double palVal
	 */
	public void setPalVal(double palVal) {
		this.palVal = palVal;
	}
	/**
	 * Get rmrk 비고 character varying(4000)
	 * @Return String rmrk
	 */
	public String getRmrk() {
		return this.rmrk;
	}
	
	/**
	 * Set rmrk 비고 character varying(4000)
	 * @Param String rmrk
	 */
	public void setRmrk(String rmrk) {
		this.rmrk = rmrk;
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
