package com.face.like;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.face.library.ARouterConstant;

/**
 * @author Alan
 * @date 2019/5/12
 */
@Route(path = ARouterConstant.AVATAR_ACTIVITY)
public class AvatarViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activty_view_avatar);

        CircleAnimator  cirlceView =  findViewById(R.id.cirlceView);
        ObjectAnimator animator = ObjectAnimator.ofFloat(cirlceView, "progress", 0, 70);
        animator.start();
    }
}
