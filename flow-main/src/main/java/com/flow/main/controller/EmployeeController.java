package com.flow.main.controller;

import com.flow.main.entity.CommonResutMessage;
import com.flow.main.entity.Employee;
import com.flow.main.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author Aaron
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2020/8/25
 */
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("emp/{id}")
    public CommonResutMessage queryEmpById(@PathVariable("id") Integer id) {
        Employee employee = employeeService.queryUserById(id);
        return new CommonResutMessage(employee,"查询成功");
    }

    @GetMapping("emp/list")
    public CommonResutMessage queryAllEmployees() {
        List<Employee> employees = employeeService.queryUserList();
        return new CommonResutMessage(employees,"查询成功");
    }

    @PostMapping("emp/check")
    public CommonResutMessage checkLoginEmp(@RequestBody Employee employee, Model model) {
        Employee newEmp = employeeService.checkEmp(employee);
        if(null != newEmp) {
            model.addAttribute("USERINFO", newEmp);
            return new CommonResutMessage();
        }else {
            return CommonResutMessage.fail();
        }
    }

    @GetMapping("emp/page/list")
    public PageInfo<Employee> queryEmployeePage(Integer pageNum,Integer pageSize) {
        PageInfo pageInfo = employeeService.queryEmpList(pageNum, pageSize);
        return pageInfo;
    }

}
