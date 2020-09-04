package com.flow.main.mapper;

import com.flow.api.entry.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

/**
 * @Description
 * @Author Aaron
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2020/8/25
 */
@Mapper
public interface EmployeeMapper {

    public Employee queryUserById(@Param("id") Integer id);

    public List<Employee> queryUserList();

    public Employee checkEmployee(Employee employee);
}
