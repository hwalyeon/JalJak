package kr.co.seculink.web.service.svcStnd.fat;

import kr.co.seculink.exception.BizException;

import java.util.List;
import java.util.Map;

public interface FatJudgStndMngService
{
	public List<Map<String, String>> searchFatJudgList(Map<String, String> params) throws BizException;
}

