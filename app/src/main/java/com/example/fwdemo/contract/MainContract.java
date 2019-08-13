package com.example.fwdemo.contract;

import com.example.fwdemo.bean.GetVersionResponse;
import com.example.fwdemo.bean.request.GetVersionRequest;

/**
 * Created by Administrator on 2018/4/9.
 */

public interface MainContract{

    interface View extends BaseContract.BaseView{

        void showBookDetail( GetVersionResponse data);
    }


    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getBookDetail(GetVersionRequest request);
    }
}
