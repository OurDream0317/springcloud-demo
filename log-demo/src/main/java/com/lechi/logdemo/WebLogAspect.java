package com.lechi.logdemo;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
    ThreadLocal startTime = new ThreadLocal();

    @Pointcut("execution(public * com.lechi.logdemo..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        startTime.set(System.currentTimeMillis());
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
        logger.info("该操作共响应了：" + (System.currentTimeMillis() - Long.parseLong(String.valueOf(startTime.get()))) / 1000 + "秒");
        logger.info(Integer.valueOf("10").intValue() + "===" + Integer.parseInt("10"));

    }

    // 匹配org.crazyit.app.service.impl包下所有类的、
    // 所有方法的执行作为切入点
    @AfterThrowing(throwing = "ex"
            , pointcut = "execution(public * com.lechi.logdemo..*.*(..))")
    // 声明ex时指定的类型会限制目标方法必须抛出指定类型的异常
    // 此处将ex的类型声明为Throwable，意味着对目标方法抛出的异常不加限制
    public void doRecoveryActions(Throwable ex) {
        System.out.println("目标方法中抛出的异常:" + ex);
        System.out.println("模拟Advice对异常的修复...");
    }
}
