package com.crhms.seabow.interceptor;

import com.crhms.seabow.Entity.Log;
import com.crhms.seabow.model.User;
import com.crhms.seabow.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;

@Component
@Aspect
@Order(5)
public class SystemLogAspect {

    @Autowired
    private LogService logService;

    /***
     * 定义service切入点拦截规则，拦截SystemServiceLog注解的方法
     */
    @Pointcut("@annotation(com.crhms.seabow.interceptor.SystemControllerLog)")
    public void controllerAspect(){}

    /**
     * @Description  前置通知  用于拦截Controller层记录用户的操作
     * @date 2018年9月3日 10:38
     */

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();

        Log log = new Log();
        log.setName(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.setOperator("admin");
        log.setContent("请求地址 : " + request.getRequestURL().toString());
        log.setCreateDate(new Date());
        logService.save(log);

    }

}
