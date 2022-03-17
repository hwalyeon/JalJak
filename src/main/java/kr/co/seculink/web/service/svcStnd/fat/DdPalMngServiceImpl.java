package kr.co.seculink.web.service.svcStnd.fat;

import kr.co.seculink.exception.BizException;
import kr.co.seculink.web.service.cmon.FileService;
import kr.co.seculink.web.service.svcStnd.strs.StrsStndMngService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("ddPalMngService")
public class DdPalMngServiceImpl implements DdPalMngService
{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;

	public List<Map<String, String>> searchDdPalList(Map<String, String> params) throws BizException
	{
		List<Map<String, String>> result = dao.selectList("svcStnd.fat.ddPalMng.searchDdPalList", params);

		return result;
	}

	//행추가_행삭제 저장
	@Transactional
	public void saveDdPal(Map<String, Object> params) throws BizException
	{
		int saveCnt = 0;

		List<Map<String, String>> gridDate = (List<Map<String, String>>) params.get("gridList");

		for(Map<String,String> info:gridDate){
			log.debug("crud         : " +  info.get("crud"));

			if( "C".equals(info.get("crud"))){
				saveCnt += dao.insert("svcStnd.fat.ddPalMng.insertTiDdPalList", info);
			}else if( "U".equals(info.get("crud"))){
				saveCnt += dao.update("svcStnd.fat.ddPalMng.updateTiDdPalList", info);
			}else if( "D".equals(info.get("crud"))){
				saveCnt += dao.delete("svcStnd.fat.ddPalMng.deleteTiDdPalList", info);
			}
		}
		if(saveCnt == 0){
			throw new BizException("ECOM999", new String[]{"비만기준정보 저장이 실패하였습니다"});
		}
	}

}

