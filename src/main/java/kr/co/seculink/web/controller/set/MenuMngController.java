package kr.co.seculink.web.controller.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.seculink.util.XUtil;
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
public class MenuMngController
{
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;
	
	@ResponseBody
	@RequestMapping("/set/menuMng/searchMenuList.ab")
	public RtnMsg searchMenuList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		List<Map<String, String>> result = dao.selectList("set.menuMng.searchTcMenuBaseList", params);
		rtnMap.put("result", result);
		
		if ( !GEUtil.isEmpty(params.get("paging")) ) {
			params.put("paging", "N");
			vo.setTotalCount(((List)dao.selectList("set.menuMng.searchTcMenuBaseList", params)).size());
		}
		
		vo.setRtnData(rtnMap, params);
				
		return vo; 
	}
	
	@ResponseBody
	@RequestMapping("/set/menuMng/searchMenuInfo.ab")
	public RtnMsg searchMenuInfo(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		Map<String, String> result = dao.selectOne("set.menuMng.searchTcMenuBaseList", params);
		
		List<Map<String, String>> roleList = dao.selectList("set.menuRoleMng.searchTcMenuRoleList", params);
		
		rtnMap.put("result", result);
		rtnMap.put("roleList", roleList);
		vo.setRtnData(rtnMap);
				
		return vo; 
	}
	
	@ResponseBody
	@RequestMapping("/set/menuMng/saveMenu.ab")
	public RtnMsg saveMenu(@RequestBody(required=false) Map<String, Object> params) throws BizException
	{	
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		int saveCnt = 0;

		if(params.get("subUpprMenuNo") != null && !XUtil.isEmpty(params.get("subUpprMenuNo").toString()) ){
			String upprMenuNo = params.get("subUpprMenuNo").toString();
			params.put("upprMenuNo" , upprMenuNo);
		}
	
		if ( "C".equals(params.get("crud")) ) {
			saveCnt = dao.insert("set.menuMng.insertTcMenuBase", params);
		} else if ( "U".equals(params.get("crud")) ) {
			saveCnt = dao.update("set.menuMng.updateTcMenuBase", params);
		} else if ( "D".equals(params.get("crud")) ) {
			saveCnt = dao.delete("set.menuMng.deleteTcMenuBase", params);
		}
			
		if ( saveCnt == 0 ) {
			throw new BizException("ECOM999", new String[]{"메뉴 저장이 실패하였습니다."});
		}
		
		// 메뉴역할 저장
		List<Map<String, String>> roleList = (List<Map<String, String>>) params.get("roleList");

		if ( "C".equals(params.get("crud")) || "U".equals(params.get("crud")) )
		{
			dao.delete("set.menuMng.deleteTcMenuRole", params);
			for ( Map<String, String> role : roleList )
			{
				if ( role == null || GEUtil.isEmpty(role.get("roleCd")) ) 
					continue;
				
				Map<String, String> insertMap = new HashMap<>();
				insertMap.put("menuNo", (String) params.get("menuNo"));
				insertMap.put("roleCd", role.get("roleCd"));
				dao.insert("set.menuRoleMng.insertTcMenuRole", insertMap);
			}
		}
		
		rtnMap.put("result", params);
		vo.setRtnData(rtnMap);
		
		return vo;
	}
	
	@ResponseBody
	@RequestMapping("/set/menuMng/searchUpprMenuList.ab")
	public RtnMsg searchUpprMenuList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{	
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		List<Map<String, String>> result = dao.selectList("set.menuMng.searchUpprMenuList", params);
		
		rtnMap.put("result", result);
		vo.setRtnData(rtnMap);
				
		return vo; 
	}

	@ResponseBody
	@RequestMapping("/set/menuMng/searchMenuList/excel.ab")
	public ModelAndView downloadExcel(@RequestBody(required=false) Map<String, String> params) throws BizException
	{	
		params.put("paging", "N");
		List<Map<String, String>> result = dao.selectList("set.menuMng.searchTcMenuBaseList", params);
		
		return new ModelAndView("excelXlsView", getExcelMap(result));
	}
	
	private Map<String, Object> getExcelMap(List<Map<String, String>> list)
    {
        String [] arrHeader = {"메뉴번호","메뉴명","상위메뉴번호","상위메뉴명","메뉴경로","아이콘정보","메뉴설명"};
        List<String> headerList = Arrays.asList(arrHeader);

        List<List<String>> dataList = new ArrayList<List<String>>();
        List<String> data;
        
        for ( Map<String, String> info : list )
        {
            data = new ArrayList<String>();
            data.add(info.get("menuNo"));
            data.add(info.get("menuNm"));
            data.add(info.get("parentMenuNo"));
            data.add(info.get("parentMenuNm"));
            data.add(info.get("menuUrl"));
            data.add(info.get("iconInfo"));
            data.add(info.get("menuDesc"));
            dataList.add(data);
        }

        Map<String, Object> map = new HashMap<>();
        map.put(ExcelConstant.FILE_NAME, "excel");
        map.put(ExcelConstant.HEAD, headerList);
        map.put(ExcelConstant.BODY, dataList);
        return map;
    }
}
