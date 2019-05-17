package com.face.library;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author Alan
 * @date 2019/5/9
 */
public class ImageLoadUtils {

    //http://img.pl.ccclubs.com/attached/20150820000914130383.jpg
    public static void loadImage(Context context, String url, ImageView imageView){
        if (TextUtils.isEmpty(url)){
            url ="http://img.pl.ccclubs.com/attached/20150820000914130383.jpg";
        }
        Glide.with(context).load(url).into(imageView);
    }
}
