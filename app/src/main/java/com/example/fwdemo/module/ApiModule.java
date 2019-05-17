package com.example.fwdemo.module;

import com.example.fwdemo.BuildConfig;
import com.example.fwdemo.api.ApiRequest;
import com.example.fwdemo.api.support.HeaderInterceptor;
import com.example.fwdemo.api.support.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by qk14 on 2018/3/28.
 */

@Module
public class ApiModule {

    @Provides
    public OkHttpClient providerOkHttpClient() {


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
            loggingInterceptor.setLevel(LoggingInterceptor.Level.BODY);
        }
        builder.connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)// 失败重发
                .addInterceptor(new HeaderInterceptor());

        return builder.build();
    }

    @Provides
    protected ApiRequest providerApiService(OkHttpClient okHttpClient) {
        return ApiRequest.getInstance(okHttpClient);
    }

}
