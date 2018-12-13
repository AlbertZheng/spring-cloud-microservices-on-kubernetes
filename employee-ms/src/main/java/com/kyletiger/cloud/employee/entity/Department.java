package com.kyletiger.cloud.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@ToString(includeFieldNames = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Department implements Serializable {
    private static final long serialVersionUID = -2030778357908759637L;

    private Long id;
    private String name;
    private String location;

    /* ..., @Data使得此处可省略一堆的胶水代码! */
}
