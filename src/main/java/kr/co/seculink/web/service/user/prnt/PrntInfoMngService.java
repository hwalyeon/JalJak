package kr.co.seculink.web.service.user.prnt;

import kr.co.seculink.exception.BizException;

import java.util.List;
import java.util.Map;

public interface PrntInfoMngService
{
	//보호자(사용자)_정보_리스트 조회
	public List<Map<String, String>> searchPrntInfoList(Map<String, String> params) throws BizException;

	//보호자(사용자)_정보 상세보기
	public Map<String, Object> searchPrntInfo(Map<String, String> params) throws BizException;

	//보호자(사용자)_정보 저장
	public void savePrntInfoDetl(Map<String, Object> params) throws BizException;

	//배우자_정보 상세보기
	public Map<String, Object> searchSposInfo(Map<String, String> params) throws BizException;

	//배우자_정보_정보 저장
	public void saveSposInfoDetl(Map<String, Object> params) throws BizException;

	//약관동의여부_정보 상세보기
	public List<Map<String, Object>> searchTermAgreYnInfoDetlList(Map<String, String> params) throws BizException;


}
