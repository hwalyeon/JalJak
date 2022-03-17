package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * tc_menu_base Value Object
 */
public class TcMenuBaseVo implements Serializable {

	/* menu_no 메뉴_번호 character varying(200) */
	private String menuNo;

	/* uppr_menu_no 상위_메뉴_번호 character varying(200) */
	private String upprMenuNo;

	/* menu_url 메뉴_URL character varying(200) */
	private String menuUrl;

	/* menu_nm 메뉴_명 character varying(100) */
	private String menuNm;

	/* menu_desc 메뉴_설명 character varying(1000) */
	private String menuDesc;

	/* icon_info 아이콘_정보 character varying(40) */
	private String iconInfo;

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
	 * Get menu_no 메뉴_번호 character varying(200)
	 * @Return String menuNo
	 */
	public String getMenuNo() {
		return this.menuNo;
	}
	
	/**
	 * Set menu_no 메뉴_번호 character varying(200)
	 * @Param String menuNo
	 */
	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}
	/**
	 * Get uppr_menu_no 상위_메뉴_번호 character varying(200)
	 * @Return String upprMenuNo
	 */
	public String getUpprMenuNo() {
		return this.upprMenuNo;
	}
	
	/**
	 * Set uppr_menu_no 상위_메뉴_번호 character varying(200)
	 * @Param String upprMenuNo
	 */
	public void setUpprMenuNo(String upprMenuNo) {
		this.upprMenuNo = upprMenuNo;
	}
	/**
	 * Get menu_url 메뉴_URL character varying(200)
	 * @Return String menuUrl
	 */
	public String getMenuUrl() {
		return this.menuUrl;
	}
	
	/**
	 * Set menu_url 메뉴_URL character varying(200)
	 * @Param String menuUrl
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	/**
	 * Get menu_nm 메뉴_명 character varying(100)
	 * @Return String menuNm
	 */
	public String getMenuNm() {
		return this.menuNm;
	}
	
	/**
	 * Set menu_nm 메뉴_명 character varying(100)
	 * @Param String menuNm
	 */
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	/**
	 * Get menu_desc 메뉴_설명 character varying(1000)
	 * @Return String menuDesc
	 */
	public String getMenuDesc() {
		return this.menuDesc;
	}
	
	/**
	 * Set menu_desc 메뉴_설명 character varying(1000)
	 * @Param String menuDesc
	 */
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	/**
	 * Get icon_info 아이콘_정보 character varying(40)
	 * @Return String iconInfo
	 */
	public String getIconInfo() {
		return this.iconInfo;
	}
	
	/**
	 * Set icon_info 아이콘_정보 character varying(40)
	 * @Param String iconInfo
	 */
	public void setIconInfo(String iconInfo) {
		this.iconInfo = iconInfo;
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
