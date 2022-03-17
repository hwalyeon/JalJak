package kr.co.seculink.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import kr.co.seculink.domain.GEConstant;
import kr.co.seculink.util.GEUtil;
import kr.co.seculink.web.model.cmon.SessionVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class MybatisAOP {

	@Pointcut("execution(public * org.mybatis.spring.SqlSessionTemplate.*(String,..))")
    public void pointCut() {}
	
	@Before(value = "pointCut()")
    public void checkSessionValid(JoinPoint joinPoint) {
		
		Object[] args = joinPoint.getArgs();
		
        // mybatis의 경우 0번째는 sqlId
		String sqlId = args[0] + "";
		
		log.debug("### SQL ID : {}", sqlId);
		
		SqlSessionTemplate sqlSessionTemplate = (SqlSessionTemplate) joinPoint.getTarget();
		
		MappedStatement stmt = sqlSessionTemplate.getConfiguration().getMappedStatement(sqlId);
		
		String rSql = "";
		String dSql = "";
		
		// parameter가 있는 경우
		if (args.length > 1) {
			
			String regUserId = "";
			String uptUserId = "";
			
			boolean isLogined = false;
			
			if (null != SecurityContextHolder.getContext().getAuthentication()) {
				
				Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();

				if (details instanceof SessionVO) {
					
					isLogined = true;
					
					SessionVO vo = (SessionVO) details;
					
					regUserId = vo.getUserId();
					uptUserId = vo.getUserId();
				
				}
			}
			
			if (false == isLogined) {
				
				regUserId = GEConstant.USER_ANONYMOUS;
				uptUserId = GEConstant.USER_ANONYMOUS;
				
				HttpServletRequest request = GEUtil.getRequest();
				
				if (null != request) {
					Object isBatch = request.getAttribute(GEConstant.IS_BATCH_KEY);
					
					if (null != isBatch && true == (boolean) isBatch) {
						regUserId = GEConstant.USER_BATCH;
						uptUserId = GEConstant.USER_BATCH;
					}
				}
			}
			
			// map인 경우에만 지원
			if (args[1] instanceof Map) {
				
				
				
				Map param = (Map) args[1];
				
				param.put(GEConstant.COL_REG_USER_ID, regUserId);
				param.put(GEConstant.COL_UPT_USER_ID, uptUserId);
				
				BoundSql bSql = stmt.getBoundSql(param);
				List<ParameterMapping> params = bSql.getParameterMappings();
				
				log.debug("params {} ", params);
				
				rSql = bSql.getSql();
				
				String[] rSqls = rSql.split("\\?");
				
				for (int idx = 0, _max = rSqls.length ; idx < _max ; idx++ ) {
					
					if (idx != 0) {
					
						Object oParam = param.get(params.get(idx-1).getProperty());
						
						String strParam = oParam + "";
						
						if (null != oParam) {
							strParam = "'" + oParam + "'";
						}
						
						dSql += strParam;
					} else {
						
					}
					
					dSql += rSqls[idx];
					
				}
				
				if (rSqls.length == 1 && params.size() == 1) {
					Object oParam = param.get(params.get(0).getProperty());
					
					String strParam = oParam + "";
					
					if (null != oParam) {
						strParam = "'" + oParam + "'";
					}
					
					dSql += strParam;
				}
			} else {
				dSql = stmt.getBoundSql(args[1]).getSql();
				
				Object oParam = args[1];
				
				if (oParam instanceof String) {
					
				} else if (null == oParam) { 
				
				} else {
				
					Class cls = oParam.getClass();
					
					if (null != cls) {
					
						try {
							Method setRegUserId = cls.getMethod("setRegUserId", String.class);
							Method setUptUserId = cls.getMethod("setUptUserId", String.class);
							
							if (null != setRegUserId) {
								setRegUserId.invoke(oParam, regUserId);
							}
							
							if (null != setUptUserId) {
								setUptUserId.invoke(oParam, uptUserId);
							}
							
						} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							//e.printStackTrace();
						}
					
					}
				}
			}
			
			log.debug("### SQL Parameter : {}", args[1]);
		} else {
			dSql = stmt.getBoundSql(null).getSql();
			
			
		}
        
		log.debug("### SQL : {}", dSql);
    }
}
