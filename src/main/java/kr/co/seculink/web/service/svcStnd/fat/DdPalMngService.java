package kr.co.seculink.web.service.svcStnd.fat;

import kr.co.seculink.exception.BizException;

import java.util.List;
import java.util.Map;

public interface DdPalMngService
{
	public List<Map<String, String>> searchDdPalList(Map<String, String> params) throws BizException;

	public void saveDdPal(Map<String,Object> params) throws BizException;
}

