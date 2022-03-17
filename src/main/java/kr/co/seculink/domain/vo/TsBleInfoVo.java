package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ts_ble_info Value Object
 */
public class TsBleInfoVo implements Serializable {

	/* ble_id BLE_ID character varying(40) */
	private String bleId;

	/* ble_inst_dt BLE_설치_일자 character(8) */
	private String bleInstDt;

	/* ble_inst_tm BLE_설치_시각 character(6) */
	private String bleInstTm;

	/* rmrk 비고 character varying(2000) */
	private String rmrk;

	/* eorg_no 교육시설_번호 numeric(null) */
	private double eorgNo;

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
	 * Get ble_id BLE_ID character varying(40)
	 * @Return String bleId
	 */
	public String getBleId() {
		return this.bleId;
	}
	
	/**
	 * Set ble_id BLE_ID character varying(40)
	 * @Param String bleId
	 */
	public void setBleId(String bleId) {
		this.bleId = bleId;
	}
	/**
	 * Get ble_inst_dt BLE_설치_일자 character(8)
	 * @Return String bleInstDt
	 */
	public String getBleInstDt() {
		return this.bleInstDt;
	}
	
	/**
	 * Set ble_inst_dt BLE_설치_일자 character(8)
	 * @Param String bleInstDt
	 */
	public void setBleInstDt(String bleInstDt) {
		this.bleInstDt = bleInstDt;
	}
	/**
	 * Get ble_inst_tm BLE_설치_시각 character(6)
	 * @Return String bleInstTm
	 */
	public String getBleInstTm() {
		return this.bleInstTm;
	}
	
	/**
	 * Set ble_inst_tm BLE_설치_시각 character(6)
	 * @Param String bleInstTm
	 */
	public void setBleInstTm(String bleInstTm) {
		this.bleInstTm = bleInstTm;
	}
	/**
	 * Get rmrk 비고 character varying(2000)
	 * @Return String rmrk
	 */
	public String getRmrk() {
		return this.rmrk;
	}
	
	/**
	 * Set rmrk 비고 character varying(2000)
	 * @Param String rmrk
	 */
	public void setRmrk(String rmrk) {
		this.rmrk = rmrk;
	}
	/**
	 * Get eorg_no 교육시설_번호 numeric(null)
	 * @Return double eorgNo
	 */
	public double getEorgNo() {
		return this.eorgNo;
	}
	
	/**
	 * Set eorg_no 교육시설_번호 numeric(null)
	 * @Param double eorgNo
	 */
	public void setEorgNo(double eorgNo) {
		this.eorgNo = eorgNo;
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
