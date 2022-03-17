package kr.co.seculink.web.controller.devc.band;

import kr.co.seculink.domain.RtnMsg;
import kr.co.seculink.exception.BizException;
import kr.co.seculink.util.GEUtil;
import kr.co.seculink.web.excel.ExcelConstant;
import kr.co.seculink.web.service.devc.band.BandOpenInfoMngService;
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
public class BandOpenInfoMngController
{
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;

	@Autowired
	private BandOpenInfoMngService bandOpenInfoMngService;

	//밴드/개통정보_리스트 조회
	@ResponseBody
	@RequestMapping("/devc/band/bandOpenInfoMng/searchBandOpenInfoList.ab")
	public RtnMsg searchBandOpenInfoList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		List<Map<String, String>> result = bandOpenInfoMngService.searchBandOpenInfoList(params);

		if ( !GEUtil.isEmpty(params.get("paging")) ) {
			params.put("paging", "N");
			vo.setTotalCount(((List)bandOpenInfoMngService.searchBandOpenInfoList(params)).size());
		}
		rtnMap.put("result", result);
		vo.setRtnData(rtnMap, params);

		return vo;
	}


	//밴드/개통 정보 목록_리스트 엑셀다운로드
	@ResponseBody
	@RequestMapping("/devc/band/bandOpenInfoMng/searchBandOpenInfoList/excel.ab")
	public ModelAndView downloadExcel(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		params.put("paging", "N");

		List<Map<String, String>> result = dao.selectList("devc.band.bandOpenInfoMng.selectBandOpenInfoList", params);
		return new ModelAndView("excelXlsView", getExcelMap(result));
	}

	private Map<String, Object> getExcelMap(List<Map<String, String>> list)
	{
		String [] arrHeader = {"기준일자","밴드등록일자","출고년월","모델TYPE", "밴드ID","전화번호","학생번호","학생명","보호자번호1","보호자명","보호자전화번호1","보호자번호2","보호자명","보호자전화번호2","블루투스ID","밴드개통상태코드","밴드개통상태코드명","개통URL전문번호","개통전문번호","URL제공여부","URL제공일시"};
		List<String> headerList = Arrays.asList(arrHeader);

		List<List<String>> dataList = new ArrayList<List<String>>();
		List<String> data;

		for ( Map<String, String> info : list )
		{
			data = new ArrayList<String>();
			data.add(info.get("uptDt"));
			data.add(info.get("regDt"));
			data.add(info.get("bandYtyp"));
			data.add(info.get("bandMdlCd"));
			data.add(info.get("bandId"));
			data.add(info.get("telNo"));
			data.add(String.valueOf(info.get("stdtNo")));		
			data.add(info.get("stdtNm"));
			data.add(String.valueOf(info.get("guarNo")));				
			data.add(info.get("guarNm"));
			data.add(info.get("guarTelNo1"));
			data.add(String.valueOf(info.get("guarNoSub")));				
			data.add(info.get("guarNmSub"));
			data.add(info.get("guarTelNo2"));
			data.add(info.get("blthId"));
			data.add(info.get("bandOpenStatCd"));
			data.add(info.get("bandOpenStatCdNm"));
			data.add(String.valueOf(info.get("apiUrlGramNo")));
			data.add(String.valueOf(info.get("openGramNo")));
			data.add(info.get("apiUrlYn"));
			data.add(info.get("apiUrlDttm"));
			/*
			 * data.add(info.get("regDt")); data.add(info.get("regTm"));
			 * data.add(info.get("regUserId")); data.add(info.get("uptDt"));
			 * data.add(info.get("uptTm")); data.add(info.get("uptUserId"));
			 */
			dataList.add(data);
		}

		Map<String, Object> map = new HashMap<>();
		map.put(ExcelConstant.FILE_NAME, "excel");
		map.put(ExcelConstant.HEAD, headerList);
		map.put(ExcelConstant.BODY, dataList);
		return map;
	}

	//밴드ID 중복 조회
	@ResponseBody
	@RequestMapping("/devc/band/bandOpenInfoMng/searchDupBandId.ab")
	public RtnMsg searchDupBandId(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		Map<String, String> result = bandOpenInfoMngService.searchDupBandId(params);

		rtnMap.put("result", result);
		vo.setRtnData(rtnMap);

		return vo;
	}

	//밴드ID 채번
	@ResponseBody
	@RequestMapping("/devc/band/bandOpenInfoMng/numberingBandId.ab")
	public RtnMsg numberingBandId(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		Map<String, Object> result = bandOpenInfoMngService.numberingBandId(params);

		rtnMap.put("result", result);
		vo.setRtnData(rtnMap);

		return vo;
	}

	//밴드정보 저장
	@ResponseBody
	@RequestMapping("/devc/band/bandOpenInfoMng/saveBandOpenInfoDetl.ab")
	public RtnMsg saveBandOpenInfoDetl(@RequestBody(required = false)Map<String,Object>params)throws BizException {

		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		bandOpenInfoMngService.saveBandOpenInfoDetl(params);


		rtnMap.put("result", params);
		vo.setRtnData(rtnMap, params);

		return vo;
	}



	//밴드_정보_상세보기
	@ResponseBody
	@RequestMapping("/devc/band/bandOpenInfoMng/searchBandOpenInfo.ab")
	public RtnMsg searchBandOpenInfo(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		Map<String, Object> result = bandOpenInfoMngService.searchBandOpenInfo(params);

		rtnMap.put("result", result);
		vo.setRtnData(rtnMap);

		return vo;
	}

}
