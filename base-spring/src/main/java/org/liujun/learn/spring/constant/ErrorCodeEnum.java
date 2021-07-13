/*
 * Copyright (C), 2008-2021, Paraview All Rights Reserved.
 */
package org.liujun.learn.spring.constant;

/**
 * 错误码信息
 *
 * @author liujun
 * @since 2021/7/5
 */
public enum ErrorCodeEnum {

    /**
     * 成功标识
     */
    SUCCESS("1", "操作成功"),


    /**
     * 失败标识
     */
    FAIL("-1", "操作失败"),

    ;

    /**
     * 错误码
     */
    private String code;


    /**
     * 提示信息
     */
    private String msg;

    ErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorCodeEnum{");
        sb.append("code='").append(code).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
