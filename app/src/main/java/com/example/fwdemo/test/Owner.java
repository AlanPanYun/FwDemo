package com.example.fwdemo.test;

/**
 * @author Alan
 * @date 2018/6/7
 */
public class Owner implements Sales{
    @Override
    public void sell() {

        System.out.print("我是房东我正在出售我的房产");
    }
}
