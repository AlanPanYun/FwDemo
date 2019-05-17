package com.example.fwdemo.test;

/**
 * @author Alan
 * @date 2018/6/7
 */
public class Customer {

    public static void main(String[] args) {
        Sales delegate = new Owner();
        Sales proxy = (Sales) new SalesInvocationHandler().bind(delegate);
        proxy.sell();
    }
}
