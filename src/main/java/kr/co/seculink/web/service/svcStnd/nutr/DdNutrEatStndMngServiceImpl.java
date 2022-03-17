package kr.co.seculink.web.service.svcStnd.nutr;

import kr.co.seculink.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;


@Slf4j
@Service("ddNutrEatStndMngService")
public class DdNutrEatStndMngServiceImpl implements DdNutrEatStndMngService
{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;

	//일일_영양소_섭취_기준_리스트 조회
	public List<Map<String, String>> searchDdNutrEatStndList(Map<String, String> params) throws BizException
	{
		List<Map<String, String>> result = dao.selectList("svcStnd.nutr.ddNutrEatStndMng.selectDdNutrEatStndList", params);
		return result;
	}

	//영양소_코드_명_리스트 조회
	public List<Map<String, String>> searchNutrCdNmList(Map<String, String> params) throws BizException
	{
		List<Map<String, String>> result = dao.selectList("svcStnd.nutr.ddNutrEatStndMng.searchNutrCdNmList", params);
		return result;
	}

	//행추가_행삭제 저장
	public void saveDdNutrEatStnd(Map<String, Object> params) throws BizException
	{
		int saveCnt = 0;

		List<Map<String, String>> gridDate = (List<Map<String, String>>) params.get("gridList");
		Map<String, String> keyValue = new HashMap<String , String>();

		for(Map<String,String> info:gridDate){
			Iterator iterator = info.entrySet().iterator();
			for(String key : info.keySet()){
				String sexCd = info.get("sexCd");
				String ageYcnt = info.get("ageYcnt");
				if(!"sexCd".equals(key) && !"ageYcnt".equals(key) && !"crud".equals(key) ) {
					keyValue = new HashMap<String, String>();
					keyValue.put("sexCd", sexCd);
					keyValue.put("ageYcnt", ageYcnt);
					keyValue.put("nutrCd", key);
					keyValue.put("ddRcmdQty", info.get(key));
					keyValue.put("ddNeedQty", info.get(key));
					saveCnt += dao.update("svcStnd.nutr.ddNutrEatStndMng.updateTiDdNutrEatStndList", keyValue);
				}
			}
		}
	}


	
}
	
