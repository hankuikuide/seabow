package com.crhms.seabow.model;

import lombok.Data;

@Data
public class AjaxResponse<T> {

    private Integer status;

    private String msg;

    private int total;

    private T data;

}
