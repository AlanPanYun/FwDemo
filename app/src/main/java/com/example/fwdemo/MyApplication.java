package com.example.fwdemo;

import android.app.Application;

import com.example.fwdemo.component.AppComponent;
import com.example.fwdemo.component.DaggerAppComponent;
import com.example.fwdemo.module.ApiModule;
import com.example.fwdemo.module.BaseModule;
import com.example.fwdemo.module.MainModule;
import com.example.fwdemo.utils.AppUtils;

/**
 * Created by qk14 on 2018/3/22.
 */

public class MyApplication extends Application {


    private AppComponent baseComponent;
    private BaseModule baseModule;
    public static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        baseModule = new BaseModule();

        AppUtils.init(this);

       baseComponent =  DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .mainModule(new MainModule(this))
                .build();
    }

    public AppComponent getBaseComponent() {
        return baseComponent;
    }

    public BaseModule getBaseModule() {
        return baseModule;
    }

    public static MyApplication getInstance(){
        return mInstance;
    }

}
