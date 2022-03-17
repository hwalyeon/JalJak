package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * ti_eorg_base Value Object
 */
public class TiEorgBaseVo implements Serializable {

	/* loc_no 위치_번호 numeric(null) */
	private double locNo;

	/* plc_cd 장소_코드 character varying(20) */
	private String plcCd;

	/* loc_nm 위치_명 character varying(100) */
	private String locNm;

	/* road_addr 도로명_주소 character varying(400) */
	private String roadAddr;

	/* jibn_addr 지번_주소 character varying(400) */
	private String jibnAddr;

	/* lat_val 위도_값 numeric(null) */
	private double latVal;

	/* lon_val 경도_값 numeric(null) */
	private double lonVal;

	/* mng_orgn_nm 관리_기관_명 character varying(100) */
	private String mngOrgnNm;

	/* mng_polc_nm 관할_경찰서_명 character varying(100) */
	private String mngPolcNm;

	/* cctv_yn CCTV_여부 character(1) */
	private String cctvYn;

	/* cctv_cnt CCTV_대수 numeric(null) */
	private double cctvCnt;

	/* road_wdth_desc 도로_넓이_설명 character varying(50) */
	private String roadWdthDesc;

	/* data_stnd_dt 자료_기준_일자 character(8) */
	private String dataStndDt;

	/* prov_orgn_cd 제공_기관_코드 character varying(20) */
	private String provOrgnCd;

	/* prov_orgn_nm 제공_기관_명 character varying(100) */
	private String provOrgnNm;

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
	 * Get loc_no 위치_번호 numeric(null)
	 * @Return double locNo
	 */
	public double getLocNo() {
		return this.locNo;
	}
	
	/**
	 * Set loc_no 위치_번호 numeric(null)
	 * @Param double locNo
	 */
	public void setLocNo(double locNo) {
		this.locNo = locNo;
	}
	/**
	 * Get plc_cd 장소_코드 character varying(20)
	 * @Return String plcCd
	 */
	public String getPlcCd() {
		return this.plcCd;
	}
	
	/**
	 * Set plc_cd 장소_코드 character varying(20)
	 * @Param String plcCd
	 */
	public void setPlcCd(String plcCd) {
		this.plcCd = plcCd;
	}
	/**
	 * Get loc_nm 위치_명 character varying(100)
	 * @Return String locNm
	 */
	public String getLocNm() {
		return this.locNm;
	}
	
	/**
	 * Set loc_nm 위치_명 character varying(100)
	 * @Param String locNm
	 */
	public void setLocNm(String locNm) {
		this.locNm = locNm;
	}
	/**
	 * Get road_addr 도로명_주소 character varying(400)
	 * @Return String roadAddr
	 */
	public String getRoadAddr() {
		return this.roadAddr;
	}
	
	/**
	 * Set road_addr 도로명_주소 character varying(400)
	 * @Param String roadAddr
	 */
	public void setRoadAddr(String roadAddr) {
		this.roadAddr = roadAddr;
	}
	/**
	 * Get jibn_addr 지번_주소 character varying(400)
	 * @Return String jibnAddr
	 */
	public String getJibnAddr() {
		return this.jibnAddr;
	}
	
	/**
	 * Set jibn_addr 지번_주소 character varying(400)
	 * @Param String jibnAddr
	 */
	public void setJibnAddr(String jibnAddr) {
		this.jibnAddr = jibnAddr;
	}
	/**
	 * Get lat_val 위도_값 numeric(null)
	 * @Return double latVal
	 */
	public double getLatVal() {
		return this.latVal;
	}
	
	/**
	 * Set lat_val 위도_값 numeric(null)
	 * @Param double latVal
	 */
	public void setLatVal(double latVal) {
		this.latVal = latVal;
	}
	/**
	 * Get lon_val 경도_값 numeric(null)
	 * @Return double lonVal
	 */
	public double getLonVal() {
		return this.lonVal;
	}
	
	/**
	 * Set lon_val 경도_값 numeric(null)
	 * @Param double lonVal
	 */
	public void setLonVal(double lonVal) {
		this.lonVal = lonVal;
	}
	/**
	 * Get mng_orgn_nm 관리_기관_명 character varying(100)
	 * @Return String mngOrgnNm
	 */
	public String getMngOrgnNm() {
		return this.mngOrgnNm;
	}
	
	/**
	 * Set mng_orgn_nm 관리_기관_명 character varying(100)
	 * @Param String mngOrgnNm
	 */
	public void setMngOrgnNm(String mngOrgnNm) {
		this.mngOrgnNm = mngOrgnNm;
	}
	/**
	 * Get mng_polc_nm 관할_경찰서_명 character varying(100)
	 * @Return String mngPolcNm
	 */
	public String getMngPolcNm() {
		return this.mngPolcNm;
	}
	
	/**
	 * Set mng_polc_nm 관할_경찰서_명 character varying(100)
	 * @Param String mngPolcNm
	 */
	public void setMngPolcNm(String mngPolcNm) {
		this.mngPolcNm = mngPolcNm;
	}
	/**
	 * Get cctv_yn CCTV_여부 character(1)
	 * @Return String cctvYn
	 */
	public String getCctvYn() {
		return this.cctvYn;
	}
	
	/**
	 * Set cctv_yn CCTV_여부 character(1)
	 * @Param String cctvYn
	 */
	public void setCctvYn(String cctvYn) {
		this.cctvYn = cctvYn;
	}
	/**
	 * Get cctv_cnt CCTV_대수 numeric(null)
	 * @Return double cctvCnt
	 */
	public double getCctvCnt() {
		return this.cctvCnt;
	}
	
	/**
	 * Set cctv_cnt CCTV_대수 numeric(null)
	 * @Param double cctvCnt
	 */
	public void setCctvCnt(double cctvCnt) {
		this.cctvCnt = cctvCnt;
	}
	/**
	 * Get road_wdth_desc 도로_넓이_설명 character varying(50)
	 * @Return String roadWdthDesc
	 */
	public String getRoadWdthDesc() {
		return this.roadWdthDesc;
	}
	
	/**
	 * Set road_wdth_desc 도로_넓이_설명 character varying(50)
	 * @Param String roadWdthDesc
	 */
	public void setRoadWdthDesc(String roadWdthDesc) {
		this.roadWdthDesc = roadWdthDesc;
	}
	/**
	 * Get data_stnd_dt 자료_기준_일자 character(8)
	 * @Return String dataStndDt
	 */
	public String getDataStndDt() {
		return this.dataStndDt;
	}
	
	/**
	 * Set data_stnd_dt 자료_기준_일자 character(8)
	 * @Param String dataStndDt
	 */
	public void setDataStndDt(String dataStndDt) {
		this.dataStndDt = dataStndDt;
	}
	/**
	 * Get prov_orgn_cd 제공_기관_코드 character varying(20)
	 * @Return String provOrgnCd
	 */
	public String getProvOrgnCd() {
		return this.provOrgnCd;
	}
	
	/**
	 * Set prov_orgn_cd 제공_기관_코드 character varying(20)
	 * @Param String provOrgnCd
	 */
	public void setProvOrgnCd(String provOrgnCd) {
		this.provOrgnCd = provOrgnCd;
	}
	/**
	 * Get prov_orgn_nm 제공_기관_명 character varying(100)
	 * @Return String provOrgnNm
	 */
	public String getProvOrgnNm() {
		return this.provOrgnNm;
	}
	
	/**
	 * Set prov_orgn_nm 제공_기관_명 character varying(100)
	 * @Param String provOrgnNm
	 */
	public void setProvOrgnNm(String provOrgnNm) {
		this.provOrgnNm = provOrgnNm;
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
