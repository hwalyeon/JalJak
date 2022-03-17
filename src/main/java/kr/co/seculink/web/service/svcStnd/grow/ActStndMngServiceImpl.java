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
@Service("actStndMngService")
public class ActStndMngServiceImpl implements ActStndMngService
{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;
	
    //활동_기준_리스트 조회
	public List<Map<String, String>> actStndList(Map<String, String> params) throws BizException
	{
		List<Map<String, String>> result = dao.selectList("svcStnd.grow.actStndMng.searchActStndList", params);
		return result;
	}
    //활동_분류_코드_리스트 조회
	public List<Map<String, String>> actClssCdList(Map<String, String> params) throws BizException
	{
		List<Map<String, String>> result = dao.selectList("svcStnd.grow.actStndMng.searchActClssCdList", params);
		return result;
	}
	//행추가_행삭제 저장
	@Transactional
	public void saveActStnd(Map<String, Object> params) throws BizException
	{
		int saveCnt = 0;

		List<Map<String, String>> gridDate = (List<Map<String, String>>) params.get("gridList");

		for(Map<String,String> info:gridDate){
			log.debug("crud         : " +  info.get("crud"));

			if( "C".equals(info.get("crud"))){
				saveCnt += dao.insert("svcStnd.grow.actStndMng.insertTiActStndList", info);
			}else if( "U".equals(info.get("crud"))){
				saveCnt += dao.update("svcStnd.grow.actStndMng.updateTiActStndList", info);
			}else if( "D".equals(info.get("crud"))){
				saveCnt += dao.delete("svcStnd.grow.actStndMng.deleteTiActStndList", info);
			}
		}
		if(saveCnt == 0){
			throw new BizException("ECOM999", new String[]{"성장기준정보 저장이 실패하였습니다"});
		}
	}

}
