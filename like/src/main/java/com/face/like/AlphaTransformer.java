package com.face.like;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @author Alan
 * @date 2019/5/6
 */
public class AlphaTransformer implements ViewPager.PageTransformer {


    private float alpha = 0.5f;


    /**
     * position取值特点：
     * 假设页面从0～1，则：
     * 第一个页面position变化为[0,-1]
     * 第二个页面position变化为[1,0]
     *
     * @param view
     * @param position
     */
    @Override
    public void transformPage(@NonNull View view, float position) {

//        Log.i("viewpager","view-w.getId()+",---position --- "+position);
        float scale= 0.0f;
        if (position < -1 || position > 1) {
            view.setAlpha(alpha);
        } else {
            // 左边页面
            if (position < 0) {
                scale = alpha + (1 + position) * (1 - alpha);

            } else {
                scale = alpha + (1 - position) * (1 - alpha);
            }
            view.setAlpha(scale);
            view.setScaleY(scale);
        }
    }


}
