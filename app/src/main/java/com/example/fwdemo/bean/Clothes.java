package com.example.fwdemo.bean;

/**
 * Created by qk14 on 2018/3/22.
 */

public class Clothes {

    private ColorBean cloth;

    public Clothes(ColorBean cloth) {
        this.cloth = cloth;
    }

    public ColorBean getCloth() {
        return cloth;
    }

    @Override
    public String toString() {
        return cloth.getColor() + "衣服";
    }

}
