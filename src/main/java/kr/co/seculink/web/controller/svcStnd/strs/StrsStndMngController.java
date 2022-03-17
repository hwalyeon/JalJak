package kr.co.seculink.web.controller.svcStnd.strs;

import kr.co.seculink.domain.RtnMsg;
import kr.co.seculink.exception.BizException;
import kr.co.seculink.util.GEUtil;
import kr.co.seculink.web.excel.ExcelConstant;
import kr.co.seculink.web.service.svcStnd.strs.StrsStndMngService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Controller
public class StrsStndMngController
{

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate dao;

	@Autowired
	private StrsStndMngService strsStndMngService;

	@ResponseBody
	@RequestMapping("/svcStnd/strs/strsStndMng/searchStrsList.ab")

	public RtnMsg searchStrsList(@RequestBody(required = false) Map<String, String> params) throws BizException
	{
		System.out.println("test1");
		RtnMsg vo = new RtnMsg();
		System.out.println("test2");
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		System.out.println("test3");

		List<Map<String, String>> result = dao.selectList("svcStnd.strs.strsStndMng.searchStrsList", params);
		System.out.println("test4");
		rtnMap.put("result", result);
		System.out.println("test5");

		if (!GEUtil.isEmpty(params.get("paging"))) {
			params.put("paging", "N");
			vo.setTotalCount(((List)dao.selectList("svcStnd.strs.strsStndMng.searchStrsList", params)).size());
		}

		vo.setRtnData(rtnMap, params);

		return vo;
	}

	@ResponseBody
	@RequestMapping("/svcStnd/strs/strsStndMng/searchCdSpecList.ab")
	public RtnMsg searchCdSpecList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		List<Map<String, String>> result = dao.selectList("svcStnd.strs.strsStndMng.selectTcCdSpec", params);
		rtnMap.put("result", result);

		if ( !GEUtil.isEmpty(params.get("paging")) ) {
			params.put("paging", "N");
			vo.setTotalCount(((List)dao.selectList("svcStnd.strs.strsStndMng.selectTcCdSpec", params)).size());
		}

		vo.setRtnData(rtnMap, params);
		return vo;
	}

	@ResponseBody
	@RequestMapping("/svcStnd/strs/strsStndMng/searchStrsList/excel.ab")
	public ModelAndView downloadExcel(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		params.put("paging", "N");
		List<Map<String, String>> result = dao.selectList("svcStnd.strs.strsStndMng.searchStrsList", params);

		return new ModelAndView("excelXlsView", getExcelMap(result));
	}

	private Map<String, Object> getExcelMap(List<Map<String, String>> list)
	{
		String [] arrHeader = {"정신적스트레스상태코드","정신적스트레스상태명","선택적스트레스상태코드","선택적스트레스상태명","스트레스판정내용","등록일자","등록시각","등록사용자ID","수정일자","수정시각","수정사용자ID"};
		List<String> headerList = Arrays.asList(arrHeader);

		List<List<String>> dataList = new ArrayList<List<String>>();
		List<String> data;

		for ( Map<String, String> info : list )
		{
			data = new ArrayList<String>();
			data.add(String.valueOf(info.get("mentStrsStatCd")));
			data.add(String.valueOf(info.get("mentStrsStatNm")));
			data.add(String.valueOf(info.get("physStrsStatCd")));
			data.add(String.valueOf(info.get("physStrsStatNm")));
			data.add(String.valueOf(info.get("strsJudgCntn")));
			data.add(info.get("regDt"));
			data.add(info.get("regTm"));
			data.add(info.get("regUserId"));
			data.add(info.get("uptDt"));
			data.add(info.get("uptTm"));
			data.add(info.get("uptUserId"));
			dataList.add(data);
		}

		Map<String, Object> map = new HashMap<>();
		map.put(ExcelConstant.FILE_NAME, "excel");
		map.put(ExcelConstant.HEAD, headerList);
		map.put(ExcelConstant.BODY, dataList);
		return map;
	}

	/*@ResponseBody
	@RequestMapping("/svcStnd/strs/strsStndMng/saveStrsStnd.ab")
	public RtnMsg saveStrsStnd(@RequestBody(required=false) Map<String, Object> params) throws BizException {

		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		strsStndMngService.saveStrsList(params);

		rtnMap.put("result", params);
		vo.setRtnData(rtnMap,null);

		return vo;
	}*/
}
