package kr.co.seculink.web.service.svcStnd.fat;

import kr.co.seculink.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("fatStndMngService")
public class FatStndMngServiceImpl implements FatStndMngService
{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;
	
    //비만_기준_리스트 조회
	public List<Map<String, String>> searchFatStndList(Map<String, String> params) throws BizException
	{
		List<Map<String, String>> result = dao.selectList("svcStnd.fat.fatStndMng.searchFatStndList", params);
		return result;
	}
    //비만_기준_버전_리스트 조회
	public List<Map<String, String>> fatStndVerList(Map<String, String> params) throws BizException
	{
		List<Map<String, String>> result = dao.selectList("svcStnd.fat.fatStndMng.searchFatStndVerList", params);
		return result;
	}
    //나이_년수_리스트 조회
	public List<Map<String, String>> ageYcntList(Map<String, String> params) throws BizException
	{
		List<Map<String, String>> result = dao.selectList("svcStnd.fat.fatStndMng.searchAgeYcntList", params);
		return result;
	}
	//행추가_행삭제 저장
	@Transactional
	public void saveFatStnd(Map<String, Object> params) throws BizException
	{
		int saveCnt = 0;

		List<Map<String, String>> gridDate = (List<Map<String, String>>) params.get("gridList");

		for(Map<String,String> info:gridDate){
			log.debug("crud         : " +  info.get("crud"));

			if( "C".equals(info.get("crud"))){
				saveCnt += dao.insert("svcStnd.fat.fatStndMng.insertTiFatStndList", info);
			}else if( "U".equals(info.get("crud"))){
				saveCnt += dao.update("svcStnd.fat.fatStndMng.updateTiFatStndList", info);
			}else if( "D".equals(info.get("crud"))){
				saveCnt += dao.delete("svcStnd.fat.fatStndMng.deleteTiFatStndList", info);
			}
		}
		if(saveCnt == 0){
			throw new BizException("ECOM999", new String[]{"비만기준정보 저장이 실패하였습니다"});
		}
	}



}
