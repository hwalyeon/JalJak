package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ts_stdt_strs_hist Value Object
 */
public class TsStdtStrsHistVo implements Serializable {

	/* stdt_no 학생_번호 numeric(null) */
	private double stdtNo;

	/* strs_hist_seq 스트레스_이력_순번 numeric(null) */
	private double strsHistSeq;

	/* strs_judg_dttm 스트레스_판정_일시 character(14) */
	private String strsJudgDttm;

	/* strs_idx 스트레스_지수 numeric(null) */
	private double strsIdx;

	/* hbit_mdan 심박_중간값 numeric(null) */
	private double hbitMdan;

	/* ment_strs_pnt 정신적_스트레스_점수 numeric(null) */
	private double mentStrsPnt;

	/* ment_strs_stat_cd 정신적_스트레스_상태_코드 character varying(20) */
	private String mentStrsStatCd;

	/* phys_strs_pnt 신체적_스트레스_점수 numeric(null) */
	private double physStrsPnt;

	/* phys_strs_stat_cd 신체적_스트레스_상태_코드 character varying(20) */
	private String physStrsStatCd;

	/* cabl_strs_pnt 대처능력_스트레스_점수 numeric(null) */
	private double cablStrsPnt;

	/* cabl_strs_stat_cd 대처능력_스트레스_상태_코드 character varying(20) */
	private String cablStrsStatCd;

	/* avg_hbit_cnt 평균_심박_수 numeric(null) */
	private double avgHbitCnt;

	/* abnm_hbit_cnt 이상_심박_수 numeric(null) */
	private double abnmHbitCnt;

	/* strs_stat_cd 스트레스_상태_코드 character varying(20) */
	private String strsStatCd;

	/* strs_proc_stat_cd 스트레스_처리_상태_코드 character varying(20) */
	private String strsProcStatCd;

	/* judg_no 판정_번호 numeric(null) */
	private double judgNo;

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
	 * Get strs_hist_seq 스트레스_이력_순번 numeric(null)
	 * @Return double strsHistSeq
	 */
	public double getStrsHistSeq() {
		return this.strsHistSeq;
	}
	
	/**
	 * Set strs_hist_seq 스트레스_이력_순번 numeric(null)
	 * @Param double strsHistSeq
	 */
	public void setStrsHistSeq(double strsHistSeq) {
		this.strsHistSeq = strsHistSeq;
	}
	/**
	 * Get strs_judg_dttm 스트레스_판정_일시 character(14)
	 * @Return String strsJudgDttm
	 */
	public String getStrsJudgDttm() {
		return this.strsJudgDttm;
	}
	
	/**
	 * Set strs_judg_dttm 스트레스_판정_일시 character(14)
	 * @Param String strsJudgDttm
	 */
	public void setStrsJudgDttm(String strsJudgDttm) {
		this.strsJudgDttm = strsJudgDttm;
	}
	/**
	 * Get strs_idx 스트레스_지수 numeric(null)
	 * @Return double strsIdx
	 */
	public double getStrsIdx() {
		return this.strsIdx;
	}
	
	/**
	 * Set strs_idx 스트레스_지수 numeric(null)
	 * @Param double strsIdx
	 */
	public void setStrsIdx(double strsIdx) {
		this.strsIdx = strsIdx;
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
	 * Get ment_strs_pnt 정신적_스트레스_점수 numeric(null)
	 * @Return double mentStrsPnt
	 */
	public double getMentStrsPnt() {
		return this.mentStrsPnt;
	}
	
	/**
	 * Set ment_strs_pnt 정신적_스트레스_점수 numeric(null)
	 * @Param double mentStrsPnt
	 */
	public void setMentStrsPnt(double mentStrsPnt) {
		this.mentStrsPnt = mentStrsPnt;
	}
	/**
	 * Get ment_strs_stat_cd 정신적_스트레스_상태_코드 character varying(20)
	 * @Return String mentStrsStatCd
	 */
	public String getMentStrsStatCd() {
		return this.mentStrsStatCd;
	}
	
	/**
	 * Set ment_strs_stat_cd 정신적_스트레스_상태_코드 character varying(20)
	 * @Param String mentStrsStatCd
	 */
	public void setMentStrsStatCd(String mentStrsStatCd) {
		this.mentStrsStatCd = mentStrsStatCd;
	}
	/**
	 * Get phys_strs_pnt 신체적_스트레스_점수 numeric(null)
	 * @Return double physStrsPnt
	 */
	public double getPhysStrsPnt() {
		return this.physStrsPnt;
	}
	
	/**
	 * Set phys_strs_pnt 신체적_스트레스_점수 numeric(null)
	 * @Param double physStrsPnt
	 */
	public void setPhysStrsPnt(double physStrsPnt) {
		this.physStrsPnt = physStrsPnt;
	}
	/**
	 * Get phys_strs_stat_cd 신체적_스트레스_상태_코드 character varying(20)
	 * @Return String physStrsStatCd
	 */
	public String getPhysStrsStatCd() {
		return this.physStrsStatCd;
	}
	
	/**
	 * Set phys_strs_stat_cd 신체적_스트레스_상태_코드 character varying(20)
	 * @Param String physStrsStatCd
	 */
	public void setPhysStrsStatCd(String physStrsStatCd) {
		this.physStrsStatCd = physStrsStatCd;
	}
	/**
	 * Get cabl_strs_pnt 대처능력_스트레스_점수 numeric(null)
	 * @Return double cablStrsPnt
	 */
	public double getCablStrsPnt() {
		return this.cablStrsPnt;
	}
	
	/**
	 * Set cabl_strs_pnt 대처능력_스트레스_점수 numeric(null)
	 * @Param double cablStrsPnt
	 */
	public void setCablStrsPnt(double cablStrsPnt) {
		this.cablStrsPnt = cablStrsPnt;
	}
	/**
	 * Get cabl_strs_stat_cd 대처능력_스트레스_상태_코드 character varying(20)
	 * @Return String cablStrsStatCd
	 */
	public String getCablStrsStatCd() {
		return this.cablStrsStatCd;
	}
	
	/**
	 * Set cabl_strs_stat_cd 대처능력_스트레스_상태_코드 character varying(20)
	 * @Param String cablStrsStatCd
	 */
	public void setCablStrsStatCd(String cablStrsStatCd) {
		this.cablStrsStatCd = cablStrsStatCd;
	}
	/**
	 * Get avg_hbit_cnt 평균_심박_수 numeric(null)
	 * @Return double avgHbitCnt
	 */
	public double getAvgHbitCnt() {
		return this.avgHbitCnt;
	}
	
	/**
	 * Set avg_hbit_cnt 평균_심박_수 numeric(null)
	 * @Param double avgHbitCnt
	 */
	public void setAvgHbitCnt(double avgHbitCnt) {
		this.avgHbitCnt = avgHbitCnt;
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
	 * Get strs_stat_cd 스트레스_상태_코드 character varying(20)
	 * @Return String strsStatCd
	 */
	public String getStrsStatCd() {
		return this.strsStatCd;
	}
	
	/**
	 * Set strs_stat_cd 스트레스_상태_코드 character varying(20)
	 * @Param String strsStatCd
	 */
	public void setStrsStatCd(String strsStatCd) {
		this.strsStatCd = strsStatCd;
	}
	/**
	 * Get strs_proc_stat_cd 스트레스_처리_상태_코드 character varying(20)
	 * @Return String strsProcStatCd
	 */
	public String getStrsProcStatCd() {
		return this.strsProcStatCd;
	}
	
	/**
	 * Set strs_proc_stat_cd 스트레스_처리_상태_코드 character varying(20)
	 * @Param String strsProcStatCd
	 */
	public void setStrsProcStatCd(String strsProcStatCd) {
		this.strsProcStatCd = strsProcStatCd;
	}
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
