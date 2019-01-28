package com.crhms.seabowsetting.Interceptor;

import com.crhms.medicareopinion.model.AjaxResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public AjaxResponse handler(Exception e){
        AjaxResponse r = new AjaxResponse();
        if (e instanceof RuntimeException){
            RuntimeException selectNoFindException = (RuntimeException) e;
            r.setStatus(-1);
            r.setMsg(selectNoFindException.getMessage());
        } else {
            r.setStatus(-1);
            r.setMsg("系统错误");
        }
        log.error(e.getMessage());
        return r;
    }
}
