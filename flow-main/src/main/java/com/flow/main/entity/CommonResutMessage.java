package com.flow.main.entity;

/**
 * @Description
 * @Author Aaron
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2020/8/12
 */

import lombok.Data;

/**
 * 通用结果集合
 *
 * @param
 */
@Data
public class CommonResutMessage<T> {

    private int code;
    private String msg;
    private T data;

    /**
     * 若没有数据返回，默认状态码为 200，提示信息为“操作成功！”
     */
    public CommonResutMessage() {
        this.code = 200;
        this.msg = "操作成功！";
    }

    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     *
     * @param code
     * @param msg
     */
    public CommonResutMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 有数据返回，状态码为 200，自定义提示信息
     *
     * @param data
     * @param msg
     */
    public CommonResutMessage(T data, String msg) {
        this.data = data;
        this.code = 200;
        this.msg = msg;
    }

    /**
     * 有数据返回，状态码自定义，提示信息自定义
     *
     * @param data
     * @param msg
     */
    public CommonResutMessage(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    /**
     * 通用成功方法，状态码为 200，默认提示信息为“操作成功！”
     * @param
     */
    public CommonResutMessage(T data) {
        this.data = data;
        this.code = 200;
        this.msg = "操作成功！";
    }

    /**
     * 通用失败方法，状态码为 200，默认提示信息为“操作成功！”
     * @param
     */
    public static CommonResutMessage fail() {
        return new CommonResutMessage(null, 999, "操作失败！");
    }
}
