package com.example.fwdemo.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Alan
 * @date 2018/6/7
 */
public class SalesInvocationHandler implements InvocationHandler {


    //代理类持有一个委托类的对象
    private Object delegate;


    // 绑定委托对象，并返回代理类
    public Object bind(Object delegate) {
        this.delegate = delegate;
        return Proxy.newProxyInstance(
                delegate.getClass().getClassLoader(),
                delegate.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

        System.out.print("enter method " + method.getName());
        long start = System.currentTimeMillis();
        Object invoke = method.invoke(delegate, objects);
        long end = System.currentTimeMillis();
        System.out.print(" exit method " + method.getName());
        System.out.print("执行时间=" + (end - start));
        return invoke;
    }
}
