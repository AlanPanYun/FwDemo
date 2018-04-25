package com.example.fwdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.fwdemo.activity.BaseActivity;
import com.example.fwdemo.activity.QRCoreActivity;
import com.example.fwdemo.activity.SecondActivity;
import com.example.fwdemo.bean.BookDetail;
import com.example.fwdemo.bean.Clothes;
import com.example.fwdemo.bean.ColorBean;
import com.example.fwdemo.bean.Shoe;
import com.example.fwdemo.component.AppComponent;
import com.example.fwdemo.component.DaggerMainComponent;
import com.example.fwdemo.contract.MainContract;
import com.example.fwdemo.inter.RedCloth;
import com.example.fwdemo.presenter.MainPresenter;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainContract.View{


    private TextView tv;

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyApplication application = (MyApplication) getApplication();


    }

    @Override
    public void initData() {
        mainPresenter.attachView(this);
    }

    @Override
    public void configView() {

        tv = (TextView) findViewById(R.id.tv_test);
        TextView tvStart = (TextView) findViewById(R.id.tv_start);
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.getBookDetail("1532");
                Log.i("book","start");
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityComponent(AppComponent upActivityComponent) {
        DaggerMainComponent.builder().appComponent(upActivityComponent).build().inject(this);
    }

    //在布局文件中声明的点击方法
    public void onClick(View v) {
        Intent intent = new Intent(this, QRCoreActivity.class);
        startActivity(intent);

        mainPresenter.getBookDetail("1532");
    }


    @Override
    public void showBookDetail(BookDetail data) {
        tv.setText(data.author);
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mainPresenter !=null){
            mainPresenter.detachView();
        }
    }
}
