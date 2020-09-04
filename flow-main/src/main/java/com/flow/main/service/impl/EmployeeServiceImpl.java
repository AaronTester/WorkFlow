package com.flow.main.service.impl;

import com.flow.api.entry.Employee;
import com.flow.main.mapper.EmployeeMapper;
import com.flow.main.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author Aaron
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2020/8/25
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee queryUserById(Integer id) {
        return employeeMapper.queryUserById(id);
    }

    @Override
    public List<Employee> queryUserList() {
        return employeeMapper.queryUserList();
    }

    @Override
    public Employee checkEmp(Employee employee) {
        return employeeMapper.checkEmployee(employee);
    }

    @Override
    public PageInfo queryEmpList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize); // 设定当前页码，以及当前页显示的条数
        //PageHelper.offsetPage(pageNum, pageSize);也可以使用此方式进行设置
        List<Employee> list = employeeMapper.queryUserList();
        PageInfo<Employee> pageInfo = new PageInfo<Employee>(list);
        return pageInfo;
    }
}
