package com.example.springbootdemo.spbttest.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Create by Bakatparin
 * on 2018/9/29
 */
@Aspect
@Component
public class LogAspect {
    private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.example.springbootdemo.spbttest.controller..*.*(..))")
    public void log(){
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        logger.info("url:{}",request.getRequestURL());
        //请求方式
        logger.info("请求方式:{}",request.getMethod());
        //类方法
        logger.info("class_method:{}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        //参数
        logger.info("args:{}", Arrays.toString(joinPoint.getArgs()));
    }

    @After("log()")
    public void doAfter(){
        logger.info("执行完毕");
    }

    @AfterReturning(pointcut = "log()",returning = "res")
    public void doAfterReturning(Object res){
        logger.info("返回结果:{}",res!=null?res.toString():"无返回值！");
    }

}
