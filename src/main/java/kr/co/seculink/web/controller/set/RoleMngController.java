package kr.co.seculink.web.controller.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.seculink.domain.RtnMsg;
import kr.co.seculink.exception.BizException;
import kr.co.seculink.util.GEUtil;
import kr.co.seculink.web.excel.ExcelConstant;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RoleMngController
{
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;
	

	@ResponseBody
	@RequestMapping("/set/roleMng/searchDupRoleCd")
	public RtnMsg  searchDupRoleCd(@RequestBody(required=false)Map<String, String> params) throws BizException
	{	RtnMsg vo = new RtnMsg();
     	Map<String, String> rtnMap = new HashMap<>();
     	
		List<Map<String, String>> result = dao.selectList("set.roleMng.selectDupRoleCd", params);
		
		
	
		if ( result.size() == 0) {
			rtnMap.put("existsYn", "N");
			}
		else {
			rtnMap.put("existsYn", "Y");
		}
		
		vo.setRtnData(rtnMap);
		
		return vo;
	}
	
	@ResponseBody
	@RequestMapping("/set/roleMng/searchRoleList.ab")
	public RtnMsg searchRoleList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{		
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		List<Map<String, String>> result = dao.selectList("set.roleMng.searchTcRoleBaseList", params);
		rtnMap.put("result", result);
		
		RtnMsg vo = new RtnMsg();
		
		if ( !GEUtil.isEmpty(params.get("paging")) ) {
			params.put("paging", "N");
			vo.setTotalCount(((List)dao.selectList("set.roleMng.searchTcRoleBaseList", params)).size());
		}
		
		vo.setRtnData(rtnMap, params);
		
		return vo;
	}
	
	@ResponseBody
	@RequestMapping("/set/roleMng/searchRoleInfo.ab")
	public RtnMsg searchRoleInfo(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		Map<String, String> result = dao.selectOne("set.roleMng.searchTcRoleBaseList", params);
		
		rtnMap.put("result", result);
		vo.setRtnData(rtnMap);
				
		return vo; 
	}
	
	
	@ResponseBody
	@RequestMapping("/set/roleMng/saveRole.ab")
	public RtnMsg saveRole(@RequestBody(required=false) Map<String, String> params) throws BizException
	{	
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		int saveCnt = 0;
		
		if ( "C".equals(params.get("crud")) ) {
			saveCnt = dao.insert("set.roleMng.insertTcRoleBase", params);
		} else if ( "U".equals(params.get("crud")) ) {
			saveCnt = dao.update("set.roleMng.updateTcRoleBase", params);
		} else if ( "D".equals(params.get("crud")) ) {
			saveCnt = dao.delete("set.roleMng.deleteTcRoleBase", params);
		}
			
		if ( saveCnt == 0 ) {
			throw new BizException("ECOM999", new String[]{"사용자 저장이 실패하였습니다."});
		}
		
		rtnMap.put("result", params);
		vo.setRtnData(rtnMap);
		
		return vo;
	}
	
	@ResponseBody
	@RequestMapping("/set/roleMng/searchRoleList/excel.ab")
	public ModelAndView downloadExcel(@RequestBody(required=false) Map<String, String> params) throws BizException
	{	
		params.put("paging", "N");
		List<Map<String, String>> result = dao.selectList("set.roleMng.searchTcRoleBaseList", params);
		
		return new ModelAndView("excelXlsView", getExcelMap(result));
	}
	
	private Map<String, Object> getExcelMap(List<Map<String, String>> list)
    {
        String [] arrHeader = {"역할코드","역할명","역할구분","알림수준","역할설명"};
        List<String> headerList = Arrays.asList(arrHeader);

        List<List<String>> dataList = new ArrayList<List<String>>();
        List<String> data;
        
        for ( Map<String, String> info : list )
        {
            data = new ArrayList<String>();
            data.add(info.get("roleCd"));
            data.add(info.get("roleNm"));
            data.add(info.get("roleDivNm"));
            data.add(info.get("notiLevlNm"));
            data.add(info.get("roleDesc"));
            dataList.add(data);
        }

        Map<String, Object> map = new HashMap<>();
        map.put(ExcelConstant.FILE_NAME, "excel");
        map.put(ExcelConstant.HEAD, headerList);
        map.put(ExcelConstant.BODY, dataList);
        return map;
    }
}
