package com.example.fwdemo.base;

import com.example.fwdemo.contract.BaseContract;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2018/4/8.
 */

public class RxPresenter<T extends BaseContract.BaseView>
        implements BaseContract.BasePresenter<T> {

    protected T mView;
    protected CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(T view) {

       this.mView = view;
    }

    protected void addSubscrebe(Subscription subscription){
        if (mCompositeSubscription == null){
            mCompositeSubscription = new CompositeSubscription();
            mCompositeSubscription.add(subscription);
        }
    }

    private void unSubscribe(){
        if (mCompositeSubscription !=null){
            mCompositeSubscription.unsubscribe();
        }
    }
    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
