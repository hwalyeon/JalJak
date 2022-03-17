package kr.co.seculink.web.service.svcStnd.food;

import kr.co.seculink.exception.BizException;

import java.util.List;
import java.util.Map;

public interface FoodInfoMngService
{
	public List<Map<String, String>> searchFoodInfoList(Map<String, String> params) throws BizException;
}

