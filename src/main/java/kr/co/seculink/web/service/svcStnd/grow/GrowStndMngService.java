package kr.co.seculink.web.service.svcStnd.grow;

import kr.co.seculink.exception.BizException;

import java.util.List;
import java.util.Map;

public interface GrowStndMngService
{
	//성장_기준_리스트 조회
	public List<Map<String, String>> growStndList(Map<String, String> params) throws BizException;

	//행추가_행삭제 저장
	public void  saveGrowStnd(Map<String,Object>params) throws BizException;
}
