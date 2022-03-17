package kr.co.seculink.web.service.svcStnd.nutr;

import kr.co.seculink.exception.BizException;

import java.util.List;
import java.util.Map;

public interface NutrInfoMngService
{
	//영양소_정보_리스트 조회
	public List<Map<String, String>> searchNutrInfoList(Map<String, String> params) throws BizException;

	//영양소_코드_리스트 조회
	public List<Map<String, String>> nutrCdList(Map<String, String> params) throws BizException;

	//행추가,행삭제 저장
	public void saveNutrInfo(Map<String, Object> params) throws BizException;

	public void saveNutrStatStndInfo(Map<String, String> params)throws BizException;

	public void saveNutrEatStndInfo(Map<String, Object> params) throws BizException;
}
