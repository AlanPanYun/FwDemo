package com.example.fwdemo.module;

import com.example.fwdemo.utils.ClothHandler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qk14 on 2018/3/22.
 */
@Module
public class BaseModule {


    @Singleton //单例
    @Provides
    public ClothHandler getClothHandler(){
        return new ClothHandler();
    }
}
