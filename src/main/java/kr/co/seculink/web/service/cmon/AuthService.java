package kr.co.seculink.web.service.cmon;

import java.util.Map;

import kr.co.seculink.exception.BaseException;

public interface AuthService {

	Integer regUser(Map<String, Object> params) throws BaseException;

	Integer regFcmTokn(Map<String, Object> params) throws BaseException;

}
