package com.crhms.seabowsetting.config;

import com.crhms.seabowsetting.model.AjaxResponse;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public class HandlerMethodReturnValueHandlerProxy implements HandlerMethodReturnValueHandler {
    private HandlerMethodReturnValueHandler proxyObject;

    public HandlerMethodReturnValueHandlerProxy(HandlerMethodReturnValueHandler proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return proxyObject.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        AjaxResponse response = new AjaxResponse();

        response.setStatus(STATUS_CODE_SUCCEEDED);
        response.setMsg("");
        response.setData(returnValue);
        proxyObject.handleReturnValue(response, returnType, mavContainer, webRequest);
    }

    private static final int STATUS_CODE_SUCCEEDED = 200;
}