package com.example.fwdemo.contract;

/**
 * @author Alan
 * @date 2019/6/14
 */
public interface AlgorithmContract   {
    interface  View extends  BaseContract.BaseNoImpView{

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{

    }
}
