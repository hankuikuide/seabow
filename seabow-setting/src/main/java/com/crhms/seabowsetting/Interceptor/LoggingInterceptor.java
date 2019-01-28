package com.crhms.seabowsetting.Interceptor;

import com.crhms.seabowsetting.model.AjaxResponse;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 针对所有com.crhms.seabowsetting下所有的方法添加拦截方法。
 * 通过情况下只在controller层添加拦截方法即可。
 */
@Aspect
@Component
@Slf4j
public class LoggingInterceptor {

    @Pointcut("execution(public * com.crhms.seabowsetting.controller.*.*(..))")
    public void exceptionLog(){}

    @Around("exceptionLog()()")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable{
        Stopwatch stopwatch = Stopwatch.createStarted();
        AjaxResponse ajaxResponse;
        Object obj;

        try {
            log.info("执行Controller开始：" + pjp.getSignature());

            obj =  pjp.proceed(pjp.getArgs());

            log.info("执行Controller结束: ");

            Long consumeTime = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
            log.info("耗时：" + consumeTime + "(毫秒).");

            return  obj;

        } catch (Throwable throwable) {
            log.error("异常：" + throwable.getStackTrace());

            ajaxResponse = new AjaxResponse();
            ajaxResponse.setMsg("出错了！！！");
            ajaxResponse.setStatus(-1);
            return ajaxResponse;
        }
    }

    @Before("exceptionLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("请求地址 : " + request.getRequestURL().toString());
        log.info("HTTP方法 : " + request.getMethod());
        log.info("IP地址 : " + request.getRemoteAddr());
        log.info("请求目标类 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("参数 : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret",pointcut = "exceptionLog()")
    public void doAfterReturn(Object ret) throws Throwable {
        log.info("请求处理结束：0000000000000000000000:" +ret);
    }


}
