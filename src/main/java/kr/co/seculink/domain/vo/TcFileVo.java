package kr.co.seculink.domain.vo;

import java.io.Serializable;

/**
 * tc_file Value Object
 */
public class TcFileVo implements Serializable {

	/* file_no 파일_번호 numeric(null) */
	private double fileNo;

	/* file_path 파일_경로 character varying(1000) */
	private String filePath;

	/* lgic_file_nm 논리_파일_명 character varying(400) */
	private String lgicFileNm;

	/* pgic_file_nm 물리_파일_명 character varying(400) */
	private String pgicFileNm;

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
	 * Get file_no 파일_번호 numeric(null)
	 * @Return double fileNo
	 */
	public double getFileNo() {
		return this.fileNo;
	}
	
	/**
	 * Set file_no 파일_번호 numeric(null)
	 * @Param double fileNo
	 */
	public void setFileNo(double fileNo) {
		this.fileNo = fileNo;
	}
	/**
	 * Get file_path 파일_경로 character varying(1000)
	 * @Return String filePath
	 */
	public String getFilePath() {
		return this.filePath;
	}
	
	/**
	 * Set file_path 파일_경로 character varying(1000)
	 * @Param String filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * Get lgic_file_nm 논리_파일_명 character varying(400)
	 * @Return String lgicFileNm
	 */
	public String getLgicFileNm() {
		return this.lgicFileNm;
	}
	
	/**
	 * Set lgic_file_nm 논리_파일_명 character varying(400)
	 * @Param String lgicFileNm
	 */
	public void setLgicFileNm(String lgicFileNm) {
		this.lgicFileNm = lgicFileNm;
	}
	/**
	 * Get pgic_file_nm 물리_파일_명 character varying(400)
	 * @Return String pgicFileNm
	 */
	public String getPgicFileNm() {
		return this.pgicFileNm;
	}
	
	/**
	 * Set pgic_file_nm 물리_파일_명 character varying(400)
	 * @Param String pgicFileNm
	 */
	public void setPgicFileNm(String pgicFileNm) {
		this.pgicFileNm = pgicFileNm;
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
