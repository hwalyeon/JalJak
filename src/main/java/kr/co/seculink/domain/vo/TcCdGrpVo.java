package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * tc_cd_grp Value Object
 */
public class TcCdGrpVo implements Serializable {

	/* cd_grp 코드_그룹 character varying(40) */
	private String cdGrp;

	/* cd_grp_nm 코드_그룹_명 character varying(100) */
	private String cdGrpNm;

	/* use_yn 사용_여부 character(1) */
	private String useYn;

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
	 * Get cd_grp 코드_그룹 character varying(40)
	 * @Return String cdGrp
	 */
	public String getCdGrp() {
		return this.cdGrp;
	}
	
	/**
	 * Set cd_grp 코드_그룹 character varying(40)
	 * @Param String cdGrp
	 */
	public void setCdGrp(String cdGrp) {
		this.cdGrp = cdGrp;
	}
	/**
	 * Get cd_grp_nm 코드_그룹_명 character varying(100)
	 * @Return String cdGrpNm
	 */
	public String getCdGrpNm() {
		return this.cdGrpNm;
	}
	
	/**
	 * Set cd_grp_nm 코드_그룹_명 character varying(100)
	 * @Param String cdGrpNm
	 */
	public void setCdGrpNm(String cdGrpNm) {
		this.cdGrpNm = cdGrpNm;
	}
	/**
	 * Get use_yn 사용_여부 character(1)
	 * @Return String useYn
	 */
	public String getUseYn() {
		return this.useYn;
	}
	
	/**
	 * Set use_yn 사용_여부 character(1)
	 * @Param String useYn
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
