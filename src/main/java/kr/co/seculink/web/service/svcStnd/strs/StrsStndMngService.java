package kr.co.seculink.web.service.svcStnd.strs;

import kr.co.seculink.exception.BizException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface StrsStndMngService
{
	public List<Map<String, String>> searchStrsList(Map<String, String> params) throws BizException;

	public List<Map<String, String>> searchCdSpecList(Map<String, String> params) throws BizException;

	/*public void saveStrsList(Map<String, Object> params) throws BizException;*/

	public Map<String, String> searchmentphysCd(Map<String, String> params) throws BizException;
}

