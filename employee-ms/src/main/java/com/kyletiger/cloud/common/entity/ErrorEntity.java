package com.kyletiger.cloud.common.entity;

import lombok.Data;
import lombok.ToString;


@ToString(includeFieldNames = true)
@Data
public class ErrorEntity<T> {
    public static final Integer SUCCESS = 200;
    public static final Integer CLIENT_ERROR = 400;
    public static final Integer SERVER_ERROR = 500;

    private Integer code;
    private String message;
    private String url;
    private T data;

    /* ..., @Data使得此处可省略一堆的胶水代码! */
}
