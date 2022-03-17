package kr.co.seculink.web.controller.svcStnd.grow;

import kr.co.seculink.domain.RtnMsg;
import kr.co.seculink.exception.BizException;
import kr.co.seculink.util.GEUtil;
import kr.co.seculink.web.excel.ExcelConstant;
import kr.co.seculink.web.service.svcStnd.grow.ActStndMngService;
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
public class ActStndMngController
{
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;

	@Autowired
	private ActStndMngService actStndMngService;

	//활동_기준_리스트_조회
	@ResponseBody
	@RequestMapping("/svcStnd/grow/actStndMng/actStndList.ab")
	public RtnMsg actStndList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		List<Map<String, String>> result = actStndMngService.actStndList(params);

		if ( !GEUtil.isEmpty(params.get("paging")) ) {
			params.put("paging", "N");
			vo.setTotalCount(((List)actStndMngService.actStndList(params)).size());
		}
		rtnMap.put("result", result);
		vo.setRtnData(rtnMap, params);

		return vo;
	}
	// 활동_분류_코드 리스트_조회
	@ResponseBody
	@RequestMapping("/svcStnd/grow/actStndMng/actClssCdList.ab")
	public RtnMsg actClssCdList(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		List<Map<String, String>> result =actStndMngService.actClssCdList(params);
		rtnMap.put("result", result);

		vo.setRtnData(rtnMap, params);

		return vo;
	}

	//행추가_행삭제_저장
	@ResponseBody
	@RequestMapping("/svcStnd/grow/actStndMng/saveActStnd.ab")
	public RtnMsg saveActStnd(@RequestBody(required = false)Map<String,Object>params)throws BizException {
		RtnMsg vo = new RtnMsg();
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		actStndMngService.saveActStnd(params);

		rtnMap.put("result", params);
		vo.setRtnData(rtnMap, params);

		return vo;
	}

	//활동_기준_리스트 엑셀다운로드
	@ResponseBody
	@RequestMapping("/svcStnd/grow/actStndMng/searchActStndList/excel.ab")
	public ModelAndView downloadExcel(@RequestBody(required=false) Map<String, String> params) throws BizException
	{
		params.put("paging", "N");

		List<Map<String, String>> result = dao.selectList("svcStnd.grow.actStndMng.searchActStndList", params);
		return new ModelAndView("excelXlsView", getExcelMap(result));
	}

	private Map<String, Object> getExcelMap(List<Map<String, String>> list)
	{
		String [] arrHeader = {"활동분류","활동코드","활동명","활동설명","MET 값","MET 분당 환산계수","정렬순서","등록일자","등록시각","등록사용자ID","수정등록일자","수정시각","수정사용자ID"};
		List<String> headerList = Arrays.asList(arrHeader);

		List<List<String>> dataList = new ArrayList<List<String>>();
		List<String> data;

		for ( Map<String, String> info : list )
		{
			data = new ArrayList<String>();    
			data.add(info.get("actClssCd"));
			data.add(info.get("actCd"));
			data.add(info.get("actNm"));
			data.add(info.get("actDesc"));
			data.add(String.valueOf(info.get("metVal")));
			data.add(String.valueOf(info.get("metMinCfct")));
			data.add(String.valueOf(info.get("sortOrd")));
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
