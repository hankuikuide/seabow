package com.crhms.seabow.model;

import lombok.Data;


@Data
public class MenuDto {
    private Long id;
    private String url;
    private String path;
    private String component;
    private String name;
    private String iconCls;
    private Long parentId;
    private Integer state;
    private boolean keepAlive;
    private boolean requireAuth;
}
