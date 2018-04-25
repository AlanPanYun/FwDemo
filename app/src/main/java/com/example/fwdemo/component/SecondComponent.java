package com.example.fwdemo.component;

import com.example.fwdemo.activity.SecondActivity;
import com.example.fwdemo.module.SecondModule;

import dagger.Component;

/**
 * Created by qk14 on 2018/3/22.
 */

@Component(modules = SecondModule.class, dependencies = AppComponent.class)
public interface SecondComponent {

    void inject(SecondActivity secondActivity);

}
