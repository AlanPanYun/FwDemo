package com.face.video;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.face.library.utils.AppUtils;

/**
 * @author Alan
 * @date 2019/4/18
 */
public class VideoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouterUtils();
        AppUtils.init(this);
    }

    private void ARouterUtils() {
//        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
