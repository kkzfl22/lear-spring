/*
 * Copyright (C), 2008-2021, Paraview All Rights Reserved.
 */
package org.liujun.learn.spring.pojo;

import org.liujun.learn.spring.constant.ErrorCodeEnum;

/**
 * 通用的返回的结果
 *
 * @author liujun
 * @since 2021/7/5
 */
public class DataResult<T> {


    /**
     * 错误信息
     */
    private String code;


    /**
     * 错误信息
     */
    private String msg;


    /**
     * 结果信息
     */
    private T data;


    /**
     * 时间戳
     */
    private String timestamp;


    public static DataResult success() {
        DataResult<Void> result = new DataResult<>();
        result.setCode(ErrorCodeEnum.SUCCESS.getCode());
        result.setMsg(ErrorCodeEnum.SUCCESS.getMsg());
        result.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return result;
    }


    public static DataResult fail() {
        DataResult<Void> result = new DataResult<>();
        result.setCode(ErrorCodeEnum.FAIL.getCode());
        result.setMsg(ErrorCodeEnum.FAIL.getMsg());
        result.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return result;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DataResult{");
        sb.append("code='").append(code).append('\'');
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", data=").append(data);
        sb.append(", timestamp='").append(timestamp).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
