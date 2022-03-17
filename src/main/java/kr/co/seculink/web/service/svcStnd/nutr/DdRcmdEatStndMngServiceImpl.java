package kr.co.seculink.web.service.svcStnd.nutr;

import kr.co.seculink.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("ddRcmdEatStndMngService")
public class DdRcmdEatStndMngServiceImpl implements DdRcmdEatStndMngService
{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;

	//일일_권장_섭취량_기준_리스트 조회
	public List<Map<String, String>> searchDdRcmdEatStndList(Map<String, String> params) throws BizException
	{
		List<Map<String, String>> result = dao.selectList("svcStnd.nutr.ddRcmdEatStndMng.selectDdRcmdEatStndList", params);
		return result;
	}

	//행추가_행삭제 저장
	public void saveDdRcmdEatStnd(Map<String, Object> params) throws BizException
	{
		int saveCnt = 0;

		List<Map<String, String>> gridDate = (List<Map<String, String>>) params.get("gridList");

		for(Map<String,String> info:gridDate){
			log.debug("crud         : " +  info.get("crud"));

			if( "C".equals(info.get("crud"))){
				saveCnt += dao.insert("svcStnd.nutr.ddRcmdEatStndMng.insertTiDdRcmdEatStndList", info);
			}else if( "U".equals(info.get("crud"))){
				saveCnt += dao.update("svcStnd.nutr.ddRcmdEatStndMng.updateTiDdRcmdEatStndList", info);
			}else if( "D".equals(info.get("crud"))){
				saveCnt += dao.delete("svcStnd.nutr.ddRcmdEatStndMng.deleteTiDdRcmdEatStndList", info);
			}
		}
		if(saveCnt == 0){
			throw new BizException("ECOM999", new String[]{"비만기준정보 저장이 실패하였습니다"});
		}
	}


	
}
	
