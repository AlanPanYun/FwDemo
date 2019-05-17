package com.example.fwdemo;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.example.fwdemo.activity.BaseActivity;
import com.example.fwdemo.activity.PicActivity;
import com.example.fwdemo.bean.GetVersionResponse;
import com.example.fwdemo.bean.TitleImageBannerEntry;
import com.example.fwdemo.bean.request.GetVersionRequest;
import com.example.fwdemo.component.AppComponent;
import com.example.fwdemo.component.DaggerMainComponent;
import com.example.fwdemo.contract.MainContract;
import com.example.fwdemo.presenter.MainPresenter;
import com.face.library.banner.CoverModeTransformer;
import com.face.library.banner.view.BannerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainContract.View{


    private TextView tv;


    @Inject
    MainPresenter mainPresenter;
    private List<TitleImageBannerEntry> data;

    @Override
    public void initData() {
        mainPresenter.attachView(this);
    }

    private void getViewSpec(){
        ViewTreeObserver v = tv.getViewTreeObserver();
        v.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

            }
        });

    }

    @Override
    public void configView() {

        initBanner();
        tv = (TextView) findViewById(R.id.tv_test);
        TextView tvStart = (TextView) findViewById(R.id.tv_start);
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.getBookDetail(new GetVersionRequest("5.9.0","android"));
                Log.i("book","start");

//                ARouterUtils.navation(ARouterConstant.like_test);
                mainPresenter.load();

//                ARouterUtils.navation(ARouterConstant.like_test);

//                EditPopu editPopu = new EditPopu(MainActivity.this);
//                editPopu.showAtLocation(tv, Gravity.BOTTOM,0,0);

                int[] ints = mainPresenter.proArry();
                int[] ints2 = mainPresenter.proArry();


                mainPresenter.sortByCha(ints);
                mainPresenter.sortByMao(ints2);
            }
        });


     }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityComponent(AppComponent upActivityComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(upActivityComponent)
                .build()
                .inject(this);
    }

    //在布局文件中声明的点击方法
    public void onClick(View v) {
        Intent intent = new Intent(this, PicActivity.class);
//        startActivity(intent);

        mainPresenter.getBookDetail(new GetVersionRequest("5.9.0","android"));
    }


    @Override
    public void showBookDetail(GetVersionResponse data) {
        tv.setText(data.getVersion());
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    private void initBanner() {
        //找到BannerView控件。
        BannerView bannerView = findViewById(R.id.vp_view_pager);
        //设置自定义翻页动画改变器，也可以不设置。如果没有设置，则使用ViewPager默认的翻页动画。
        bannerView.setPageTransformer(true, new CoverModeTransformer(bannerView));
        //getData()方法是从网络上获取数据。这里只是伪代码。
        List<TitleImageBannerEntry> bannerEntries = getData();
        //设置数据源并开始轮播。如果不希望启动轮播则调用两个参数的方法：bannerView.setEntries(bannerEntries, false); 你也可以通过bannerView.start();的方式启动轮播。
        bannerView.setEntries(bannerEntries);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mainPresenter !=null){
            mainPresenter.detachView();
        }
    }

    public List<TitleImageBannerEntry> getData() {
        data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TitleImageBannerEntry entry = new TitleImageBannerEntry("11","",R.drawable.img_banner01,"");
            data.add(entry);
        }
        return data;
    }
}
