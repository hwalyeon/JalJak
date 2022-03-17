package kr.co.seculink.web.controller.svcStnd.nurt;

import kr.co.seculink.domain.RtnMsg;
import kr.co.seculink.exception.BizException;
import kr.co.seculink.util.GEUtil;
import kr.co.seculink.web.excel.ExcelConstant;
import kr.co.seculink.web.service.svcStnd.nutr.NutrInfoMngService;
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
public class NutrInfoMngController
{
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;

	@Autowired
	private NutrInfoMngService nutrInfoMngService;

	//영양소_정보_리스트 조회
	@ResponseBody
	@RequestMapping("/svcStnd/nutr/nutrInfoMng/searchNutrInfoList.ab")
	public RtnMsg searchNutrInfoList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		List<Map<String, String>> result = nutrInfoMngService.searchNutrInfoList(params);

		if ( !GEUtil.isEmpty(params.get("paging")) ) {
			params.put("paging", "N");
			vo.setTotalCount(((List)nutrInfoMngService.searchNutrInfoList(params)).size());
		}
		rtnMap.put("result", result);
		vo.setRtnData(rtnMap, params);

		return vo;
	}
	//영양소_코드_리스트 조회
	@ResponseBody
	@RequestMapping("/svcStnd/nutr/nutrInfoMng/nutrCdList.ab")
	public RtnMsg nutrCdList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		List<Map<String, String>> result = nutrInfoMngService.nutrCdList(params);
		rtnMap.put("result", result);

		vo.setRtnData(rtnMap, params);

		return vo;
	}
	//행추가_행삭제_저장
	@ResponseBody
	@RequestMapping("/svcStnd/nutr/nutrInfoMng/saveNutrInfo.ab")
	public RtnMsg saveNutrInfo(@RequestBody(required=false) Map<String, Object> params) throws BizException {
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		nutrInfoMngService.saveNutrInfo(params);

		rtnMap.put("result", params);
		vo.setRtnData(rtnMap,null);

		return vo;
	}
	//영양소_정보_리스트 엑셀다운로드
	@ResponseBody
	@RequestMapping("/svcStnd/nutr/nutrInfoMng/searchNutrInfoList/excel.ab")
	public ModelAndView downloadExcel(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		params.put("paging", "N");

		List<Map<String, String>> result = dao.selectList("svcStnd.nutr.nutrInfoMng.searchNutrInfoList", params);
		return new ModelAndView("excelXlsView", getExcelMap(result));
	}

	private Map<String, Object> getExcelMap(List<Map<String, String>> list)
	{
		String [] arrHeader = {"영양소코드","영양소명","영양소 단위 코드","성장비만지수 구분 코드","정렬순서","사용여부","등록날짜","등록시각","등록사용자ID","수정등록일자","수정시각","수정사용자ID"};
		List<String> headerList = Arrays.asList(arrHeader);

		List<List<String>> dataList = new ArrayList<List<String>>();
		List<String> data;

		for ( Map<String, String> info : list )
		{
			data = new ArrayList<String>();
			data.add(info.get("nutrCd"));
			data.add(info.get("nutrNm"));
			data.add(info.get("nutrUnitCd"));
			data.add(info.get("gfixDivCd"));
			data.add(String.valueOf(info.get("sortOrd")));
			data.add(info.get("useYn"));
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

	//영양소_코드_정보_리스트 조회
	@ResponseBody
	@RequestMapping("/svcStnd/nutr/nutrInfoMng/searchNutrCodeList.ab")
	public RtnMsg searchNutrCodeList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		List<Map<String, String>> result = dao.selectList("svcStnd.nutr.nutrInfoMng.searchNutrCodeList", params);

		rtnMap.put("result", result);
		vo.setRtnData(rtnMap, params);

		return vo;
	}

	//영양소_코드_정보_리스트 조회
	@ResponseBody
	@RequestMapping("/svcStnd/nutr/nutrInfoMng/searchNutrEatStndList.ab")
	public RtnMsg searchNutrEatStndList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		List<Map<String, String>> result = dao.selectList("svcStnd.nutr.nutrInfoMng.searchNutrEatStndList", params);

		rtnMap.put("result", result);
		vo.setRtnData(rtnMap, params);

		return vo;
	}

	//영양소_상태기준_정보_리스트 조회
	@ResponseBody
	@RequestMapping("/svcStnd/nutr/nutrInfoMng/searchNutrStatStndInfoList.ab")
	public RtnMsg searchNutrStatStndInfoList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		List<Map<String, String>> result = dao.selectList("svcStnd.nutr.nutrInfoMng.searchNutrStatStndInfoList", params);

		rtnMap.put("result", result);
		vo.setRtnData(rtnMap, params);

		return vo;
	}

	//영양소 상태기준 저장
	@ResponseBody
	@RequestMapping("/svcStnd/nutr/nutrInfoMng/saveNutrStatStndInfo.ab")
	public RtnMsg saveNutrStatStndInfo(@RequestBody(required=false) Map<String, String> params) throws BizException {
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		nutrInfoMngService.saveNutrStatStndInfo(params);

		rtnMap.put("result", params);
		vo.setRtnData(rtnMap,null);

		return vo;
	}

	//영양소_섭취기준_정보_리스트 조회
	@ResponseBody
	@RequestMapping("/svcStnd/nutr/nutrInfoMng/searchNutrEatStndInfoList.ab")
	public RtnMsg searchNutrEatStndInfoList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		List<Map<String, String>> result = dao.selectList("svcStnd.nutr.nutrInfoMng.searchNutrEatStndInfoList", params);

		params.put("paging", "N");
		vo.setTotalCount(result.size());


		rtnMap.put("result", result);
		vo.setRtnData(rtnMap, params);

		return vo;
	}

	//영양소 섭취기준 저장
	@ResponseBody
	@RequestMapping("/svcStnd/nutr/nutrInfoMng/saveNutrEatStndInfo.ab")
	public RtnMsg saveNutrEatStndInfo(@RequestBody(required=false) Map<String, Object> params) throws BizException {
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		nutrInfoMngService.saveNutrEatStndInfo(params);

		rtnMap.put("result", params);
		vo.setRtnData(rtnMap,null);

		return vo;
	}

}
