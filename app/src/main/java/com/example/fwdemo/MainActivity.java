package com.example.fwdemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.fwdemo.rv.TestBean;
import com.example.fwdemo.rv.TestListAdapter;
import com.example.fwdemo.utils.GlideUtil;
import com.face.library.banner.CoverModeTransformer;
import com.face.library.banner.view.BannerView;
import com.face.library.utils.BarUtils;
import com.face.library.utils.LogUtils;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity<MainPresenter,MainContract.View>  implements MainContract.View {


    private TextView tv;
    private RecyclerView recyclerView;

    @Inject
    MainPresenter mainPresenter;

    private List<TitleImageBannerEntry> data;
    private List<TestBean> listadapter;

    @Override
    public void initData() {
        mainPresenter.attachView(this);

        GlideUtil glideUtil = new GlideUtil();
        glideUtil.addMap();
        glideUtil.remove();
    }

    private void getViewSpec() {
        ViewTreeObserver v = tv.getViewTreeObserver();
        v.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

            }
        });

    }


    @SuppressLint("CheckResult")
    @Override
    public void configView() {


        listadapter = new ArrayList<>();
        BarUtils.setNavBarVisibility(MainActivity.this,false);
        final RxPermissions rxPermissions = new RxPermissions(this);

        rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Logger.i("log",aBoolean);
                    }
                });
        initBanner();
        tv = (TextView) findViewById(R.id.tv_test);
        TextView tvStart = (TextView) findViewById(R.id.tv_start);
        recyclerView =  findViewById(R.id.recyclerview);
        tvStart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View view) {
//                mainPresenter.getBookDetail(new GetVersionRequest("5.9.0", "android"));
                LogUtils.i("book", "start");

                List<String> list = new ArrayList<>();
//                "cats", "dog", "sand", "and", "cat"]
//                "aaaaaaa"
//                        ["aaaa","aaa"]
                list.add("aaaa");
                list.add("aaa");
//                mainPresenter.wordBreak("aaaaaaa",list);
//                ARouterUtils.navation(ARouterConstant.APP_HOOK);

                listadapter.get(2).setTitle("0000");
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,1));

        for (int i = 0; i < 10; i++) {
            TestBean testBean = new TestBean();
            testBean.setCollect(true);
            testBean.setTitle("hhh "+i);
            listadapter.add(testBean);
        }
        TestListAdapter adapter = new TestListAdapter(this);
        adapter.addAll(listadapter);

        recyclerView.setAdapter(adapter);

    }

    private void showCamera() {
        // 跳转到系统照相机
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(cameraIntent);

    }

    public static String getModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
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

        mainPresenter.getBookDetail(new GetVersionRequest("5.9.0", "android"));
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
        if (mainPresenter != null) {
            mainPresenter.detachView();
        }
    }

    public List<TitleImageBannerEntry> getData() {
        data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TitleImageBannerEntry entry = new TitleImageBannerEntry("11", "", R.drawable.img_banner01, "");
            data.add(entry);
        }
        return data;
    }

    public static void testDecare(String[] args) {
        List<String> list1 = new ArrayList<String>();
        list1.add("a");
        list1.add("b");
        List<String> list2 = new ArrayList<String>();
        list2.add("0");
        list2.add("1");
        list2.add("2");
        List<List<String>> dimValue = new ArrayList<List<String>>();
        dimValue.add(list1);
        dimValue.add(list2);

        // 递归实现笛卡尔积
        Solution s = new Solution();
        List<List<String>> res = s.descartes(dimValue);
        System.out.println("递归实现笛卡尔乘积: 共 " + res.size() + " 个结果");
        for (List<String> list : res) {
            for (String string : list) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }
}
