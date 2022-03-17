package kr.co.seculink.web.service.svcStnd.dgem;

import kr.co.seculink.exception.BizException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface DgemStndMngService
{
	public List<Map<String, String>> searchDgemList(Map<String, String> params) throws BizException;

	public void saveDgemList(Map<String, Object> params) throws BizException;
	//중복 조회
	public Map<String, String> searchDupCdCk(Map<String, String> params) throws BizException;

}
 