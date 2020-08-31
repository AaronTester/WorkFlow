package com.flow.main.service;

import com.flow.main.entity.Employee;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description
 * @Author Aaron
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2020/8/25
 */
public interface EmployeeService {

    public Employee queryUserById(Integer id);

    public List<Employee> queryUserList();

    public Employee checkEmp(Employee employee);

    public PageInfo queryEmpList(Integer pageNum,Integer pageSize);
}
