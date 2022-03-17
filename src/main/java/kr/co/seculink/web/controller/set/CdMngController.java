package kr.co.seculink.web.controller.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CdMngController
{
	@Autowired
	private SqlSessionTemplate dao;

	

	@ResponseBody
	@RequestMapping("/set/cdMng/searchDuprCdSpec")
	public RtnMsg  searchDuprCdSpec(@RequestBody(required=false)Map<String, String> params) throws BizException
	{	RtnMsg vo = new RtnMsg();
     	Map<String, String> rtnMap = new HashMap<>();
     	
    		Map<String, String> result = dao.selectOne("set.cdMng.selectDuprCdSpec", params);
    	   
    		
    
	    	
    		if (params.get("dataCk").equals("N") ||  result == null || GEUtil.isEmpty(result.get("cdGrp"))  && GEUtil.isEmpty(result.get("cdGrpNm"))) {
    			rtnMap.put("existsYn", "N");
    			
    			}
    		else
    		{
    			rtnMap.put("existsYn", "Y");		
    		}
    		
    		vo.setRtnData(rtnMap);
    		
    		return vo;
    	
	}
	
	
	@ResponseBody
	@RequestMapping("/set/cdMng/searchDupCdGrp")
	public RtnMsg  searchDupUserId(@RequestBody(required=false)Map<String, String> params) throws BizException
	{	RtnMsg vo = new RtnMsg();
     	Map<String, String> rtnMap = new HashMap<>();

		Map<String, String> result = dao.selectOne("set.cdMng.searchTcCdGrp", params);
	
	
		if ( result == null || GEUtil.isEmpty(result.get("cdGrp"))  && GEUtil.isEmpty(result.get("cdVal"))) {
			rtnMap.put("existsYn", "N");
			}
		else {
			rtnMap.put("existsYn", "Y");
		}
		
		vo.setRtnData(rtnMap);
		
		return vo;
    }
	
	
	@ResponseBody
	@RequestMapping("/set/cdMng/searchGrpDivList")
	public RtnMsg searchGrpDivList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		List<Map<String, String>> result = dao.selectList("set.cdMng.selectTcCdDivGrp", params);
		rtnMap.put("result", result);
		
		if ( !GEUtil.isEmpty(params.get("paging")) ) {
			params.put("paging", "N");
			vo.setTotalCount(((List)dao.selectList("set.cdMng.selectTcCdGrp", params)).size());
		}
		
		vo.setRtnData(rtnMap, params);
		
		return vo;
	}
	

	@ResponseBody
	@RequestMapping("/set/cdMng/searchCdGrpDivList")
	public RtnMsg searchCdGrpDivList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		List<Map<String, String>> result = dao.selectList("set.cdMng.selectCdGrpDivList", params);
		
		rtnMap.put("result", result);
		
		/*
		 * if ( !GEUtil.isEmpty(params.get("paging")) ) { params.put("paging", "N");
		 * vo.setTotalCount(((List)dao.selectList("set.cdMng.selectTcCdGrp",
		 * params)).size()); }
		 */
		vo.setRtnData(rtnMap, params);
		
		return vo;
	}
	@ResponseBody
	@RequestMapping("/set/cdMng/searchCdGrpList.ab")
	public RtnMsg searchCdGrpList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		List<Map<String, String>> result = dao.selectList("set.cdMng.selectTcCdGrp", params);
		rtnMap.put("result", result);
		
		if ( !GEUtil.isEmpty(params.get("paging")) ) {
			params.put("paging", "N");
			vo.setTotalCount(((List)dao.selectList("set.cdMng.selectTcCdGrp", params)).size());
		}
		
		vo.setRtnData(rtnMap, params);
		
		return vo;
	}@ResponseBody
@RequestMapping("/set/cdMng/searchCdSpecList.ab")
public RtnMsg searchCdSpecList(@RequestBody(required=false) Map<String, String> params) throws BizException
{
	RtnMsg vo = new RtnMsg();
	Map<String, Object> rtnMap = new HashMap<String, Object>();

	List<Map<String, String>> result = dao.selectList("set.cdMng.selectTcCdSpec", params);
	rtnMap.put("result", result);

	if ( !GEUtil.isEmpty(params.get("paging")) ) {
		params.put("paging", "N");
		vo.setTotalCount(((List)dao.selectList("set.cdMng.selectTcCdSpec", params)).size());
	}

	vo.setRtnData(rtnMap, params);
	return vo;
}
	

	
	@ResponseBody
	@RequestMapping("/set/cdMng/saveCdGrp.ab")
	public RtnMsg saveCdGrp(@RequestBody(required=false) Map<String, String> params) throws BizException
	{	
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		int saveCnt = 0;
		
		if ( "C".equals(params.get("crud")) ) {
			saveCnt = dao.insert("set.cdMng.insertTcCdGrp", params);
		} else if ( "U".equals(params.get("crud")) ) {
			saveCnt = dao.update("set.cdMng.updateTcCdGrp", params);
		} else if ( "D".equals(params.get("crud")) ) {
			saveCnt = dao.delete("set.cdMng.deleteTcCdSpec", params);
			saveCnt = dao.delete("set.cdMng.deleteTcCdGrp", params);
		}
			
		if ( saveCnt == 0 ) {
			throw new BizException("ECOM999", new String[]{"공통코드 저장이 실패하였습니다."});
		}
		
		rtnMap.put("result", params);
		vo.setRtnData(rtnMap);
		
		return vo;
	}
	
	@ResponseBody
	@RequestMapping("/set/cdMng/saveCdSpec.ab")
	public RtnMsg saveCdSpec(@RequestBody(required=false) Map<String, String> params) throws BizException
	{	
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		int saveCnt = 0;
		
		if ( "C".equals(params.get("crud")) ) {
			saveCnt = dao.insert("set.cdMng.insertTcCdSpec", params);
		} else if ( "U".equals(params.get("crud")) ) {
			saveCnt = dao.update("set.cdMng.updateTcCdSpec", params);
		} else if ( "D".equals(params.get("crud")) ) {
			saveCnt = dao.delete("set.cdMng.deleteTcCdSpec", params);
		}
			
		if ( saveCnt == 0 ) {
			throw new BizException("ECOM999", new String[]{"공통코드 저장이 실패하였습니다."});
		}
		
		rtnMap.put("result", params);
		vo.setRtnData(rtnMap);
		
		return vo;
	}
	
	@ResponseBody
	@RequestMapping("/set/cdMng/searchCdGrpList/excel.ab")
	public ModelAndView downloadExcelCdGrp(@RequestBody(required=false) Map<String, String> params) throws BizException
	{	
		params.put("paging", "N");
		List<Map<String, String>> result = dao.selectList("set.cdMng.selectTcCdGrp", params);
		
		return new ModelAndView("excelXlsView", getExcelMapCdGrp(result));
	}
	
	private Map<String, Object> getExcelMapCdGrp(List<Map<String, String>> list)
    {
        String [] arrHeader = {"코드그룹","코드그룹명","사용여부"};
        List<String> headerList = Arrays.asList(arrHeader);

        List<List<String>> dataList = new ArrayList<List<String>>();
        List<String> data;
        
        for ( Map<String, String> info : list )
        {
            data = new ArrayList<String>();
            data.add(info.get("cdGrp"));
            data.add(info.get("cdGrpNm"));
            data.add(info.get("useYn"));
            dataList.add(data);
        }

        Map<String, Object> map = new HashMap<>();
        map.put(ExcelConstant.FILE_NAME, "excel");
        map.put(ExcelConstant.HEAD, headerList);
        map.put(ExcelConstant.BODY, dataList);
        return map;
    }
	
	@ResponseBody
	@RequestMapping("/set/cdMng/searchCdSpecList/excel.ab")
	public ModelAndView downloadExcelCdSpec(@RequestBody(required=false) Map<String, String> params) throws BizException
	{	
		params.put("paging", "N");
		List<Map<String, String>> result = dao.selectList("set.cdMng.selectTcCdSpec", params);
		
		return new ModelAndView("excelXlsView", getExcelMapCdSpec(result));
	}
	
	private Map<String, Object> getExcelMapCdSpec(List<Map<String, String>> list)
    {
        String [] arrHeader = {"코드그룹","코드그룹명","코드값","코드명","정렬순서","사용여부"};
        List<String> headerList = Arrays.asList(arrHeader);

        List<List<String>> dataList = new ArrayList<List<String>>();
        List<String> data;
        
        for ( Map<String, String> info : list )
        {
            data = new ArrayList<String>();
            data.add(info.get("cdGrp"));
            data.add(info.get("cdGrpNm"));
            data.add(info.get("cdVal"));
            data.add(info.get("cdNm"));
            data.add(info.get("sortOrd"));
            data.add(info.get("useYn"));
            dataList.add(data);
        }

        Map<String, Object> map = new HashMap<>();
        map.put(ExcelConstant.FILE_NAME, "excel");
        map.put(ExcelConstant.HEAD, headerList);
        map.put(ExcelConstant.BODY, dataList);
        return map;
    }
}
