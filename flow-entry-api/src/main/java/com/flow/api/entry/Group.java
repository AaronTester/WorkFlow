package com.flow.api.entry;

import lombok.Data;

/**
 * @Description 部门
 * @Author Aaron
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2020/8/11
 */
@Data
public class Group {
    //主键
    private Integer id;
    //部门名称
    private String groupName;
    //部门编码
    private String groupCode;
    //上级部门
    private Integer parentId;
}
