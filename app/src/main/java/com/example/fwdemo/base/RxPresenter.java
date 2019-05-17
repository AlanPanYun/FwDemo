package com.example.fwdemo.base;

import com.example.fwdemo.contract.BaseContract;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Created by Administrator on 2018/4/8.
 */

public class RxPresenter<T extends BaseContract.BaseView>
        implements BaseContract.BasePresenter<T> {

    protected T mView;
    protected CompositeDisposable mCompositeSubscription;

    @Override
    public void attachView(T view) {

       this.mView = view;
    }

    protected void addSubscrebe(Disposable disposable){
        if (mCompositeSubscription == null){
            mCompositeSubscription = new CompositeDisposable();
            mCompositeSubscription.add(disposable);
        }
    }

    private void unSubscribe(){
        if (mCompositeSubscription !=null){
            mCompositeSubscription.clear();
        }
    }
    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
