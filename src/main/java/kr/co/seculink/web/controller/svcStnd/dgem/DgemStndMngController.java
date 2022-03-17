package kr.co.seculink.web.controller.svcStnd.dgem;

import kr.co.seculink.domain.RtnMsg;
import kr.co.seculink.exception.BizException;
import kr.co.seculink.util.GEUtil;
import kr.co.seculink.web.excel.ExcelConstant;
import kr.co.seculink.web.service.svcStnd.dgem.DgemStndMngService;
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
public class DgemStndMngController {
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate dao;

	@Autowired
	private DgemStndMngService dgemStndMngService;

	// 위험_감정_기준 리스트 조회
	@ResponseBody
	@RequestMapping("/svcStnd/dgem/dgemStndMng/searchDgemList.ab")
	public RtnMsg searchDgemList(@RequestBody(required = false) Map<String, String> params) throws BizException {
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		List<Map<String, String>> result = dgemStndMngService.searchDgemList(params);
		rtnMap.put("result", result);

		if (!GEUtil.isEmpty(params.get("paging"))) {
			params.put("paging", "N");
			vo.setTotalCount(((List) dgemStndMngService.searchDgemList(params)).size());
		}

		vo.setRtnData(rtnMap, params);

		return vo;
	}

	// 위험_감정_기준 리스트 엑셀다운로드
	@ResponseBody
	@RequestMapping("/svcStnd/dgem/dgemStndMng/searchDgemList/excel.ab")
	public ModelAndView downloadExcel(@RequestBody(required = false) Map<String, String> params) throws BizException {
	
		params.put("paging", "N");
		List<Map<String, String>> result = dao.selectList("svcStnd.dgem.dgemStndMng.searchTcDgemStatBase", params);

		return new ModelAndView("excelXlsView", getExcelMap(result));
	}

	private Map<String, Object> getExcelMap(List<Map<String, String>> list) {
		String[] arrHeader = { "활동 구분 명", "심박 상태 명","장소 분류 명","체온 상태 명","위험감정 지수","위험감정 상태 명","위험감정 요약내용","등록일자","등록시각","등록사용자ID","수정등록일자","수정시각","수정사용자ID" };
		List<String> headerList = Arrays.asList(arrHeader);

		List<List<String>> dataList = new ArrayList<List<String>>();
		List<String> data;

		for (Map<String, String> info : list) {
			data = new ArrayList<String>();
			data.add(info.get("actDivCdNm"));
			data.add(info.get("hbitStatCdNm"));
			data.add(info.get("plcClssCdNm"));       
			data.add(info.get("tempStatCdNm"));       
			data.add(String.valueOf(info.get("dgemIdx")));       
			data.add(info.get("dgemStatCdNm"));       
			data.add(info.get("dgemSmryCntn"));       
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
	@RequestMapping("/svcStnd/dgem/dgemStndMng/searchDgemStndInfo.ab")

	public RtnMsg searchDgemStndInfo(@RequestBody(required = false) Map<String, String> params) throws BizException
	{

		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		List<Map<String, String>> result = dao.selectList("svcStnd.dgem.dgemStndMng.searchTcDgemStatBase", params);
		
		rtnMap.put("result", result);
		vo.setRtnData(rtnMap, params);

		return vo;
	}

	@ResponseBody
	@RequestMapping("/svcStnd/dgem/dgemStndMng/saveInfo.ab")
	public RtnMsg saveInfo(@RequestBody(required=false) Map<String, Object> params) throws BizException {
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		if("C".equals(params.get("crud"))) dao.insert("svcStnd.dgem.dgemStndMng.insertTiDgemStnd",params);
		else if("U".equals(params.get("crud"))) dao.insert("svcStnd.dgem.dgemStndMng.updateTiDgemStnd",params);
		else if("D".equals(params.get("crud"))) dao.insert("svcStnd.dgem.dgemStndMng.deleteTiDgemStnd",params);

		rtnMap.put("result", params);
		vo.setRtnData(rtnMap,null);

		return vo;
	}
	@ResponseBody
	@RequestMapping("/svcStnd/dgem/dgemStndMng/searchDupCdCk.ab")
	public RtnMsg searchDupCdCk(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		Map<String, String> result = dgemStndMngService.searchDupCdCk(params);

		rtnMap.put("result", result);
		vo.setRtnData(rtnMap);

		return vo;
	}
}
