package kr.co.seculink.aop;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.seculink.domain.GEConstant.RTN_CD;
import kr.co.seculink.domain.RtnMsg;
import kr.co.seculink.exception.BaseException;
import kr.co.seculink.exception.BizException;
import kr.co.seculink.exception.SysException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@ControllerAdvice
public class GlobalControllerAdvice {

	private ObjectMapper om = new ObjectMapper();
	
	@Autowired
	private MessageSource message;
	
	@ExceptionHandler
	public ResponseEntity<String> handleBaseException(Exception ex) {
		
		RtnMsg<Map<String, String>> vo = new RtnMsg<>();
		
		HttpStatus status = HttpStatus.OK;
		
		ResponseEntity<String> entity = null;
		
		Exception oEx = null;
		
		if (ex instanceof BizException) {
			
			BaseException be = (BizException) ex;
			
			String errCd = be.getErrCd();
			String[] params = be.getCdParams();
			
			String errMsg = message.getMessage(errCd, params, LocaleContextHolder.getLocale());
			
			vo.setRtnCd(errCd);
			vo.setRtnMsg("[" + errCd + "] " + errMsg);
			
			oEx = (Exception) be.getThrowable();
		} else if (ex instanceof MethodArgumentNotValidException) {
			
			MethodArgumentNotValidException me = (MethodArgumentNotValidException) ex;
			
			String field = me.getBindingResult().getAllErrors().get(0).getCodes()[0];
			String message = me.getBindingResult().getAllErrors().get(0).getDefaultMessage();
			
			String errMsg = message + " : " + field;
			
			vo.setRtnCd(RTN_CD.파라메터오류);
			vo.setRtnMsg(errMsg);
			
			status = HttpStatus.BAD_REQUEST;
			oEx = ex;
			
		} else if (ex instanceof org.springframework.web.bind.MissingServletRequestParameterException) {
			
			MissingServletRequestParameterException me = (MissingServletRequestParameterException) ex;
			
			String field = me.getParameterName();
			String errMsg = message.getMessage("VALID.001", new String[]{field}, LocaleContextHolder.getLocale());
			
			vo.setRtnCd(RTN_CD.파라메터오류);
			vo.setRtnMsg("[VALID.001] " + errMsg);
			
			status = HttpStatus.BAD_REQUEST;
			oEx = ex;
		} else if (ex instanceof BindException) {
			
			BindException be = (BindException) ex;
			
			String errMsg = message.getMessage("VALID.001", new String[]{be.getFieldError().getField()}, LocaleContextHolder.getLocale());
			
			vo.setRtnCd(RTN_CD.파라메터오류);
			vo.setRtnMsg("[VALID.001] " + errMsg);
			
			status = HttpStatus.BAD_REQUEST;
			oEx = ex;
			
		} else if (ex instanceof HttpMessageNotReadableException) {
			
			String errMsg = message.getMessage("ESYS002", null, LocaleContextHolder.getLocale());
			vo.setRtnMsg(errMsg);
			
			vo.setRtnCd(RTN_CD.JSON오류);
			status = HttpStatus.BAD_REQUEST;
			oEx = ex;
		} else if (ex instanceof org.springframework.web.HttpRequestMethodNotSupportedException) {
			
			String errMsg = message.getMessage("ESYS003", null, LocaleContextHolder.getLocale());
			vo.setRtnMsg(errMsg);
			
			vo.setRtnCd(RTN_CD.METHOD오류);
			status = HttpStatus.METHOD_NOT_ALLOWED;
			oEx = ex;
		} else {
			
			vo.setRtnCd(RTN_CD.시스템오류);
			vo.setRtnMsg(message.getMessage("SYSTEM.default", null, LocaleContextHolder.getLocale()));
			
			if (ex instanceof SysException) {
				
				SysException be = (SysException) ex;
				
				oEx = (Exception) be.getThrowable();
				
			} else {
			
				oEx = ex;
			
			}
			
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		if (null != oEx) {
			
			//StringWriter sw = new StringWriter();
			//oEx.printStackTrace(new PrintWriter(sw));
			//String exceptionAsString = sw.toString();
			
			Map<String, String> data = new HashMap<String, String>();
			
			//SQL 오류의 경우 자세한 내용 표시 안함
			if (oEx instanceof SQLException) {
				data.put("errorMsg", "SQL Exception");
			} else {
				data.put("errorMsg", oEx.getLocalizedMessage());
			}
			//data.put("errorStack", exceptionAsString);
			
			vo.setRtnData(data);
		}
		
		// 로그에 에러 표시
		log.error("", oEx);
		
		String restJson = "";
		
		try {
			restJson = om.writeValueAsString(vo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		log.debug("ERROR Response : restvo {}", restJson);
		
		entity = new ResponseEntity<String>(restJson, status);
		
		return entity;
		
	}

	
}
