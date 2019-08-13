package com.example.fwdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.fwdemo.MyApplication;
import com.example.fwdemo.component.AppComponent;
import com.example.fwdemo.contract.BaseContract;


/**
 * Created by alanpan on 2018/4/4.
 */

public abstract class BaseActivity<T extends BaseContract.BasePresenter,V extends BaseContract.BaseNoImpView> extends AppCompatActivity implements View.OnClickListener {

    private T presenter;
    private V view;
    private int layoutId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        setupActivityComponent(MyApplication.getInstance().getBaseComponent());

        presenter.attachView(view);

        configView();
        initData();

    }

    public abstract void initData();

    public abstract void configView();

    public abstract int getLayoutId();

    public abstract void setupActivityComponent(AppComponent upActivityComponent);

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

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

    public static void getDat(){

    }

    @Override
    public void onClick(View v) {

    }
}
