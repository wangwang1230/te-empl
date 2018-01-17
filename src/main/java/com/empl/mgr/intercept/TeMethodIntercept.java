package com.empl.mgr.intercept;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.empl.mgr.annotation.ExcludeLog;
import com.empl.mgr.utils.CompareUtil;

@Component(value = "teMethodIntercept")
public class TeMethodIntercept implements MethodInterceptor {

	private final Logger log = LoggerFactory.getLogger(TeMethodIntercept.class);

	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		if (CompareUtil.isNotEmpty(methodInvocation.getMethod().getAnnotation(ExcludeLog.class)))
			return methodInvocation.proceed(); // 排除注解包
		Object proceed = methodInvocation.proceed();
		try {
			final int length = methodInvocation.getArguments().length;
			final String methodName = new StringBuffer().append(methodInvocation.getThis().toString()).append("|")
					.append(methodInvocation.getMethod().getName()).toString();
			log.info("methodName={}", new Object[] { methodName });
			for (int i = 0; i < length; i++) {
				if (methodInvocation.getArguments()[i] == null)
					continue;
				log.info("\targ[{}] -> {}", i, methodInvocation.getArguments()[i].toString());
			}
			log.info("\treturn : {} ", new Object[] { methodName, proceed }, "\n");
		} catch (Exception e) {
			log.error("ERROR : ", e);
			throw e;
		}
		return proceed;
	}
}