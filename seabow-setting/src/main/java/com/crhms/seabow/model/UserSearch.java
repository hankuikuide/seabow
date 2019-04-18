package com.crhms.seabow.model;

import lombok.Data;

@Data
public class UserSearch {

    private Integer pageNum;

    private Integer pageSize;

    private String name;
}
