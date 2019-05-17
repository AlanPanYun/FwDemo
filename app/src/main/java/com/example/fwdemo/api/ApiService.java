package com.example.fwdemo.api;

import com.example.fwdemo.bean.GetVersionResponse;
import com.example.fwdemo.bean.request.GetVersionRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by qk14 on 2018/3/28.
 */

public interface ApiService {

    //http://139.219.193.104:20353/mobile/app/upgrade/version.json?{platform=android, version=5.18.0}
    @POST("/mobile/app/upgrade/version.json")
    Observable<GetVersionResponse> getVersion(@Body GetVersionRequest request);
}
