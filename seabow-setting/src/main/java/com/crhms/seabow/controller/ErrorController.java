package com.crhms.seabow.controller;

import com.crhms.seabow.interceptor.FriendlyException;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "handle filter throws exception", description = "处理filter抛出的异常")
public class ErrorController extends BasicErrorController {

    public ErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @Override
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",body.get("status"));
        map.put("msg",body.get("message"));
        return new ResponseEntity<Map<String, Object>>(map, status);
    }
}
