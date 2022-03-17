package kr.co.seculink.web.service.svcStnd.nutr;

import kr.co.seculink.exception.BizException;

import java.util.List;
import java.util.Map;

public interface DdRcmdEatStndMngService
{
	//일일_권장_섭취량_기준_리스트 조회
	public List<Map<String, String>> searchDdRcmdEatStndList(Map<String, String> params) throws BizException;

	//행추가_행삭제_저장
	public void saveDdRcmdEatStnd(Map<String, Object> params) throws BizException;

	}
