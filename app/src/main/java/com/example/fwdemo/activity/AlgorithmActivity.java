package com.example.fwdemo.activity;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.fwdemo.R;
import com.example.fwdemo.component.AppComponent;
import com.example.fwdemo.component.DaggerMainComponent;
import com.example.fwdemo.contract.AlgorithmContract;
import com.example.fwdemo.presenter.AlgorithmPresenter;
import com.face.library.ARouterConstant;

import javax.inject.Inject;

/**
 * @author Alan
 * @date 2019/6/14
 */
@Route(path = ARouterConstant.APP_ALGORITHMACTIVITY)
public class AlgorithmActivity extends BaseActivity implements AlgorithmContract.View{



    @Inject
    AlgorithmPresenter mPresenter;

    @Override
    public void initData() {
        mPresenter.attachView(this);
         //"PAHNAPLSIIGYIR" PAHNALIGYPISRIx
        mPresenter.reverse(30009);
    }

    @Override
    public void configView() {
        TextView tvTest = findViewById(R.id.tv_test);
        addOnClikcLisenter(tvTest);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_algorithm;
    }

    @Override
    public void setupActivityComponent(AppComponent upActivityComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(upActivityComponent)
                .build()
                .inject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_test:
            mPresenter.twoSum(new int[]{3,2,5,9,0,1},4);
            break;
        }

        getDat();
    }


}
