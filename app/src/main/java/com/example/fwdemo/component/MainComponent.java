package com.example.fwdemo.component;

import com.example.fwdemo.MainActivity;
import com.example.fwdemo.module.MainModule;

import dagger.Component;

/**
 * Created by qk14 on 2018/3/22.
 */

@Component(dependencies = AppComponent.class)
public interface MainComponent {

    MainActivity inject(MainActivity mainActivity);
}
