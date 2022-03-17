package kr.co.seculink.web.service.svcStnd.nutr;

import kr.co.seculink.exception.BizException;

import java.util.List;
import java.util.Map;

public interface DdNutrEatStndMngService
{
	//일일_영양소_섭취_기준_리스트 조회
	public List<Map<String, String>> searchDdNutrEatStndList(Map<String, String> params) throws BizException;

	//영양소_코드_명_리스트 조회
	public List<Map<String, String>> searchNutrCdNmList(Map<String, String> params) throws BizException;

	//행추가_행삭제_저장
	public void saveDdNutrEatStnd(Map<String, Object> params) throws BizException;

	}
