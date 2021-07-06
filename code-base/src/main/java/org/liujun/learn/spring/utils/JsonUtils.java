/*
 * Copyright (C), 2008-2021, Paraview All Rights Reserved.
 */
package org.liujun.learn.spring.utils;


import com.google.gson.Gson;

/**
 * json转换的工具类
 *
 * @author liujun
 * @since 2021/7/5
 */
public class JsonUtils {

    private static Gson instance = new Gson();


    /**
     * 执行将将对象输出为json
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> String toJson(T data) {
        return instance.toJson(data);
    }


}

