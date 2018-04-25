package com.example.fwdemo.contract;

/**
 * Created by Administrator on 2018/4/8.
 */

public interface BaseContract {


    interface BasePresenter<T> {

        void attachView(T view);

        void detachView();
    }

    interface BaseView {

        void showError();

        void complete();

    }
}
