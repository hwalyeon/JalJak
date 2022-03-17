package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ti_pal_stnd Value Object
 */
public class TiPalStndVo implements Serializable {

	/* pal_cd 신체활동수준_코드 character varying(20) */
	private String palCd;

	/* pal_nm 신체활동수준_명 character varying(30) */
	private String palNm;

	/* pal_val_fr 신체활동수준_값_FROM numeric(null) */
	private double palValFr;

	/* pal_val_to 신체활동수준_값_TO numeric(null) */
	private double palValTo;

	/* pal_eat_rmrk 신체활동수준_섭취_비고 character varying(4000) */
	private String palEatRmrk;

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
	 * Get pal_cd 신체활동수준_코드 character varying(20)
	 * @Return String palCd
	 */
	public String getPalCd() {
		return this.palCd;
	}
	
	/**
	 * Set pal_cd 신체활동수준_코드 character varying(20)
	 * @Param String palCd
	 */
	public void setPalCd(String palCd) {
		this.palCd = palCd;
	}
	/**
	 * Get pal_nm 신체활동수준_명 character varying(30)
	 * @Return String palNm
	 */
	public String getPalNm() {
		return this.palNm;
	}
	
	/**
	 * Set pal_nm 신체활동수준_명 character varying(30)
	 * @Param String palNm
	 */
	public void setPalNm(String palNm) {
		this.palNm = palNm;
	}
	/**
	 * Get pal_val_fr 신체활동수준_값_FROM numeric(null)
	 * @Return double palValFr
	 */
	public double getPalValFr() {
		return this.palValFr;
	}
	
	/**
	 * Set pal_val_fr 신체활동수준_값_FROM numeric(null)
	 * @Param double palValFr
	 */
	public void setPalValFr(double palValFr) {
		this.palValFr = palValFr;
	}
	/**
	 * Get pal_val_to 신체활동수준_값_TO numeric(null)
	 * @Return double palValTo
	 */
	public double getPalValTo() {
		return this.palValTo;
	}
	
	/**
	 * Set pal_val_to 신체활동수준_값_TO numeric(null)
	 * @Param double palValTo
	 */
	public void setPalValTo(double palValTo) {
		this.palValTo = palValTo;
	}
	/**
	 * Get pal_eat_rmrk 신체활동수준_섭취_비고 character varying(4000)
	 * @Return String palEatRmrk
	 */
	public String getPalEatRmrk() {
		return this.palEatRmrk;
	}
	
	/**
	 * Set pal_eat_rmrk 신체활동수준_섭취_비고 character varying(4000)
	 * @Param String palEatRmrk
	 */
	public void setPalEatRmrk(String palEatRmrk) {
		this.palEatRmrk = palEatRmrk;
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
