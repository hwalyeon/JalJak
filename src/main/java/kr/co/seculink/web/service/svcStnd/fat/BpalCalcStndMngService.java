package kr.co.seculink.web.service.svcStnd.fat;

import kr.co.seculink.exception.BizException;

import java.util.List;
import java.util.Map;

public interface BpalCalcStndMngService
{
	//휴식대사량_계산_기준_리스트 조회
	public List<Map<String, String>> searchBpalCalcStndList(Map<String, String> params) throws BizException;
	
	//행추가_행삭제_저장
	public void saveBpalStnd(Map<String, Object> params) throws BizException;

	}
