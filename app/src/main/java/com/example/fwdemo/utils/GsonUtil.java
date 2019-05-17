package com.example.fwdemo.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * Created by qk14 on 2017/6/15.
 */

public class GsonUtil {


    public static Gson sGson = new Gson();

    //将Json数据解析成相应的映射对象
    public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
        if (TextUtils.isEmpty(jsonData)) {
            return null;
        }
        Gson gson = new Gson();
        T result = gson.fromJson(jsonData, type);
        return result;
    }


    //将Json数据解析成相应的映射对象列表
    public static <T> ArrayList<T> parseJsonToListWithGson(String jsonData, Class<T> type) {
        if (TextUtils.isEmpty(jsonData)) {
            return null;
        }

        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(jsonData).getAsJsonArray();

        ArrayList<T> arrayList = new ArrayList<>();
        Gson gson = new Gson();
        //for循环遍历JsonArray
        for (JsonElement jsonElement : jsonArray) {
            //使用GSON，直接转成Bean对象
            T userBean = gson.fromJson(jsonElement,type);
            arrayList.add(userBean);
        }
        return arrayList;
    }

    public static String getJsonStringFromObject(Object object) {
        String jsonString = sGson.toJson(object);
        return jsonString;
    }


}
