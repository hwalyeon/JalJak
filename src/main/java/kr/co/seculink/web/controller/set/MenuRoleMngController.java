package kr.co.seculink.web.controller.set;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
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
public class MenuRoleMngController
{
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;
	
	@ResponseBody
	@RequestMapping("/set/menuRoleMng/searchMenuRoleList.ab")
	public RtnMsg searchMenuRoleList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		List<Map<String, String>> result = dao.selectList("set.menuRoleMng.searchTcMenuRoleList", params);
		rtnMap.put("result", result);
		
		if ( !GEUtil.isEmpty(params.get("paging")) ) {
			params.put("paging", "N");
			vo.setTotalCount(((List)dao.selectList("set.menuRoleMng.searchTcMenuRoleList", params)).size());
		}
		
		vo.setRtnData(rtnMap, params);
				
		return vo; 
	}
		
	@ResponseBody
	@RequestMapping("/set/menuRoleMng/saveMenuRole.ab")
	public RtnMsg saveMenuRole(@RequestBody(required=false) Map<String, Object> params) throws BizException
	{	
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		
		dao.delete("set.menuRoleMng.deleteTcMenuRole", params);
		
		int saveCnt = 0;
		
		List<Map<String, String>> menuRoleList = (List<Map<String, String>>) params.get("menuRoleList");
		for ( Map<String, String> menuRole : menuRoleList )
		{
			menuRole.put("roleCd", (String) params.get("roleCd"));
			saveCnt = saveCnt + dao.insert("set.menuRoleMng.insertTcMenuRole", menuRole);
		}
					
		if ( saveCnt != menuRoleList.size() ) {
			throw new BizException("ECOM999", new String[]{"메뉴역할 저장이 실패하였습니다."});
		}
		
		rtnMap.put("result", params);
		vo.setRtnData(rtnMap);
		
		return vo;
	}
	
}
