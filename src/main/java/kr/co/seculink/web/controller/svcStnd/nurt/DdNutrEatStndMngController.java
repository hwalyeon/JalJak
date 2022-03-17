package kr.co.seculink.web.controller.svcStnd.nurt;

import kr.co.seculink.domain.RtnMsg;
import kr.co.seculink.exception.BizException;
import kr.co.seculink.util.GEUtil;
import kr.co.seculink.web.excel.ExcelConstant;
import kr.co.seculink.web.service.svcStnd.nutr.DdNutrEatStndMngService;
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
public class DdNutrEatStndMngController
{
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;

	@Autowired
	private DdNutrEatStndMngService ddNutrEatStndMngService;

	//일일_영양소_섭취_기준_리스트 조회
	@ResponseBody
	@RequestMapping("/svcStnd/nutr/ddNutrEatStndMng/searchDdNutrEatStndList.ab")
	public RtnMsg searchDdNutrEatStndList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		List<Map<String, String>> result = ddNutrEatStndMngService.searchDdNutrEatStndList(params);
		if ( !GEUtil.isEmpty(params.get("paging")) ) {
			params.put("paging", "N");
			vo.setTotalCount(((List)ddNutrEatStndMngService.searchDdNutrEatStndList(params)).size());
		}
		rtnMap.put("result", result);
		vo.setRtnData(rtnMap, params);

		return vo;
	}

	//영양소_코드_명_리스트 조회
	@ResponseBody
	@RequestMapping("/svcStnd/nutr/ddNutrEatStndMng/searchNutrCdNmList.ab")
	public RtnMsg searchNutrCdNmList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		List<Map<String, String>> result = ddNutrEatStndMngService.searchNutrCdNmList(params);

		rtnMap.put("result", result);
		vo.setRtnData(rtnMap, params);

		return vo;
	}


	//행추가_행삭제_저장
	@ResponseBody
	@RequestMapping("/svcStnd/nutr/ddNutrEatStndMng/saveDdNutrEatStnd.ab")
	public RtnMsg saveDdNutrEatStnd(@RequestBody(required = false)Map<String,Object>params)throws BizException {
	
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		ddNutrEatStndMngService.saveDdNutrEatStnd(params);

	
		rtnMap.put("result", params);
		vo.setRtnData(rtnMap, params);

		return vo;
	}
	//일일_영양소_섭취_기준_리스트 엑셀다운로드
	@ResponseBody
	@RequestMapping("/svcStnd/nutr/ddNutrEatStndMng/searchDdNutrEatStndList/excel.ab")
	public ModelAndView downloadExcel(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		params.put("paging", "N");

		List<Map<String, String>> resultHeader = ddNutrEatStndMngService.searchNutrCdNmList(params);

		List<Map<String, String>> result = dao.selectList("svcStnd.nutr.ddNutrEatStndMng.selectDdNutrEatStndList", params);
		return new ModelAndView("excelXlsView", getExcelMap(result,resultHeader ));
	}

	private Map<String, Object> getExcelMap(List<Map<String, String>> list, List<Map<String, String>> header)
	{
		//String [] arrHeader = {"성별","나이(년수)"};

		List<String> headerList = new ArrayList<>();
		headerList.add("성별");
		headerList.add("나이(년수)");
		if(header.size() > 0){
			for(Map<String, String> headerInfo : header){
				headerList.add(headerInfo.get("nutrNm") + " ( " +  headerInfo.get("nutrCd") + " )");
			}
		}


		List<List<String>> dataList = new ArrayList<List<String>>();
		List<String> data;

		for ( Map<String, String> info : list )
		{
			data = new ArrayList<String>();
			data.add(info.get("sexCd"));
			data.add(String.valueOf(info.get("ageYcnt")));
			data.add(String.valueOf(info.get("cal")));
			data.add(String.valueOf(info.get("prtn")));
			data.add(String.valueOf(info.get("fidx")));
			data.add(String.valueOf(info.get("carb")));
			data.add(String.valueOf(info.get("dfib")));
			data.add(String.valueOf(info.get("ca")));
			data.add(String.valueOf(info.get("fe")));
			data.add(String.valueOf(info.get("mg")));
			data.add(String.valueOf(info.get("na")));
			data.add(String.valueOf(info.get("zn")));
			data.add(String.valueOf(info.get("vitD3")));
			data.add(String.valueOf(info.get("vitB")));
			data.add(String.valueOf(info.get("vitB1")));
			data.add(String.valueOf(info.get("vitB2")));
			data.add(String.valueOf(info.get("nia")));
			data.add(String.valueOf(info.get("dfe")));
			data.add(String.valueOf(info.get("vitB12")));
			data.add(String.valueOf(info.get("amno")));
			data.add(String.valueOf(info.get("ile")));
			data.add(String.valueOf(info.get("leu")));
			data.add(String.valueOf(info.get("val")));
			data.add(String.valueOf(info.get("chl")));
			data.add(String.valueOf(info.get("fapu")));
			data.add(String.valueOf(info.get("epa")));
			data.add(String.valueOf(info.get("dha")));
			data.add(String.valueOf(info.get("epaDha")));

			dataList.add(data);
		}

		Map<String, Object> map = new HashMap<>();
		map.put(ExcelConstant.FILE_NAME, "excel");
		map.put(ExcelConstant.HEAD, headerList);
		map.put(ExcelConstant.BODY, dataList);
		return map;
	}
}
