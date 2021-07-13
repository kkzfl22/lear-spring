/*
 * Copyright (C), 2008-2021, Paraview All Rights Reserved.
 */
package org.liujun.learn.spring.factory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.liujun.learn.spring.utils.TransferManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理工厂
 *
 * @author liujun
 * @since 2021/7/13
 */
public class ProxyFactory {


    /**
     * 事务管理
     */
    private TransferManager transferManager;


    /**
     * 代理工厂,使用java原生的jdk的动态代理实现
     *
     * @param srcObject
     * @return
     */
    public Object newJdkProxy(Object srcObject) {
        return Proxy.newProxyInstance(srcObject.getClass().getClassLoader(), srcObject.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                transferManager.beginTransfer();

                Object result = null;
                try {
                    //执行业务逻辑代码
                    result = method.invoke(srcObject, args);

                    //成功执行，则提交事务
                    transferManager.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    transferManager.rollback();
                }

                return result;
            }
        });
    }


    /**
     * 使用cglib来实现动态代理
     *
     * @param srcObject
     * @return
     */
    public Object newCglibProxy(Object srcObject) {
        return Enhancer.create(srcObject.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                transferManager.beginTransfer();

                Object result = null;
                try {
                    //执行业务逻辑代码
                    result = method.invoke(srcObject, objects);

                    //成功执行，则提交事务
                    transferManager.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    transferManager.rollback();
                }

                return result;
            }
        });
    }

    public void setTransferManager(TransferManager transferManager) {
        this.transferManager = transferManager;
    }
}
