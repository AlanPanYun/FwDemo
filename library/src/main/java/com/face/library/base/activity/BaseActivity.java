package com.face.library.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.face.library.base.inter.IBaseView;

/**
 * @author Alan
 * @date 2019/6/17
 */
public class BaseActivity extends AppCompatActivity implements IBaseView {


    private long firstTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private boolean isShortClick(){
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime >=1500){
            firstTime = secondTime;
            return false;
        }
        return true;

    }



    @Override
    public void onClick(View v) {
        if (!isShortClick()){
            onWedigetClick(v);
        }
    }

    @Override
    public void onWedigetClick(View view) {

    }

    @Override
    public void showVisiable(View... views) {
        for (View v :
                views) {
            if (v!=null){
                v.setVisibility(View.VISIBLE);
            }
        }
    }
}
