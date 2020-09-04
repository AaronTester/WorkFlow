package com.flow.api.entry;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author Aaron
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2020/8/25
 */
@Data
public class Department {
    private Integer id;
    private String deptName;
    private List<Employee> employees;
}
