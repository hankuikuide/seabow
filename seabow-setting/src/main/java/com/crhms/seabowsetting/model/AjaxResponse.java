package com.crhms.seabowsetting.model;

import lombok.Data;

@Data
public class AjaxResponse<T> {

    private Integer status;

    private String msg;

    private T data;

}
