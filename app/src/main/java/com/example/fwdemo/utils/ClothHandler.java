package com.example.fwdemo.utils;

import com.example.fwdemo.bean.Clothes;
import com.example.fwdemo.bean.ColorBean;

/**
 * Created by qk14 on 2018/3/22.
 */

public class ClothHandler {

    public Clothes handle(ColorBean cloth) {
        return new Clothes(cloth);
    }
}
