package kr.co.seculink.web.controller.svcStnd.grow;

import kr.co.seculink.domain.RtnMsg;
import kr.co.seculink.exception.BizException;
import kr.co.seculink.util.GEUtil;
import kr.co.seculink.web.excel.ExcelConstant;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Controller
public class GrowJudgStndMngController
{

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate dao;

	@ResponseBody
	@RequestMapping("/svcStnd/grow/growJudgStndMng/searchGrowJudgList.ab")

	public RtnMsg searchGrowJudgList(@RequestBody(required = false) Map<String, String> params) throws BizException
	{

		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();


		List<Map<String, String>> result = dao.selectList("svcStnd.grow.growJudgStndMng.searchGrowJudgList", params);
		rtnMap.put("result", result);


		if (!GEUtil.isEmpty(params.get("paging"))) {
			params.put("paging", "N");
			vo.setTotalCount(((List)dao.selectList("svcStnd.grow.growJudgStndMng.searchGrowJudgList", params)).size());
		}

		vo.setRtnData(rtnMap, params);

		return vo;
	}

	@ResponseBody
	@RequestMapping("/svcStnd/grow/growJudgStndMng/searchGrowJudgList/excel.ab")
	public ModelAndView downloadExcel(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		params.put("paging", "N");
		List<Map<String, String>> result = dao.selectList("svcStnd.grow.growJudgStndMng.searchGrowJudgList", params);

		return new ModelAndView("excelXlsView", getExcelMap(result));
	}

	private Map<String, Object> getExcelMap(List<Map<String, String>> list)
	{
		String [] arrHeader = {"성장판정코드","성장판정코드명","성장지수_FORM","성장지수_TO","요약내용","상세내용","등록일자","등록시각","등록사용자ID","수정일자","수정시각","수정사용자ID"};
		List<String> headerList = Arrays.asList(arrHeader);

		List<List<String>> dataList = new ArrayList<List<String>>();
		List<String> data;

		for ( Map<String, String> info : list )
		{
			data = new ArrayList<String>();
			data.add(String.valueOf(info.get("growJudgCd")));
			data.add(String.valueOf(info.get("growJudgNm")));
			data.add(String.valueOf(info.get("gidxFr")));
			data.add(String.valueOf(info.get("gidxTo")));
			data.add(String.valueOf(info.get("smryCntn")));
			data.add(String.valueOf(info.get("specCntn")));
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

	@ResponseBody
	@RequestMapping("/svcStnd/grow/growJudgStndMng/searchGrowJudgStndInfo.ab")

	public RtnMsg searchGrowJudgStndInfo(@RequestBody(required = false) Map<String, String> params) throws BizException
	{

		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();


		List<Map<String, String>> result = dao.selectList("svcStnd.grow.growJudgStndMng.searchGrowJudgList", params);

		if(result.size() > 0 ){
			rtnMap.put("result", result.get(0));
		}

		vo.setRtnData(rtnMap, params);

		return vo;
	}

	@ResponseBody
	@RequestMapping("/svcStnd/grow/growJudgStndMng/saveInfo.ab")
	public RtnMsg saveInfo(@RequestBody(required=false) Map<String, Object> params) throws BizException {
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		if("C".equals(params.get("crud"))) dao.insert("svcStnd.grow.growJudgStndMng.insertTiGrowJudgStnd",params);
		else if("U".equals(params.get("crud"))) dao.insert("svcStnd.grow.growJudgStndMng.updateTiGrowJudgStnd",params);
		else if("D".equals(params.get("crud"))) dao.insert("svcStnd.grow.growJudgStndMng.deleteTiGrowJudgStnd",params);

		rtnMap.put("result", params);
		vo.setRtnData(rtnMap,null);

		return vo;
	}
}
