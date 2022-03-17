package kr.co.seculink.web.service.svcStnd.grow;

import kr.co.seculink.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("growStndMngService")
public class GrowStndMngServiceImpl implements GrowStndMngService
{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;

	//성장_기준_리스트 조회
	public List<Map<String, String>> growStndList(Map<String, String> params) throws BizException
	{
		List<Map<String, String>> result = dao.selectList("svcStnd.grow.growStndMng.searchGrowStndList", params);
		return result;
	}
	//행추가_행삭제 저장
	@Transactional
	public void saveGrowStnd(Map<String, Object> params) throws BizException
	{
		int saveCnt = 0;

		List<Map<String, String>> gridDate = (List<Map<String, String>>) params.get("gridList");

		for(Map<String,String> info:gridDate){
			log.debug("crud         : " +  info.get("crud"));

			if( "C".equals(info.get("crud"))){
				saveCnt += dao.insert("svcStnd.grow.growStndMng.insertTiGrowStndList", info);
			}else if( "U".equals(info.get("crud"))){
				saveCnt += dao.update("svcStnd.grow.growStndMng.updateTiGrowStndList", info);
			}else if( "D".equals(info.get("crud"))){
				saveCnt += dao.delete("svcStnd.grow.growStndMng.deleteTiGrowStndList", info);
			}
		}
		if(saveCnt == 0){
			throw new BizException("ECOM999", new String[]{"성장기준정보 저장이 실패하였습니다"});
		}
	}


}
