package com.flow.api.entry;

import lombok.Data;

/**
 * @Description
 * @Author Aaron
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2020/8/25
 */
@Data
public class Employee {

    private Integer id;
    private String empName;
    private String email;
    private String address;
    private String gender;
    private Department department;
    //private Integer deptId;
}
