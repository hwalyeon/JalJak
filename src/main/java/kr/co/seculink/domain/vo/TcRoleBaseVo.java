package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * tc_role_base Value Object
 */
public class TcRoleBaseVo implements Serializable {

	/* role_cd 역할_코드 character varying(40) */
	private String roleCd;

	/* role_nm 역할_명 character varying(100) */
	private String roleNm;

	/* role_desc 역할_설명 character varying(1000) */
	private String roleDesc;

	/* role_div_cd 역할_구분_코드 character varying(20) */
	private String roleDivCd;

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
	 * Get role_cd 역할_코드 character varying(40)
	 * @Return String roleCd
	 */
	public String getRoleCd() {
		return this.roleCd;
	}
	
	/**
	 * Set role_cd 역할_코드 character varying(40)
	 * @Param String roleCd
	 */
	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}
	/**
	 * Get role_nm 역할_명 character varying(100)
	 * @Return String roleNm
	 */
	public String getRoleNm() {
		return this.roleNm;
	}
	
	/**
	 * Set role_nm 역할_명 character varying(100)
	 * @Param String roleNm
	 */
	public void setRoleNm(String roleNm) {
		this.roleNm = roleNm;
	}
	/**
	 * Get role_desc 역할_설명 character varying(1000)
	 * @Return String roleDesc
	 */
	public String getRoleDesc() {
		return this.roleDesc;
	}
	
	/**
	 * Set role_desc 역할_설명 character varying(1000)
	 * @Param String roleDesc
	 */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	/**
	 * Get role_div_cd 역할_구분_코드 character varying(20)
	 * @Return String roleDivCd
	 */
	public String getRoleDivCd() {
		return this.roleDivCd;
	}
	
	/**
	 * Set role_div_cd 역할_구분_코드 character varying(20)
	 * @Param String roleDivCd
	 */
	public void setRoleDivCd(String roleDivCd) {
		this.roleDivCd = roleDivCd;
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
