package com.example.fwdemo.module;

import com.example.fwdemo.bean.ColorBean;
import com.example.fwdemo.inter.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qk14 on 2018/3/22.
 */
@Module
public class SecondModule {

    @Provides
    public ColorBean getBlueCloth() {
        ColorBean cloth = new ColorBean();
        cloth.setColor("蓝色");
        return cloth;
    }


}
