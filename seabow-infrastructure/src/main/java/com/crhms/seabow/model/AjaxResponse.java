package com.crhms.seabow.model;

import lombok.Data;

@Data
public class AjaxResponse<T> {

    private Integer status;

    private String msg;

    private T data;

}
