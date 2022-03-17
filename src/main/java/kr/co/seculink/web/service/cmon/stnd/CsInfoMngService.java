package kr.co.seculink.web.service.cmon.stnd;

import kr.co.seculink.exception.BizException;

import java.util.List;
import java.util.Map;

public interface CsInfoMngService
{
	public List<Map<String, String>> searchCsInfoList(Map<String, String> params) throws BizException;
}

