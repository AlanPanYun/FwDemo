package com.example.fwdemo.bean;

/**
 * Created by qk14 on 2018/3/22.
 */

public class ColorBean {

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color + "布料";
    }

}
