package kr.co.seculink.web.controller.set;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.seculink.web.service.svcStnd.strs.StrsStndMngService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.seculink.domain.RtnMsg;
import kr.co.seculink.exception.BizException;
import kr.co.seculink.util.GEUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserRoleMngController
{
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;

	@ResponseBody
	@RequestMapping("/set/userRoleMng/searchUserRoleList.ab")
	public RtnMsg searchUserRoleList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		List<Map<String, String>> result = dao.selectList("set.userRoleMng.searchTcUserRoleList", params);
		rtnMap.put("result", result);
		
		if ( !GEUtil.isEmpty(params.get("paging")) ) {
			params.put("paging", "N");
			vo.setTotalCount(((List)dao.selectList("set.userRoleMng.searchTcUserRoleList", params)).size());
		}
		
		vo.setRtnData(rtnMap, params);
				
		return vo; 
	}
		
	@ResponseBody
	@RequestMapping("/set/userRoleMng/saveUserRole.ab")
	public RtnMsg saveUserRole(@RequestBody(required=false) Map<String, Object> params) throws BizException
	{	
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		dao.delete("set.userRoleMng.deleteTcUserRole", params);
		
		int saveCnt = 0;
		
		List<Map<String, String>> userRoleList = (List<Map<String, String>>) params.get("userRoleList");
		for ( Map<String, String> userRole : userRoleList )
		{
			userRole.put("roleCd", (String) params.get("roleCd"));
			saveCnt = saveCnt + dao.insert("set.userRoleMng.insertTcUserRole", userRole);
		}
					
		if ( saveCnt != userRoleList.size() ) {
			throw new BizException("ECOM999", new String[]{"사용자역할 저장이 실패하였습니다."});
		}
		
		rtnMap.put("result", params);
		vo.setRtnData(rtnMap);
		
		return vo;
	}
	
}
