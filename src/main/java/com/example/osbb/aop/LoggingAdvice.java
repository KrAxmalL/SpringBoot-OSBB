package com.example.osbb.aop;

import java.util.Date;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log4j2
public class LoggingAdvice {

  @Pointcut("execution(public * *.*(..))")
  public void publicMethods() {}

  @Pointcut("within(com.example.osbb.web.controller.*)")
  public void controllers() {}

  @Pointcut("within(com.example.osbb.service.*)")
  public void services() {}

  @Around("publicMethods() && controllers()")
  public Object logRequestAndResponse(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    final String methodName = proceedingJoinPoint.getSignature().getName();
    final String className = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();

    final Object[] arguments = proceedingJoinPoint.getArgs();
    final Object returnValue = proceedingJoinPoint.proceed(arguments);
    log.info(
        "Controller: [{}], method: [{}], input: {}, result: [{}]",
        className,
        methodName,
        arguments,
        returnValue);

    return returnValue;
  }

  @Around("publicMethods() && services()")
  public Object logMethodCallTimeAndExecutionTime(ProceedingJoinPoint proceedingJoinPoint)
      throws Throwable {
    final String methodName = proceedingJoinPoint.getSignature().getName();
    final String className = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();

    final Date callTime = new Date();
    log.info("Service: [{}], method: [{}], called at: {}", className, methodName, callTime);

    final long startExecution = System.nanoTime();
    final Object[] arguments = proceedingJoinPoint.getArgs();
    final Object returnValue = proceedingJoinPoint.proceed(arguments);
    final long finishExecution = System.nanoTime();
    final double executionTimeMills = (finishExecution - startExecution) / 1000.0;
    log.info(
        "Service: [{}], method: [{}], execution time: {} ms",
        className,
        methodName,
        executionTimeMills);

    return returnValue;
  }
}
