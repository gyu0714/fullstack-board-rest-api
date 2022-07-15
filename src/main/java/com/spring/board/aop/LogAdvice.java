package com.spring.board.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogAdvice.class);
	
	@Before("execution(* com.spring.board.apiController.*(..))")
	public void beforeLoggin() {
		LOGGER.info("before loggin : --> ");
	}
	
}
