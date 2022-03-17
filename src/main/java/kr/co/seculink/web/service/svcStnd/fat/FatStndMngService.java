package kr.co.seculink.web.service.svcStnd.fat;

import kr.co.seculink.exception.BizException;

import java.util.List;
import java.util.Map;

public interface FatStndMngService
{
	//비만_기준_리스트 조회
	public List<Map<String, String>> searchFatStndList(Map<String, String> params) throws BizException;

	//행추가_행삭제 저장
	public void saveFatStnd(Map<String,Object> params) throws BizException;
	}
