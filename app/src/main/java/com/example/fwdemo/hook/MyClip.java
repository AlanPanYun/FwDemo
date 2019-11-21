package com.example.fwdemo.hook;

import android.content.ClipData;
import android.os.IBinder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Alan
 * @date 2019/9/25
 */
public class MyClip implements InvocationHandler {

    private Object mBase;

    public MyClip(IBinder binder, Class stub) {

        try {
            Method asInterface = stub.getDeclaredMethod("asInterface", IBinder.class);
            mBase = asInterface.invoke(null, binder);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("getPrimaryClip".equals(method.getName())) {
            return ClipData.newPlainText(null, "hahhaha");
        }

        if ("hasPrimaryClip".equals(method.getName())) {
            return true;
        }


        return method.invoke(mBase, args);
    }
}
