package kr.co.seculink.web.service.user.prnt;

import kr.co.seculink.domain.RtnMsg;
import kr.co.seculink.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("prntInfoMngService")
public class PrntInfoMngServiceImpl implements PrntInfoMngService
{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;

	//보호자(사용자)_정보_리스트 조회
	public List<Map<String, String>> searchPrntInfoList(Map<String, String> params) throws BizException
	{
		List<Map<String, String>> result = dao.selectList("user.prnt.prntInfoMng.selectPrntInfoList", params);

		return result;
	}

	//보호자(사용자)_정보 상세보기
	public Map<String, Object> searchPrntInfo(Map<String, String> params) throws BizException
	{
		Map<String, String> result = dao.selectOne("user.prnt.prntInfoMng.selectPrntInfoDetl", params);

		Map<String, Object> rtnMap = new HashMap<>();

		rtnMap.put("result", result);

		return rtnMap;
	}

	//보호자(사용자)_정보 저장
	public void savePrntInfoDetl(Map<String, Object> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		int saveCnt = 0;

		if ("C".equals(params.get("crud"))){
			saveCnt += dao.insert("user.prnt.prntInfoMng.insertTmGuarInfoList", params);
		} else if ("U".equals(params.get("crud"))) {
			saveCnt += dao.update("user.prnt.prntInfoMng.updateTmGuarInfoList", params);
		} else if ("D".equals(params.get("crud"))) {
			saveCnt += dao.delete("user.prnt.prntInfoMng.deleteTmGuarInfoList", params);
		}
		if (saveCnt == 0) {
			throw new BizException("ECOM999", new String[]{"공통코드 저장이 실패하였습니다."});
		}
	}

	//배우자_정보 상세보기
	public Map<String, Object> searchSposInfo(Map<String, String> params) throws BizException
	{
		Map<String, String> result = dao.selectOne("user.prnt.prntInfoMng.selectSposInfoDetl", params);

		Map<String, Object> rtnMap = new HashMap<>();

		rtnMap.put("result", result);

		return rtnMap;
	}

	//배우자_정보 저장
	public void saveSposInfoDetl(Map<String, Object> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		int saveCnt = 0;

		if ("C".equals(params.get("crud"))){
			saveCnt += dao.insert("user.prnt.prntInfoMng.insertTmSposInfoList", params);
		} else if ("U".equals(params.get("crud"))) {
			saveCnt += dao.update("user.prnt.prntInfoMng.updateTmSposInfoList", params);
		} else if ("D".equals(params.get("crud"))) {
			saveCnt += dao.delete("user.prnt.prntInfoMng.deleteTmSposInfoList", params);
		}
		if (saveCnt == 0) {
			throw new BizException("ECOM999", new String[]{"공통코드 저장이 실패하였습니다."});
		}
	}

	//약관동의여부_정보_상세보기
	public List<Map<String, Object>> searchTermAgreYnInfoDetlList(Map<String, String> params) throws BizException
	{
		List<Map<String, Object>> result = dao.selectList("user.prnt.prntInfoMng.selectTermAgreYnInfoDetl", params);

		return result;
	}


}
	
