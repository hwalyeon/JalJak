package kr.co.seculink.web.controller.svcStnd.fat;

import kr.co.seculink.domain.RtnMsg;
import kr.co.seculink.exception.BizException;
import kr.co.seculink.util.GEUtil;
import kr.co.seculink.web.excel.ExcelConstant;
import kr.co.seculink.web.service.svcStnd.fat.BpalCalcStndMngService;
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
public class BpalCalcStndMngController
{
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;

	@Autowired
	private BpalCalcStndMngService bpalCalcStndMngService;

	//휴식대사량_계산_기준_리스트_조회
	@ResponseBody
	@RequestMapping("/svcStnd/fat/bpalCalcStndMng/searchBpalCalcStndList.ab")
	public RtnMsg searchBpalCalcStndList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		List<Map<String, String>> result = bpalCalcStndMngService.searchBpalCalcStndList(params);
		if ( !GEUtil.isEmpty(params.get("paging")) ) {
			params.put("paging", "N");
			vo.setTotalCount(((List)bpalCalcStndMngService.searchBpalCalcStndList(params)).size());
		}
		rtnMap.put("result", result);
		vo.setRtnData(rtnMap, params);

		return vo;
	}
	//행추가_행삭제_저장
	@ResponseBody
	@RequestMapping("/svcStnd/fat/bpalStndMng/saveBpalStnd.ab")
	public RtnMsg saveBpalStnd(@RequestBody(required = false)Map<String,Object>params)throws BizException {
	
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		System.out.println("파람"+params);
		bpalCalcStndMngService.saveBpalStnd(params);

	
		rtnMap.put("result", params);
		vo.setRtnData(rtnMap, params);

		return vo;
	}

	//휴식대사량_계산_기준_리스트 엑셀다운로드
	@ResponseBody
	@RequestMapping("/svcStnd/fat/bpalCalcStndMng/searchBpalCalcStndList/excel.ab")
	public ModelAndView downloadExcel(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		params.put("paging", "N");

		List<Map<String, String>> result = dao.selectList("svcStnd.fat.bpalCalcStndMng.selectBpalCalcStndList", params);
		return new ModelAndView("excelXlsView", getExcelMap(result));
	}

	private Map<String, Object> getExcelMap(List<Map<String, String>> list)
	{
		String [] arrHeader = {"성별","나이(FROM)","나이(TO)","계산식","등록일자","등록시각","등록사용자ID","수정등록일자","수정시각","수정사용자ID"};
		List<String> headerList = Arrays.asList(arrHeader);

		List<List<String>> dataList = new ArrayList<List<String>>();
		List<String> data;

		for ( Map<String, String> info : list )
		{
			data = new ArrayList<String>();
			data.add(info.get("sexCd"));
			data.add(String.valueOf(info.get("ageYcntFr")));
			data.add(String.valueOf(info.get("ageYcntTo")));
			data.add(info.get("calcFrml"));
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
