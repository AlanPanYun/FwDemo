package com.example.fwdemo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.fwdemo.component.AppComponent;
import com.example.fwdemo.component.DaggerAppComponent;
import com.example.fwdemo.module.ApiModule;
import com.example.fwdemo.module.BaseModule;
import com.example.fwdemo.module.MainModule;
import com.face.library.utils.AppUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

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
        Logger.addLogAdapter(new AndroidLogAdapter());
        AppUtils.init(this);
        ARouterUtils();

        baseComponent = DaggerAppComponent
                .builder()
                .apiModule(new ApiModule())
                .mainModule(new MainModule(this))
                .build();
    }

    private void ARouterUtils() {
//        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    public AppComponent getBaseComponent() {
        return baseComponent;
    }

    public BaseModule getBaseModule() {
        return baseModule;
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

}
