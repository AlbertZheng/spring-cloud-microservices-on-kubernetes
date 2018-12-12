package com.kyletiger.cloud.employee.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@ToString(includeFieldNames = true)
@Data
public class Employee implements Serializable {
    private static final long serialVersionUID = -8967259662108705379L;

    private Long id;
    private String name;
    private String mobile;
    private String loginPwd;
    private String pwdSalt;
    private Long departmentId;

    // Computing fields:
    private String departmentName = "";

    /* ..., @Data使得此处可省略一堆的胶水代码! */
}
