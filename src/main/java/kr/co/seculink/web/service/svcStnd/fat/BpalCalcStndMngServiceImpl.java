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
@Service("bpalCalcStndMngService")
public class BpalCalcStndMngServiceImpl implements BpalCalcStndMngService
{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;
	
    //휴식대사량_계산_기준_리스트 조회
	public List<Map<String, String>> searchBpalCalcStndList(Map<String, String> params) throws BizException
	{
		List<Map<String, String>> result = dao.selectList("svcStnd.fat.bpalCalcStndMng.selectBpalCalcStndList", params);
		return result;
	}
	
	//행추가_행삭제 저장
	public void saveBpalStnd(Map<String, Object> params) throws BizException
	{
		int saveCnt = 0;

		List<Map<String, String>> gridDate = (List<Map<String, String>>) params.get("gridList");

		for(Map<String,String> info:gridDate){
			log.debug("crud         : " +  info.get("crud"));

			if( "C".equals(info.get("crud"))){
				saveCnt += dao.insert("svcStnd.fat.bpalCalcStndMng.insertTiBpalCalcStndList", info);
			}else if( "U".equals(info.get("crud"))){
				saveCnt += dao.update("svcStnd.fat.bpalCalcStndMng.updateTiBpalCalcStndList", info);
			}else if( "D".equals(info.get("crud"))){
				saveCnt += dao.delete("svcStnd.fat.bpalCalcStndMng.deleteTiBpalCalcStndList", info);
			}
		}
		if(saveCnt == 0){
			throw new BizException("ECOM999", new String[]{"비만기준정보 저장이 실패하였습니다"});
		}
	}


	
}
	
