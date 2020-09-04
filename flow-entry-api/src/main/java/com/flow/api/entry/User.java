package com.flow.api.entry;

import lombok.Data;

import java.util.List;

/**
 * @Description 用户表
 * @Author Aaron
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2020/8/11
 */
@Data
public class User {
    //主键
    private Integer id;
    //用户姓名
    private String userName;
    //用户密码
    private String password;
    //性别
    private String gender;
    //手机号码
    private String telphone;
    //邮箱
    private String email;
    //角色Id
    private List<Integer> roleIds;
    //部门Id
    private Integer groupId;
    //部门
    private Department department;
}
