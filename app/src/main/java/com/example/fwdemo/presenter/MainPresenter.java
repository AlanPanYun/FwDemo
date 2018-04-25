package com.example.fwdemo.presenter;

import android.util.Log;

import com.example.fwdemo.api.ApiRequest;
import com.example.fwdemo.base.RxPresenter;
import com.example.fwdemo.bean.BookDetail;
import com.example.fwdemo.contract.MainContract;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/9.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter<MainContract.View> {


    private ApiRequest apiRequest;

    @Inject
    public MainPresenter(ApiRequest apiRequest) {
        this.apiRequest = apiRequest;
    }

    @Override
    public void attachView(MainContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getBookDetail(final String bookId) {
        Subscription subscribe = apiRequest.getBookDetail(bookId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookDetail>() {
                    @Override
                    public void onCompleted() {
                        Log.i("boookdetail", "book = onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("boookdetail", "book = error " + e.getMessage());
                    }

                    @Override
                    public void onNext(BookDetail bookDetail) {
                        if (bookDetail != null && mView != null) {
                            mView.showBookDetail(bookDetail);
                            Log.i("boookdetail", "book = " + bookDetail.author);
                        }

                        Log.i("boookdetail", "book = null");
                    }
                });
        addSubscrebe(subscribe);

    }
}
