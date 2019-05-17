package com.face.library;

import android.app.Activity;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author Alan
 * @date 2019/4/18
 */
public class ARouterUtils {


    public static void injectActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        ARouter.getInstance().inject(activity);
    }

    
    public static void navation(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        ARouter.getInstance().build(url).navigation();
    }
}
