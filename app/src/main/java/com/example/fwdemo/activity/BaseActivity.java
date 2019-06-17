package com.example.fwdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.fwdemo.MyApplication;
import com.example.fwdemo.component.AppComponent;


/**
 * Created by Administrator on 2018/4/4.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private int layoutId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        setupActivityComponent(MyApplication.getInstance().getBaseComponent());

        configView();
        initData();

    }

    public abstract void initData();

    public abstract void configView();

    public abstract int getLayoutId();

    public abstract void setupActivityComponent(AppComponent upActivityComponent);


    protected void gone(View... views){
        if (views !=null && views.length >0){
            for (View view :
                 views) {
                if (view !=null){
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void addOnClikcLisenter(View...views){
        if (views !=null && views.length >0){
            for (View view :
                    views) {
                if (view !=null){
                    view.setOnClickListener(this);
                }
            }
        }
    }

    protected void  isiable(View... views){
        if (views !=null && views.length >0){
            for (View view :
                 views) {
                if (view !=null){
                    view.setVisibility(View.VISIBLE);
                }
            }
        }
    }


    @Override
    public void onClick(View v) {

    }
}
