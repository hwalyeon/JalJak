package kr.co.seculink.web.service.set;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.co.seculink.exception.BizException;

public interface UserMngService
{
	public List<Map<String, String>> searchUserList(Map<String, String> params) throws BizException;
	
	public Map<String, String> searchUserInfo(Map<String, String> params) throws BizException;
	
	public void saveAcdm(Map<String, String> params) throws BizException;
	
	public void saveLctr(MultipartFile uploadImg, Map<String, String> params) throws BizException;
	
	public void saveStdt(MultipartFile profImg, List<MultipartFile> uploadImgList, List<Map<String, String>> delImgList, Map<String, String> params) throws BizException;
	
	public void saveUser(Map<String, String> params) throws BizException;
	
	public void updateUserPw(Map<String, String> params) throws BizException;
	
	public Map<String, String> searchDupUserId(Map<String, String> params) throws BizException;
}
