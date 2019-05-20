package com.face.library.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Alan
 * @date 2018/4/18
 */
public class DateUtils {

    public static String getCurrentTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }
}
