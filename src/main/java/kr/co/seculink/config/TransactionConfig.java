package kr.co.seculink.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
@Aspect
@Configuration
public class TransactionConfig {

	private static final int TX_METHOD_TIMEOUT = 3;
	private static final String AOP_POINTCUT_EXPRESSION = "execution(* kr.co.seculink..*ServiceImpl.*(..))";

	@Autowired
	@Qualifier("writerPlatformTransactionManager")
	private PlatformTransactionManager writerTransactionManager;

	@Bean
	@Qualifier("writerPlatformTransactionManager")
	public PlatformTransactionManager writerTransactionManager(@Qualifier("writerDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

//	@Autowired
//	@Qualifier("readerPlatformTransactionManager")
//	private PlatformTransactionManager readerTransactionManager;
//
//	@Bean
//	@Qualifier("readerPlatformTransactionManager")
//	public PlatformTransactionManager readerTransactionManager(@Qualifier("readerDataSource") DataSource dataSource) {
//		return new DataSourceTransactionManager(dataSource);
//	}

	@Bean
	public TransactionInterceptor txAdvice() {

		TransactionInterceptor txAdvice = new TransactionInterceptor();

		Properties txAttributes = new Properties();

		List<RollbackRuleAttribute> rollbackRules = new ArrayList<RollbackRuleAttribute>();

		rollbackRules.add(new RollbackRuleAttribute(Exception.class));

		/** If need to add additionall exceptio, add here **/
		DefaultTransactionAttribute readOnlyAttribute = new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED);
		readOnlyAttribute.setReadOnly(true);
		readOnlyAttribute.setTimeout(TX_METHOD_TIMEOUT);

		RuleBasedTransactionAttribute writeAttribute = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, rollbackRules);
		writeAttribute.setTimeout(TX_METHOD_TIMEOUT);

		String readOnlyTransactionAttributesDefinition = readOnlyAttribute.toString();
		String writeTransactionAttributesDefinition = writeAttribute.toString();

		log.info("Read Only Attributes :: {}", readOnlyTransactionAttributesDefinition);
		log.info("Write Attributes :: {}", writeTransactionAttributesDefinition);

		// read-only
		//txAttributes.setProperty("srch*", readOnlyTransactionAttributesDefinition);

		// write rollback-rule
		txAttributes.setProperty("reg*", writeTransactionAttributesDefinition);
		txAttributes.setProperty("mod*", writeTransactionAttributesDefinition);
		txAttributes.setProperty("upt*", writeTransactionAttributesDefinition);
		txAttributes.setProperty("insert*", writeTransactionAttributesDefinition);
		txAttributes.setProperty("update*", writeTransactionAttributesDefinition);
		txAttributes.setProperty("save*", writeTransactionAttributesDefinition);
		txAttributes.setProperty("del*", writeTransactionAttributesDefinition);
		txAttributes.setProperty("proc*", writeTransactionAttributesDefinition);

		txAdvice.setTransactionAttributes(txAttributes);
		txAdvice.setTransactionManager(writerTransactionManager);

		return txAdvice;

	}

	@Bean
	public Advisor txAdviceAdvisor() {

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(AOP_POINTCUT_EXPRESSION);

		return new DefaultPointcutAdvisor(pointcut, txAdvice());

	}

}
