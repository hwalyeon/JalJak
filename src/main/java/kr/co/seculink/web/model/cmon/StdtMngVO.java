package kr.co.seculink.web.model.cmon;

import lombok.Data;

@Data
public class StdtMngVO {
	
	private String crud;
	private String stdtId;
	private String stdtNm;
	private String telNo;
	private String mtelNo;
	private String mailAddr;
	private String stdtStatCd;
	private String profImgNo;
	private String filePath;
	private String lgicFileNm;
	private String pgicFileNm;
	private String imgFileNm;
}
