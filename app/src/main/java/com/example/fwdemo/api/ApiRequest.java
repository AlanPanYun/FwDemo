package com.example.fwdemo.api;

import com.example.fwdemo.base.Constat;
import com.example.fwdemo.bean.GetVersionResponse;
import com.example.fwdemo.bean.request.GetVersionRequest;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by qk14 on 2018/3/28.
 */

public class ApiRequest {


    private final ApiService apiService;
    private static ApiRequest instance;

    public ApiRequest(OkHttpClient okHttpClient) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constat.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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

    public Observable<GetVersionResponse> getVersion(GetVersionRequest request){
        return apiService.getVersion(request);
    }
}
