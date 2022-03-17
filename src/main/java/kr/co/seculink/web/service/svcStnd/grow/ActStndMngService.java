package kr.co.seculink.web.service.svcStnd.grow;

import kr.co.seculink.exception.BizException;

import java.util.List;
import java.util.Map;

public interface ActStndMngService
{
	//활동_기준_리스트 조회
	public List<Map<String, String>> actStndList(Map<String, String> params) throws BizException;

	//활동_분류_코드_리스트 조회
	public List<Map<String, String>> actClssCdList(Map<String, String> params) throws BizException;

	//행추가_행삭제 저장
	public void  saveActStnd(Map<String,Object>params) throws BizException;

	}
