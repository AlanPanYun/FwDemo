package com.face.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Alan
 * @date 2019/6/5
 */
public abstract class BaseMapActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        initMap();
    }

    //获取数据
    abstract void initData();

    private void initMap(){
        //初始化map
    }

}
