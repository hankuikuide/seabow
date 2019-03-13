package com.crhms.seabow.interceptor;

import com.crhms.seabow.model.AjaxResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * 捕获自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = FriendlyException.class)
    public Map<String,Object> errorHandle(FriendlyException e){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",e.getCode());
        map.put("msg",e.getMsg());
        return map;
    }
}
