package kr.co.seculink.web.service.cmon;

import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import kr.co.seculink.exception.BaseException;

@Service("authService")
public class AuthServiceImpl implements AuthService {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;
	
	@Override
	public Integer regUser(Map<String, Object> params) throws BaseException {
		
		return dao.insert("USER_BASE.insert", params);
	}
	

	@Override
	public Integer regFcmTokn(Map<String, Object> params) throws BaseException {
		
		return dao.update("USER_FCM.merge", params);
	}
}
