package kr.co.seculink.web.controller.cmon;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.seculink.domain.RtnMsg;
import kr.co.seculink.exception.BizException;
import kr.co.seculink.web.service.set.UserMngService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AccountController
{
	@Autowired
	private UserMngService userMngService;
	
	@ResponseBody
	@RequestMapping("/account/mypage/searchMyInfo")
	public RtnMsg searchMyInfo(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		Map<String, String> userInfo = userMngService.searchUserInfo(params);
		
		rtnMap.put("userInfo", userInfo);
		vo.setRtnData(rtnMap);
				
		return vo;
		
	}
}
