package kr.co.seculink.web.controller.svcStnd.nurt;

import kr.co.seculink.domain.RtnMsg;
import kr.co.seculink.exception.BizException;
import kr.co.seculink.util.GEUtil;
import kr.co.seculink.web.excel.ExcelConstant;
import kr.co.seculink.web.service.svcStnd.nutr.DdRcmdEatStndMngService;
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
public class DdRcmdEatStndMngController
{
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;

	@Autowired
	private DdRcmdEatStndMngService ddRcmdEatStndMngService;

	//일일_권장_섭취량_기준_리스트 조회
	@ResponseBody
	@RequestMapping("/svcStnd/nutr/ddRcmdEatStndMng/searchDdRcmdEatStndList.ab")
	public RtnMsg searchDdRcmdEatStndList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		List<Map<String, String>> result = ddRcmdEatStndMngService.searchDdRcmdEatStndList(params);
		if ( !GEUtil.isEmpty(params.get("paging")) ) {
			params.put("paging", "N");
			vo.setTotalCount(((List)ddRcmdEatStndMngService.searchDdRcmdEatStndList(params)).size());
		}
		rtnMap.put("result", result);
		vo.setRtnData(rtnMap, params);

		return vo;
	}



	//행추가_행삭제_저장
	@ResponseBody
	@RequestMapping("/svcStnd/nutr/ddRcmdEatStndMng/saveDdRcmdEatStnd.ab")
	public RtnMsg saveDdRcmdEatStnd(@RequestBody(required = false)Map<String,Object>params)throws BizException {
	
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		System.out.println("파람"+params);
		ddRcmdEatStndMngService.saveDdRcmdEatStnd(params);

	
		rtnMap.put("result", params);
		vo.setRtnData(rtnMap, params);

		return vo;
	}

	//일일_권장섭취량기준_리스트 엑셀다운로드
	@ResponseBody
	@RequestMapping("/svcStnd/nutr/ddRcmdEatStndMng/searchDdRcmdEatStndList/excel.ab")
	public ModelAndView downloadExcel(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		params.put("paging", "N");

		List<Map<String, String>> result = dao.selectList("svcStnd.nutr.ddRcmdEatStndMng.selectDdRcmdEatStndList", params);
		return new ModelAndView("excelXlsView", getExcelMap(result));
	}

	private Map<String, Object> getExcelMap(List<Map<String, String>> list)
	{
		String [] arrHeader = {"성별","나이(년수)","영양소 명","섭취량(From)", "섭취량(To)","영양섭취 상태","사용여부","등록일자","등록시각","등록사용자ID","수정등록일자","수정시각","수정사용자ID"};
		List<String> headerList = Arrays.asList(arrHeader);

		List<List<String>> dataList = new ArrayList<List<String>>();
		List<String> data;

		for ( Map<String, String> info : list )
		{
			data = new ArrayList<String>();
			data.add(info.get("sexCd"));
			data.add(String.valueOf(info.get("ageYcnt")));
			data.add(info.get("nutrNm"));
			data.add(String.valueOf(info.get("eatQtyFr")));
			data.add(String.valueOf(info.get("eatQtyTo")));
			data.add(info.get("nutrStatCd"));
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
}
