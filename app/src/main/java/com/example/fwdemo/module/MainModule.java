package com.example.fwdemo.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qk14 on 2018/3/22.
 */
@Module
public class MainModule {

    private Context context;

    public MainModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context getContext() {
        return context;
    }

}
