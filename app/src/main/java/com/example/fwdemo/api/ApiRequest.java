package com.example.fwdemo.api;

import com.example.fwdemo.base.Constat;
import com.example.fwdemo.bean.BookDetail;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by qk14 on 2018/3/28.
 */

public class ApiRequest {


    private final ApiService apiService;
    private static ApiRequest instance;

    public ApiRequest(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constat.API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        apiService = retrofit.create(ApiService.class);

    }

    public static ApiRequest getInstance(OkHttpClient okHttpClient){

        if (instance == null){
            instance = new ApiRequest(okHttpClient);
        }

        return instance;
    }

    public Observable<BookDetail> getBookDetail(String bookId){
        return apiService.getBookDetail(bookId);
    }
}
