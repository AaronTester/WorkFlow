package com.flow.api.entry;

import lombok.Data;

/**
 * @Description 功能页面对应类
 * @Author Aaron
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2020/8/11
 */
@Data
public class FunctionPage {
    //主键
    private Integer id;
    //功能名称
    private String funcName;
    //功能是否启用
    private boolean isEnable;
    //功能具体对应页面
    private String funcPage;
    //功能描述
    private String funcDesc;
}
