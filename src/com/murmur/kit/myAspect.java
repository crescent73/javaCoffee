package com.murmur.kit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class myAspect {
	
	@Pointcut("execution(* com.murmur.service.*.*(..))")
	private void myAspect() {
		
	}
	
	@Before("myPointCut()")
	public void myBefore(JoinPoint jp) {
		System.out.println("myBefore");
	}
	
	@AfterReturning(value="myPointCut()")
	public void myAfterReturning(JoinPoint jp) {
		System.out.println("myAfterReturning");
	}
	

}
