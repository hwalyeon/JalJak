package kr.co.seculink.web.service.svcStnd.strs;

import kr.co.seculink.exception.BizException;
import kr.co.seculink.util.GEUtil;
import kr.co.seculink.util.XUtil;
import kr.co.seculink.web.model.cmon.TcFileVO;
import kr.co.seculink.web.service.cmon.FileService;
import kr.co.seculink.web.service.svcStnd.dgem.DgemStndMngService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("strsStndMngService")
public class StrsStndMngServiceImpl implements StrsStndMngService
{
	@Autowired
	private FileService fileService;

	@Autowired
	private DgemStndMngService dgemStndMngService;
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;
	
	public List<Map<String, String>> searchStrsList(Map<String, String> params) throws BizException
	{
		List<Map<String, String>> result = dao.selectList("svcStnd.strs.strsStndMng.searchStrsList", params);

		return result;
	}

	public List<Map<String, String>> searchCdSpecList(Map<String, String> params) throws BizException
	{
		List<Map<String, String>> result = dao.selectList("svcStnd.strs.strsStndMng.searchCdSpecList", params);

		return result;
	}

	/*@Transactional
	public void saveStrsList(Map<String, Object> params) throws BizException
	{
		int saveCnt = 0;


		List<Map<String, String>> gridData = (List<Map<String, String>>) params.get("gridList");

		for (Map<String, String> info : gridData) {

			if( "C".equals(info.get("crud"))){
				Map<String, String> rtnData = this.searchmentphysCd(info);
				if("Y".equals(rtnData.get("existsYn"))){
					throw new BizException("ECOM999", new String[]{"정신적 스트레스 코드 : " + rtnData.get("mentStrsStatNm") + ""
							+ "\n 신체적 스트레스 코드 : " +rtnData.get("physStrsStatNm")
							+ "\n 스트레스판정내용"+info.get("strsJudgCntn")
							+ "\n 은 이미 등록 된 코드 입니다."}
							);
				}
				saveCnt += dao.insert("svcStnd.strs.strsStndMng.insertTiStrsStnd", info);
			}else if( "U".equals(info.get("crud"))){
				saveCnt += dao.update("svcStnd.strs.strsStndMng.updateTiStrsStnd", info);
			}else if( "D".equals(info.get("crud"))){
				saveCnt += dao.delete("svcStnd.strs.strsStndMng.deleteTiStrsStnd", info);
			}
		}

		if ( saveCnt == 0 ) {
			throw new BizException("ECOM999", new String[]{"스트레스 상태 코드 저장이 실패하였습니다."});
		}
	}*/


	public Map<String, String> searchmentphysCd(Map<String, String> params) throws BizException
	{

		Map<String, String> result = dao.selectOne("svcStnd.strs.strsStndMng.mentphysCd", params);

		Map<String, String> rtnMap = new HashMap<>();

		if ( result == null || GEUtil.isEmpty(result.get("mentStrsStatCd")) ) {
			rtnMap.put("existsYn", "N");
		} else {
			rtnMap.put("existsYn", "Y");
			rtnMap.put("mentStrsStatNm", result.get("mentStrsStatNm"));
			rtnMap.put("physStrsStatNm", result.get("physStrsStatNm"));
		}

		return rtnMap;
	}

}

