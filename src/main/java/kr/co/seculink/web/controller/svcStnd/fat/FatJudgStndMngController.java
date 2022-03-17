package kr.co.seculink.web.controller.svcStnd.fat;

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
public class FatJudgStndMngController
{

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate dao;

	@ResponseBody
	@RequestMapping("/svcStnd/fat/fatJudgStndMng/searchFatJudgList.ab")

	public RtnMsg searchFatJudgList(@RequestBody(required = false) Map<String, String> params) throws BizException
	{

		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();


		List<Map<String, String>> result = dao.selectList("svcStnd.fat.fatJudgStndMng.searchFatJudgList", params);
		rtnMap.put("result", result);


		if (!GEUtil.isEmpty(params.get("paging"))) {
			params.put("paging", "N");
			vo.setTotalCount(((List)dao.selectList("svcStnd.fat.fatJudgStndMng.searchFatJudgList", params)).size());
		}

		vo.setRtnData(rtnMap, params);

		return vo;
	}

	@ResponseBody
	@RequestMapping("/svcStnd/fat/fatJudgStndMng/searchFatJudgList/excel.ab")
	public ModelAndView downloadExcel(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		params.put("paging", "N");
		List<Map<String, String>> result = dao.selectList("svcStnd.fat.fatJudgStndMng.searchFatJudgList", params);

		return new ModelAndView("excelXlsView", getExcelMap(result));
	}

	private Map<String, Object> getExcelMap(List<Map<String, String>> list)
	{
		String [] arrHeader = {"비만판정코드","비만판정코드명","비만지수FORM","비만지수TO","현재평가내용","예측평가내용","등록일자","등록시각","등록사용자ID","수정일자","수정시각","수정사용자ID"};
		List<String> headerList = Arrays.asList(arrHeader);

		List<List<String>> dataList = new ArrayList<List<String>>();
		List<String> data;

		for ( Map<String, String> info : list )
		{
			data = new ArrayList<String>();
			data.add(String.valueOf(info.get("fatJudgCd")));
			data.add(String.valueOf(info.get("fatJudgNm")));
			data.add(String.valueOf(info.get("fidxFr")));
			data.add(String.valueOf(info.get("fidxTo")));
			data.add(String.valueOf(info.get("currEvalCntn")));
			data.add(String.valueOf(info.get("prdtEvalCntn")));
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
	@RequestMapping("/svcStnd/fat/fatJudgStndMng/searchFatJudgStndInfo.ab")

	public RtnMsg searchFatJudgStndInfo(@RequestBody(required = false) Map<String, String> params) throws BizException
	{

		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();


		List<Map<String, String>> result = dao.selectList("svcStnd.fat.fatJudgStndMng.searchFatJudgList", params);
		if(result.size() > 0){
			rtnMap.put("result", result.get(0));
		}

		vo.setRtnData(rtnMap, params);

		return vo;
	}

	@ResponseBody
	@RequestMapping("/svcStnd/fat/fatJudgStndMng/saveInfo.ab")
	public RtnMsg saveInfo(@RequestBody(required=false) Map<String, Object> params) throws BizException {
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		if("C".equals(params.get("crud"))) dao.insert("svcStnd.fat.fatJudgStndMng.insertTiFatJudgStnd",params);
		else if("U".equals(params.get("crud"))) dao.insert("svcStnd.fat.fatJudgStndMng.updateTiFatJudgStnd",params);
		else if("D".equals(params.get("crud"))) dao.insert("svcStnd.fat.fatJudgStndMng.deleteTiFatJudgStnd",params);

		rtnMap.put("result", params);
		vo.setRtnData(rtnMap,null);

		return vo;
	}
}
