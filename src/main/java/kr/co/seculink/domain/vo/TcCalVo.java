package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * tc_cal Value Object
 */
public class TcCalVo implements Serializable {

	/* regi_no 등록_번호 numeric(null) */
	private double regiNo;

	/* stnd_dt 기준_일자 character(8) */
	private String stndDt;

	/* kor_year 단기_년도 character(4) */
	private String korYear;

	/* stnd_year 기준_년도 character(4) */
	private String stndYear;

	/* stnd_mnth 기준_월 character(2) */
	private String stndMnth;

	/* stnd_day 기준_일 character(2) */
	private String stndDay;

	/* moon_dt 음력_일자 character(8) */
	private String moonDt;

	/* moon_year 음력_년도 character(4) */
	private String moonYear;

	/* moon_mnth 음력_월 character(2) */
	private String moonMnth;

	/* moon_day 음력_일 character(2) */
	private String moonDay;

	/* year_ganji_chn 년도_간지_한자 character varying(20) */
	private String yearGanjiChn;

	/* year_ganji_kor 년도_간지_한글 character varying(20) */
	private String yearGanjiKor;

	/* day_ganji_chn 일_간지_한자 character varying(20) */
	private String dayGanjiChn;

	/* day_ganji_kor 일_간지_한글 character varying(20) */
	private String dayGanjiKor;

	/* term_chn 절기_한자 character varying(20) */
	private String termChn;

	/* term_kor 절기_한글 character varying(20) */
	private String termKor;

	/* week_nm 요일_명 character varying(10) */
	private String weekNm;

	/* scal_evnt 양력_행사 character varying(40) */
	private String scalEvnt;

	/* moon_evnt 음력_행사 character varying(40) */
	private String moonEvnt;

	/* dday_nm 복날_명 character varying(10) */
	private String ddayNm;

	/* ddi_nm 띠_명 character varying(10) */
	private String ddiNm;

	/* leap_mnth_yn 윤달_여부 character(1) */
	private String leapMnthYn;

	/* anni_div_cd 기념일_구분_코드 character varying(20) */
	private String anniDivCd;

	/* holiday_yn 휴일_여부 character(1) */
	private String holidayYn;

	/* year_week_seq 년별_주차 numeric(null) */
	private double yearWeekSeq;

	/* mnth_week_seq 월별_주차 numeric(null) */
	private double mnthWeekSeq;

	/* week_seq 주차 numeric(null) */
	private double weekSeq;

	/* regi_dt 등록_일자 character(8) */
	private String regiDt;

	/* regi_tm 등록_시각 character(6) */
	private String regiTm;

	/* regi_user_id 등록_사용자_ID character varying(20) */
	private String regiUserId;

	/* updt_dt 수정_일자 character(8) */
	private String updtDt;

	/* updt_tm 수정_시각 character(6) */
	private String updtTm;

	/* updt_user_id 수정_사용자_ID character varying(20) */
	private String updtUserId;


	/**
	 * Get regi_no 등록_번호 numeric(null)
	 * @Return double regiNo
	 */
	public double getRegiNo() {
		return this.regiNo;
	}
	
	/**
	 * Set regi_no 등록_번호 numeric(null)
	 * @Param double regiNo
	 */
	public void setRegiNo(double regiNo) {
		this.regiNo = regiNo;
	}
	/**
	 * Get stnd_dt 기준_일자 character(8)
	 * @Return String stndDt
	 */
	public String getStndDt() {
		return this.stndDt;
	}
	
	/**
	 * Set stnd_dt 기준_일자 character(8)
	 * @Param String stndDt
	 */
	public void setStndDt(String stndDt) {
		this.stndDt = stndDt;
	}
	/**
	 * Get kor_year 단기_년도 character(4)
	 * @Return String korYear
	 */
	public String getKorYear() {
		return this.korYear;
	}
	
	/**
	 * Set kor_year 단기_년도 character(4)
	 * @Param String korYear
	 */
	public void setKorYear(String korYear) {
		this.korYear = korYear;
	}
	/**
	 * Get stnd_year 기준_년도 character(4)
	 * @Return String stndYear
	 */
	public String getStndYear() {
		return this.stndYear;
	}
	
	/**
	 * Set stnd_year 기준_년도 character(4)
	 * @Param String stndYear
	 */
	public void setStndYear(String stndYear) {
		this.stndYear = stndYear;
	}
	/**
	 * Get stnd_mnth 기준_월 character(2)
	 * @Return String stndMnth
	 */
	public String getStndMnth() {
		return this.stndMnth;
	}
	
	/**
	 * Set stnd_mnth 기준_월 character(2)
	 * @Param String stndMnth
	 */
	public void setStndMnth(String stndMnth) {
		this.stndMnth = stndMnth;
	}
	/**
	 * Get stnd_day 기준_일 character(2)
	 * @Return String stndDay
	 */
	public String getStndDay() {
		return this.stndDay;
	}
	
	/**
	 * Set stnd_day 기준_일 character(2)
	 * @Param String stndDay
	 */
	public void setStndDay(String stndDay) {
		this.stndDay = stndDay;
	}
	/**
	 * Get moon_dt 음력_일자 character(8)
	 * @Return String moonDt
	 */
	public String getMoonDt() {
		return this.moonDt;
	}
	
	/**
	 * Set moon_dt 음력_일자 character(8)
	 * @Param String moonDt
	 */
	public void setMoonDt(String moonDt) {
		this.moonDt = moonDt;
	}
	/**
	 * Get moon_year 음력_년도 character(4)
	 * @Return String moonYear
	 */
	public String getMoonYear() {
		return this.moonYear;
	}
	
	/**
	 * Set moon_year 음력_년도 character(4)
	 * @Param String moonYear
	 */
	public void setMoonYear(String moonYear) {
		this.moonYear = moonYear;
	}
	/**
	 * Get moon_mnth 음력_월 character(2)
	 * @Return String moonMnth
	 */
	public String getMoonMnth() {
		return this.moonMnth;
	}
	
	/**
	 * Set moon_mnth 음력_월 character(2)
	 * @Param String moonMnth
	 */
	public void setMoonMnth(String moonMnth) {
		this.moonMnth = moonMnth;
	}
	/**
	 * Get moon_day 음력_일 character(2)
	 * @Return String moonDay
	 */
	public String getMoonDay() {
		return this.moonDay;
	}
	
	/**
	 * Set moon_day 음력_일 character(2)
	 * @Param String moonDay
	 */
	public void setMoonDay(String moonDay) {
		this.moonDay = moonDay;
	}
	/**
	 * Get year_ganji_chn 년도_간지_한자 character varying(20)
	 * @Return String yearGanjiChn
	 */
	public String getYearGanjiChn() {
		return this.yearGanjiChn;
	}
	
	/**
	 * Set year_ganji_chn 년도_간지_한자 character varying(20)
	 * @Param String yearGanjiChn
	 */
	public void setYearGanjiChn(String yearGanjiChn) {
		this.yearGanjiChn = yearGanjiChn;
	}
	/**
	 * Get year_ganji_kor 년도_간지_한글 character varying(20)
	 * @Return String yearGanjiKor
	 */
	public String getYearGanjiKor() {
		return this.yearGanjiKor;
	}
	
	/**
	 * Set year_ganji_kor 년도_간지_한글 character varying(20)
	 * @Param String yearGanjiKor
	 */
	public void setYearGanjiKor(String yearGanjiKor) {
		this.yearGanjiKor = yearGanjiKor;
	}
	/**
	 * Get day_ganji_chn 일_간지_한자 character varying(20)
	 * @Return String dayGanjiChn
	 */
	public String getDayGanjiChn() {
		return this.dayGanjiChn;
	}
	
	/**
	 * Set day_ganji_chn 일_간지_한자 character varying(20)
	 * @Param String dayGanjiChn
	 */
	public void setDayGanjiChn(String dayGanjiChn) {
		this.dayGanjiChn = dayGanjiChn;
	}
	/**
	 * Get day_ganji_kor 일_간지_한글 character varying(20)
	 * @Return String dayGanjiKor
	 */
	public String getDayGanjiKor() {
		return this.dayGanjiKor;
	}
	
	/**
	 * Set day_ganji_kor 일_간지_한글 character varying(20)
	 * @Param String dayGanjiKor
	 */
	public void setDayGanjiKor(String dayGanjiKor) {
		this.dayGanjiKor = dayGanjiKor;
	}
	/**
	 * Get term_chn 절기_한자 character varying(20)
	 * @Return String termChn
	 */
	public String getTermChn() {
		return this.termChn;
	}
	
	/**
	 * Set term_chn 절기_한자 character varying(20)
	 * @Param String termChn
	 */
	public void setTermChn(String termChn) {
		this.termChn = termChn;
	}
	/**
	 * Get term_kor 절기_한글 character varying(20)
	 * @Return String termKor
	 */
	public String getTermKor() {
		return this.termKor;
	}
	
	/**
	 * Set term_kor 절기_한글 character varying(20)
	 * @Param String termKor
	 */
	public void setTermKor(String termKor) {
		this.termKor = termKor;
	}
	/**
	 * Get week_nm 요일_명 character varying(10)
	 * @Return String weekNm
	 */
	public String getWeekNm() {
		return this.weekNm;
	}
	
	/**
	 * Set week_nm 요일_명 character varying(10)
	 * @Param String weekNm
	 */
	public void setWeekNm(String weekNm) {
		this.weekNm = weekNm;
	}
	/**
	 * Get scal_evnt 양력_행사 character varying(40)
	 * @Return String scalEvnt
	 */
	public String getScalEvnt() {
		return this.scalEvnt;
	}
	
	/**
	 * Set scal_evnt 양력_행사 character varying(40)
	 * @Param String scalEvnt
	 */
	public void setScalEvnt(String scalEvnt) {
		this.scalEvnt = scalEvnt;
	}
	/**
	 * Get moon_evnt 음력_행사 character varying(40)
	 * @Return String moonEvnt
	 */
	public String getMoonEvnt() {
		return this.moonEvnt;
	}
	
	/**
	 * Set moon_evnt 음력_행사 character varying(40)
	 * @Param String moonEvnt
	 */
	public void setMoonEvnt(String moonEvnt) {
		this.moonEvnt = moonEvnt;
	}
	/**
	 * Get dday_nm 복날_명 character varying(10)
	 * @Return String ddayNm
	 */
	public String getDdayNm() {
		return this.ddayNm;
	}
	
	/**
	 * Set dday_nm 복날_명 character varying(10)
	 * @Param String ddayNm
	 */
	public void setDdayNm(String ddayNm) {
		this.ddayNm = ddayNm;
	}
	/**
	 * Get ddi_nm 띠_명 character varying(10)
	 * @Return String ddiNm
	 */
	public String getDdiNm() {
		return this.ddiNm;
	}
	
	/**
	 * Set ddi_nm 띠_명 character varying(10)
	 * @Param String ddiNm
	 */
	public void setDdiNm(String ddiNm) {
		this.ddiNm = ddiNm;
	}
	/**
	 * Get leap_mnth_yn 윤달_여부 character(1)
	 * @Return String leapMnthYn
	 */
	public String getLeapMnthYn() {
		return this.leapMnthYn;
	}
	
	/**
	 * Set leap_mnth_yn 윤달_여부 character(1)
	 * @Param String leapMnthYn
	 */
	public void setLeapMnthYn(String leapMnthYn) {
		this.leapMnthYn = leapMnthYn;
	}
	/**
	 * Get anni_div_cd 기념일_구분_코드 character varying(20)
	 * @Return String anniDivCd
	 */
	public String getAnniDivCd() {
		return this.anniDivCd;
	}
	
	/**
	 * Set anni_div_cd 기념일_구분_코드 character varying(20)
	 * @Param String anniDivCd
	 */
	public void setAnniDivCd(String anniDivCd) {
		this.anniDivCd = anniDivCd;
	}
	/**
	 * Get holiday_yn 휴일_여부 character(1)
	 * @Return String holidayYn
	 */
	public String getHolidayYn() {
		return this.holidayYn;
	}
	
	/**
	 * Set holiday_yn 휴일_여부 character(1)
	 * @Param String holidayYn
	 */
	public void setHolidayYn(String holidayYn) {
		this.holidayYn = holidayYn;
	}
	/**
	 * Get year_week_seq 년별_주차 numeric(null)
	 * @Return double yearWeekSeq
	 */
	public double getYearWeekSeq() {
		return this.yearWeekSeq;
	}
	
	/**
	 * Set year_week_seq 년별_주차 numeric(null)
	 * @Param double yearWeekSeq
	 */
	public void setYearWeekSeq(double yearWeekSeq) {
		this.yearWeekSeq = yearWeekSeq;
	}
	/**
	 * Get mnth_week_seq 월별_주차 numeric(null)
	 * @Return double mnthWeekSeq
	 */
	public double getMnthWeekSeq() {
		return this.mnthWeekSeq;
	}
	
	/**
	 * Set mnth_week_seq 월별_주차 numeric(null)
	 * @Param double mnthWeekSeq
	 */
	public void setMnthWeekSeq(double mnthWeekSeq) {
		this.mnthWeekSeq = mnthWeekSeq;
	}
	/**
	 * Get week_seq 주차 numeric(null)
	 * @Return double weekSeq
	 */
	public double getWeekSeq() {
		return this.weekSeq;
	}
	
	/**
	 * Set week_seq 주차 numeric(null)
	 * @Param double weekSeq
	 */
	public void setWeekSeq(double weekSeq) {
		this.weekSeq = weekSeq;
	}
	/**
	 * Get regi_dt 등록_일자 character(8)
	 * @Return String regiDt
	 */
	public String getRegiDt() {
		return this.regiDt;
	}
	
	/**
	 * Set regi_dt 등록_일자 character(8)
	 * @Param String regiDt
	 */
	public void setRegiDt(String regiDt) {
		this.regiDt = regiDt;
	}
	/**
	 * Get regi_tm 등록_시각 character(6)
	 * @Return String regiTm
	 */
	public String getRegiTm() {
		return this.regiTm;
	}
	
	/**
	 * Set regi_tm 등록_시각 character(6)
	 * @Param String regiTm
	 */
	public void setRegiTm(String regiTm) {
		this.regiTm = regiTm;
	}
	/**
	 * Get regi_user_id 등록_사용자_ID character varying(20)
	 * @Return String regiUserId
	 */
	public String getRegiUserId() {
		return this.regiUserId;
	}
	
	/**
	 * Set regi_user_id 등록_사용자_ID character varying(20)
	 * @Param String regiUserId
	 */
	public void setRegiUserId(String regiUserId) {
		this.regiUserId = regiUserId;
	}
	/**
	 * Get updt_dt 수정_일자 character(8)
	 * @Return String updtDt
	 */
	public String getUpdtDt() {
		return this.updtDt;
	}
	
	/**
	 * Set updt_dt 수정_일자 character(8)
	 * @Param String updtDt
	 */
	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}
	/**
	 * Get updt_tm 수정_시각 character(6)
	 * @Return String updtTm
	 */
	public String getUpdtTm() {
		return this.updtTm;
	}
	
	/**
	 * Set updt_tm 수정_시각 character(6)
	 * @Param String updtTm
	 */
	public void setUpdtTm(String updtTm) {
		this.updtTm = updtTm;
	}
	/**
	 * Get updt_user_id 수정_사용자_ID character varying(20)
	 * @Return String updtUserId
	 */
	public String getUpdtUserId() {
		return this.updtUserId;
	}
	
	/**
	 * Set updt_user_id 수정_사용자_ID character varying(20)
	 * @Param String updtUserId
	 */
	public void setUpdtUserId(String updtUserId) {
		this.updtUserId = updtUserId;
	}

} // end of class
