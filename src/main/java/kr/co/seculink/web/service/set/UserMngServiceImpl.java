package kr.co.seculink.web.service.set;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.seculink.exception.BizException;
import kr.co.seculink.util.GEUtil;
import kr.co.seculink.util.XUtil;
import kr.co.seculink.web.model.cmon.TcFileVO;
import kr.co.seculink.web.service.cmon.FileService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userMngService")
public class UserMngServiceImpl implements UserMngService
{
	@Autowired
	private FileService fileService;
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;
	
	public List<Map<String, String>> searchUserList(Map<String, String> params) throws BizException
	{
		List<Map<String, String>> result = dao.selectList("set.userMng.searchTcUserBase", params);
		
		return result;
	}
	
	public Map<String, String> searchUserInfo(Map<String, String> params) throws BizException
	{
		Map<String, String> result = dao.selectOne("set.userMng.searchTcUserBase", params);
		
		return result;
	}
	
	public Map<String, String> searchDupUserId(Map<String, String> params) throws BizException
	{
		Map<String, String> user = dao.selectOne("set.userMng.searchTcUserBase", params);
		
		Map<String, String> rtnMap = new HashMap<>();
		if ( user == null || GEUtil.isEmpty(user.get("userId")) ) {
			rtnMap.put("existsYn", "N");
		} else {
			rtnMap.put("existsYn", "Y");
		}
		
		return rtnMap;
	}
	
	public void updateUserPw(Map<String, String> params) throws BizException
	{
		int saveCnt = dao.update("set.userMng.updateUserPw", params);
		if ( saveCnt != 1 ) {
			throw new BizException("ECOM999", new String[] {"비밀번호 변경에 실패하였습니다."});
		}
	}
	
	@Transactional
	public void saveAcdm(Map<String, String> params) throws BizException
	{
		int saveCnt = 0;
		
//		TcFileVO tcFileVo = new TcFileVO();
//		tcFileVo.setFileNo   (params.get("profImgNo"));
//		tcFileVo.setFileDivCd("LCTR");
//		tcFileVo.setFile     (uploadImg);
//		tcFileVo.setUserId   (params.get("lctrId"));
//		saveProfImg(tcFileVo, params);
		
		String crud = params.get("crud");
		if ( "C".equals(crud) )
		{
			saveCnt = dao.insert("acdm.acdmMng.insertToAcdmBase", params);
		}
		else if ( "U".equals(crud) )
		{
			saveCnt = dao.update("acdm.acdmMng.updateToAcdmBase", params);
		}
		else if ( "D".equals(crud) )
		{
			saveCnt = dao.update("acdm.acdmMng.deleteToAcdmBase", params);
		}
		
		params.put("userId", params.get("acdmId"));
		params.put("userNm", params.get("acdmNm"));
		params.put("acdmYn", "Y");
		saveUser(params);
		
		if ( saveCnt == 0 ) {
			throw new BizException("ECOM999", new String[]{"학원 저장이 실패하였습니다."});
		}
	}
	
	@Transactional
	public void saveLctr(MultipartFile uploadImg, Map<String, String> params) throws BizException
	{
		int saveCnt = 0;
		
		String crud = params.get("crud");
		if ( "C".equals(crud) )
		{
			saveCnt = dao.insert("lect.lctrMng.insertToLctrBase", params);
		}
		else if ( "U".equals(crud) )
		{
			saveCnt = dao.update("lect.lctrMng.updateToLctrBase", params);
		}
		else if ( "D".equals(crud) )
		{
			saveCnt = dao.update("lect.lctrMng.deleteToLctrBase", params);
		}

		saveLctrProfImg(uploadImg, params);
		
		params.put("userId", params.get("lctrId"));
		params.put("userNm", params.get("lctrNm"));
		params.put("lctrYn", "Y");
		saveUser(params);
		
		if ( saveCnt == 0 ) {
			throw new BizException("ECOM999", new String[]{"강사 저장이 실패하였습니다."});
		}
	}
	
	@Transactional
	public void saveStdt(MultipartFile profImg, List<MultipartFile> uploadImgList, List<Map<String, String>> delImgList, Map<String, String> params) throws BizException
	{
		int saveCnt = 0;
		
		String crud = params.get("crud");
		if ( "C".equals(crud) ) {
			saveCnt = dao.insert("clss.stdtMng.insertToStdtBase", params);
		} else if ( "U".equals(crud) ) {
			saveCnt = dao.update("clss.stdtMng.updateToStdtBase", params);
		} else if ( "D".equals(crud) ) {
			saveCnt = dao.update("clss.stdtMng.deleteToStdtBase", params);
		}
		
		saveStdtProfImg(profImg, params);
		
		deleteStdtImg(delImgList);
				
		saveStdtImg(uploadImgList, params);
		
		params.put("userId", params.get("stdtId"));
		params.put("userNm", params.get("stdtNm"));
		params.put("stdtYn", "Y");
		saveUser(params);
		
		if ( saveCnt == 0 ) {
			throw new BizException("ECOM999", new String[]{"학생 저장이 실패하였습니다."});
		}
	}
	
	@Transactional
	public void saveStdtProfImg(MultipartFile uploadImg, Map<String, String> params) throws BizException
	{
		if ( uploadImg != null )
		{
			TcFileVO tcFileVo = new TcFileVO();
			tcFileVo.setFileNo   (params.get("profImgNo"));
			tcFileVo.setFileDivCd("STDT");
			tcFileVo.setFile     (uploadImg);
			tcFileVo.setUserId   (params.get("stdtId"));
			saveProfImg(tcFileVo, params);
		}
		else
		{
			if ( "Y".equals(params.get("profImgDelYn")) ) {
				fileService.delete(params.get("profImgNo"));
				
				TcFileVO tcFileVo = new TcFileVO();
				tcFileVo.setFileNo("");
				tcFileVo.setUserId(params.get("stdtId"));
				dao.update("set.userMng.updateStdtProfImg", tcFileVo);
			}
		}
	}
	
	@Transactional
	public void deleteStdtImg(List<Map<String, String>> delImgList) throws BizException
	{
		if ( delImgList != null )
		{
			TcFileVO fileVo = null;
			for ( Map<String, String> delImgInfo : delImgList )
			{
				fileVo = new TcFileVO();
				fileVo.setFileNo(delImgInfo.get("profImgNo"));
				fileVo.setFileDivCd("STDT_IMAGE");
				if ( fileService.delete(fileVo) )
				{
					dao.delete("clss.stdtMng.deleteToStdtImg", delImgInfo);
				}
			}
		}
	}
	
	@Transactional
	public void saveStdtImg(List<MultipartFile> uploadImgList, Map<String, String> params) throws BizException
	{
		for ( MultipartFile uploadImg : uploadImgList )
		{
			TcFileVO tcFileVo = new TcFileVO();
			tcFileVo.setFileDivCd("STDT_IMAGE");
			tcFileVo.setFile     (uploadImg);
			tcFileVo.setUserId   (params.get("stdtId"));
			fileService.save(tcFileVo);
			
			if ( XUtil.isEmpty(tcFileVo.getFileNo()) ) {
				throw new BizException("EFIL001");
			} else {
				log.debug("fileNo : " + tcFileVo.getFileNo());
				
				Map<String, String> paramMap = new HashMap<>();
				paramMap.put("stdtId"   , params.get("stdtId"));
				paramMap.put("profImgNo", tcFileVo.getFileNo());
				dao.insert("clss.stdtMng.insertToStdtImg", paramMap);
			}
		}
	}
	
	@Transactional
	public void saveLctrProfImg(MultipartFile uploadImg, Map<String, String> params) throws BizException
	{
		if ( uploadImg != null )
		{
			TcFileVO tcFileVo = new TcFileVO();
			tcFileVo.setFileNo   (params.get("profImgNo"));
			tcFileVo.setFileDivCd("LCTR");
			tcFileVo.setFile     (uploadImg);
			tcFileVo.setUserId   (params.get("lctrId"));
			saveProfImg(tcFileVo, params);
		}
	}
	
	@Transactional
	public void saveUser(Map<String, String> params) throws BizException
	{
		int saveCnt = 0;
		
		if ( "C".equals(params.get("crud")) ) {
		
			Map<String, String> existUserParam = new HashMap<>();
			existUserParam.put("userId", (String) params.get("userId"));
			Map<String, String> user = dao.selectOne("set.userMng.searchTcUserBase", existUserParam);
			
			if ( user != null && !GEUtil.isEmpty(user.get("userId")) ) {
				throw new BizException("ECOM999", new String[]{"사용자ID[" + params.get("userId") + "]는 존재합니다."});
			} else {
				saveCnt = dao.insert("set.userMng.insertTcUserBase", params);
			}
		
			String roleCd = "";
			Map<String, String> roleMap = new HashMap<>();
		
			if ( "Y".equals(params.get("acdmYn")) ) {
				Map<String, String> acdmMap = new HashMap<String, String>();
				acdmMap.put("acdmId"       , (String) params.get("userId"));
				acdmMap.put("acdmNm"       , (String) params.get("userNm"));
				acdmMap.put("reprNm"       , (String) params.get("userNm"));
				acdmMap.put("reprTelNo"    , (String) params.get("telNo"));
				acdmMap.put("pchrgNm"      , (String) params.get("userNm"));
				acdmMap.put("pchrgTelNo"   , (String) params.get("telNo"));
				acdmMap.put("pchrgMailAddr", (String) params.get("mailAddr"));
				acdmMap.put("acdmStatCd"   , "10");
				dao.insert("acdm.acdmMng.insertToAcdmBase", acdmMap);
				
				roleCd = "ACDM";
			}
			
			if ( "Y".equals(params.get("lctrYn")) ) {
				Map<String, String> lctrMap = new HashMap<String, String>();
				lctrMap.put("lctrId"    , (String) params.get("userId"));
				lctrMap.put("lctrNm"    , (String) params.get("userNm"));
				lctrMap.put("telNo"     , (String) params.get("telNo"));
				lctrMap.put("mtelNo"    , (String) params.get("mtelNo"));
				lctrMap.put("mailAddr"  , (String) params.get("mailAddr"));
				lctrMap.put("lctrStatCd", "10");
				dao.insert("lect.lctrMng.insertToLctrBase", lctrMap);
				
				roleCd = "LCTR";
			}
	
			if ( "Y".equals(params.get("stdtYn")) ) {
				Map<String, String> stdtMap = new HashMap<String, String>();
				stdtMap.put("stdtId"    , (String) params.get("userId"));
				stdtMap.put("stdtNm"    , (String) params.get("userNm"));
				stdtMap.put("telNo"     , (String) params.get("telNo"));
				stdtMap.put("mtelNo"    , (String) params.get("mtelNo"));
				stdtMap.put("mailAddr"  , (String) params.get("mailAddr"));
				stdtMap.put("stdtStatCd", "10");
				dao.insert("clss.stdtMng.insertToStdtBase", stdtMap);
				
				roleCd = "STDT";
			}
			
			roleMap.put("userId", (String) params.get("userId"));
			roleMap.put("roleCd", roleCd);
			dao.insert("set.userRoleMng.insertTcUserRole", roleMap);
			
		} else if ( "U".equals(params.get("crud")) ) {
			
			saveCnt = dao.update("set.userMng.updateTcUserBase", params);
			
			if ( "Y".equals(params.get("acdmYn")) ) {
				params.put("acdmId", params.get("userId"));
				dao.insert("acdm.acdmMng.updateToAcdmBase", params);
			}
			
			if ( "Y".equals(params.get("lctrYn")) ) {
				params.put("lctrId", params.get("userId"));
				dao.insert("lect.lctrMng.updateToLctrBase", params);
			}
	
			if ( "Y".equals(params.get("stdtYn")) ) {
				params.put("stdtId", params.get("userId"));
				dao.insert("clss.stdtMng.updateToStdtBase", params);
			}
			
		} else if ( "D".equals(params.get("crud")) ) {
			
			Map<String, String> deleteMap = new HashMap<String, String>();
			deleteMap.put("userId", params.get("userId"));
			deleteMap.put("useYn" , "N");
			
			saveCnt = dao.delete("set.userMng.deleteTcUserBase", deleteMap);
			
			if ( "Y".equals(params.get("acdmYn")) ) {
				deleteMap.put("acdmId", params.get("userId"));
				dao.insert("acdm.acdmMng.updateToAcdmBase", deleteMap);
			}
			
			if ( "Y".equals(params.get("lctrYn")) ) {
				deleteMap.put("lctrId", params.get("userId"));
				dao.insert("lect.lctrMng.updateToLctrBase", deleteMap);
			}
	
			if ( "Y".equals(params.get("stdtYn")) ) {
				deleteMap.put("stdtId", params.get("userId"));
				dao.insert("clss.stdtMng.updateToStdtBase", deleteMap);
			}
			
			dao.insert("set.userRoleMng.deleteTcUserRole", deleteMap);
		}
			
		if ( saveCnt == 0 ) {
			throw new BizException("ECOM999", new String[]{"사용자 저장이 실패하였습니다."});
		}
	}
	
	private void saveProfImg(TcFileVO tcFileVo, Map<String, String> params) throws BizException
	{
		String crud = params.get("crud");
		
		if ( "C".equals(crud) )
		{
			if ( tcFileVo.getFile() != null )
			{
				fileService.save(tcFileVo);
				
				if ( XUtil.isEmpty(tcFileVo.getFileNo()) ) {
					throw new BizException("EFIL001");
				} else {
					log.debug("fileNo : " + tcFileVo.getFileNo());
				}
			}
		}
		else if ( "U".equals(crud) )
		{
			if ( tcFileVo.getFile() != null )
			{
				fileService.save(tcFileVo);
				
				if ( XUtil.isEmpty(tcFileVo.getFileNo()) ) {
					throw new BizException("EFIL001");
				} else if ( tcFileVo.getFileNo().equals(params.get("profImgNo")) ) {
					throw new BizException("EFIL001");
				} else {
					log.debug("fileNo : " + tcFileVo.getFileNo());
				}
			}
		}
		else if ( "D".equals(crud) )
		{
			fileService.delete(params.get("profImgNo"));
		}
		
		if ( "STDT".equals(tcFileVo.getFileDivCd()) ) {
			dao.update("set.userMng.updateStdtProfImg", tcFileVo);
		} else if ( "LCTR".equals(tcFileVo.getFileDivCd()) ) {
			dao.update("set.userMng.updateLctrProfImg", tcFileVo);
		} else if ( "ACDM".equals(tcFileVo.getFileDivCd()) ) {
			dao.update("set.userMng.updateAcdmProfImg", tcFileVo);
		} else {
			throw new BizException("ECOM999", new String[] {"파일 구분이 없습니다."});
		}
		
	}
}
