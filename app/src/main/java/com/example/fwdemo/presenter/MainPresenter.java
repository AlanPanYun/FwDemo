package com.example.fwdemo.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.fwdemo.api.ApiRequest;
import com.example.fwdemo.base.RxPresenter;
import com.example.fwdemo.bean.BaseDataBean;
import com.example.fwdemo.bean.GetVersionResponse;
import com.example.fwdemo.bean.request.GetVersionRequest;
import com.example.fwdemo.contract.MainContract;
import com.face.library.utils.GsonUtil;

import java.util.Random;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/9.
 */

public class MainPresenter extends RxPresenter<MainContract.View>
        implements MainContract.Presenter<MainContract.View> {


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
    public void getBookDetail(GetVersionRequest request) {
        Disposable disposable = apiRequest
                .getVersion(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetVersionResponse>() {
                    @Override
                    public void accept(GetVersionResponse getVersionResponse) throws Exception {
                        Log.i("response", GsonUtil.getJsonStringFromObject(getVersionResponse));
                    }
                });
        addSubscrebe(disposable);


        Single.just(1)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return String.valueOf(integer);
                    }
                })
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @SuppressLint("CheckResult")
    public void load() {
        Disposable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

            }
        })
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                });


        Observable.create(new ObservableOnSubscribe<BaseDataBean>() {
            @Override
            public void subscribe(ObservableEmitter<BaseDataBean> emitter) throws Exception {
                BaseDataBean baseDataBean = new BaseDataBean();
                baseDataBean.setError("ffff");
                emitter.onNext(baseDataBean);
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseDataBean>() {
                    @Override
                    public void accept(BaseDataBean s) throws Exception {
                        Log.i("book", s.getError());
                    }
                });

    }


    public int[] proArry() {
        Log.i("suanfa", "proArry==" + System.currentTimeMillis());
        Random random = new Random();
        int[] items = new int[10000];
        for (int i = 0; i < 9999; i++) {
            int ra = (int) Math.floor(random.nextDouble() * 1000);
            items[i] = ra;
        }
        Log.i("suanfa", "proArry==" + System.currentTimeMillis());
        return items;
    }


    public void sortByMao(int[] item) {
        Log.i("suanfa", "sortByMao==" + System.currentTimeMillis());
        int size = item.length;
        for (int i = 0; i < size; i++) {
            boolean flag = false;
            for (int j = 0; j < size - 1 - i; j++) {
                if (item[j] > item[j + 1]) {
                    int tem = item[j];
                    item[j] = item[j + 1];
                    item[j + 1] = tem;
                    //是否有数据交换
                    flag = true;
                }
            }

            if (!flag) {
                break;
            }
        }
        Log.i("suanfa", "sortByMao==" + System.currentTimeMillis());
    }

    public void sortByCha(int[] a) {
        Log.i("suanfa", "sortByCha==" + System.currentTimeMillis());
        int size = a.length;
        for (int i = 1; i < size; i++) {
            int value = a[i];
            int j = i - 1;

            for (; j >= 0; j--) {
                if (a[j] > value) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = value;
        }
        Log.i("suanfa", "sortByCha==" + System.currentTimeMillis());
    }


    private void sortMao(int[] a) {
        int size = a.length;
        boolean ch = false;

        for (int i = 0; i < size; i++) {
            ch = false;
            for (int j = 0; j < size - i - 1; j++) {
                if (a[j + 1] < a[j]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    ch = true;
                }
            }
            if (!ch) {
                return;
            }
        }
    }


    private void sortCha(int[] a) {

        int size = a.length;
        for (int i = 1; i < size; i++) {
            int j = i - 1;
            int temp = a[i];

            for (; j >= 0; j--) {

                if (a[j] > temp) {
                    a[j + 1] = a[j];
                } else {
                    return;
                }
            }
            a[j + 1] = temp;

        }
    }

    public int bsearch(int[] a, int n, int value) {
        int low = 0;
        int hight = n - 1;
        //第一个值
        while (low <= hight) {
            int mid = low + (hight - low) >> 1;
            if (a[mid] > value) {
                hight = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == 0 || a[mid - 1] != value) {
                    return mid;
                } else {
                    hight = mid - 1;
                }
            }

        }
        return -1;
    }

    public int bsearchLast(int[] a, int n, int value) {

        // 最后一个指定值
        int l = 0;
        int h = n - 1;
        while (l <= h) {
            int m = l +(h-l)/2;
            if (a[m] > value){
                h = m-1;
            }else if (a[m] <value){
                l = m+1;
            }else {
                if (m == n-1 ||a[m+1] !=value){
                    return m;
                }else {
                    l = m+1;
                }
            }
        }

        return -1;

    }

}
