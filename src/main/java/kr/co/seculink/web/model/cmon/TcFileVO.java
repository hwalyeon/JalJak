package kr.co.seculink.web.model.cmon;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class TcFileVO
{
	private String cousNo;
	private String lectSeq;
	private String userId;
	private String fileDivCd;
	private String fileNo;
	private String filePath;
	private String lgicFileNm;
	private String pgicFileNm;
	private String prefix;
	private MultipartFile file;
	private String dataURL;
	private String regUserId;
	private String uptUserId;
}
