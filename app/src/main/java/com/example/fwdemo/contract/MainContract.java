package com.example.fwdemo.contract;

import com.example.fwdemo.bean.BookDetail;

/**
 * Created by Administrator on 2018/4/9.
 */

public interface MainContract{

    interface View extends BaseContract.BaseView{

        void showBookDetail(BookDetail data);
    }


    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getBookDetail(String bookId);
    }
}
