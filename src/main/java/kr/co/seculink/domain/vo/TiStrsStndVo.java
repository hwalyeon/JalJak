package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ti_strs_stnd Value Object
 */
public class TiStrsStndVo implements Serializable {

	/* ment_strs_stat_cd 정신적_스트레스_상태_코드 character varying(20) */
	private String mentStrsStatCd;

	/* phys_strs_stat_cd 신체적_스트레스_상태_코드 character varying(20) */
	private String physStrsStatCd;

	/* strs_judg_cntn 스트레스_판정_내용 text(null) */
	private String strsJudgCntn;

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
	 * Get strs_judg_cntn 스트레스_판정_내용 text(null)
	 * @Return String strsJudgCntn
	 */
	public String getStrsJudgCntn() {
		return this.strsJudgCntn;
	}
	
	/**
	 * Set strs_judg_cntn 스트레스_판정_내용 text(null)
	 * @Param String strsJudgCntn
	 */
	public void setStrsJudgCntn(String strsJudgCntn) {
		this.strsJudgCntn = strsJudgCntn;
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
