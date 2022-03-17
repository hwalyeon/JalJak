package kr.co.seculink.web.service.svcStnd.grow;

import kr.co.seculink.exception.BizException;

import java.util.List;
import java.util.Map;

public interface GrowJudgStndMngService
{
	public List<Map<String, String>> searchGrowJudgList(Map<String, String> params) throws BizException;
}

