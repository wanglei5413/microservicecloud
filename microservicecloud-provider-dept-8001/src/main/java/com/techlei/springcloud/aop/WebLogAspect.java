package com.techlei.springcloud.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 统一输出调用者的请求和响应信息日志
 */
@Component
@Aspect
public class WebLogAspect {

    private static Logger logger= LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.techlei.springcloud.controller.*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        logger.info("URL:"+request.getRequestURL());
        logger.info("Method:"+request.getMethod());
        logger.info("QureyString:"+request.getQueryString());
        logger.info("RemoteHost:"+request.getRemoteHost());

        logger.info("Class_Method:"+joinPoint.getSignature().getDeclaringTypeName()+":"+joinPoint.getSignature().getName());
        logger.info("Args:"+ Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret){
        logger.info("Response:"+ret);
    }
}
