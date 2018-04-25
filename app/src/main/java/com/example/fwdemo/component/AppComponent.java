package com.example.fwdemo.component;

import android.content.Context;

import com.example.fwdemo.api.ApiRequest;
import com.example.fwdemo.module.ApiModule;
import com.example.fwdemo.module.BaseModule;
import com.example.fwdemo.module.MainModule;


import dagger.Component;

/**
 * Created by qk14 on 2018/3/28.
 */

@Component (modules = {ApiModule.class, MainModule.class})
public interface AppComponent {

    Context getContext();

    ApiRequest getApiRequest();

}
