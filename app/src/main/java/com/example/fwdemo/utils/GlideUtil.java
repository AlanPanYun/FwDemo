package com.example.fwdemo.utils;

import android.util.Log;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Alan
 * @date 2019/7/18
 */
public class GlideUtil {

    private Map<Object, Object> cache;

    public GlideUtil() {
        cache = new LinkedHashMap<>(100, 0.75f, true);
    }


    public void addMap() {
        for (int i = 0; i < 100; i++) {
            cache.put(i, "value " + 1);
        }
    }

    public void remove(){
        Iterator<Map.Entry<Object, Object>> iterator = cache.entrySet().iterator();
        Map.Entry<Object, Object> next = iterator.next();
        Log.i("glide","glide = "+next.getValue()+" ,key = "+next.getKey());
    }
}
