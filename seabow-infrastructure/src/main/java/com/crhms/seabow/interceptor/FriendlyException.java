package com.crhms.seabow.interceptor;

import lombok.Data;

/**
 * 自定义异常
 */
@Data
public class FriendlyException extends RuntimeException {

    private String code;
    private String msg;
}
