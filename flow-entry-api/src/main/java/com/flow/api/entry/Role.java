package com.flow.api.entry;

import lombok.Data;

import java.util.List;

/**
 * @Description 角色
 * @Author Aaron
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2020/8/11
 */
@Data
public class Role {
    //主键
    private Integer id;
    //角色名称
    private String roleName;
    //角色编码
    private String roleCode;
    //角色对应页面
    private List<Integer> functionPage;
}
