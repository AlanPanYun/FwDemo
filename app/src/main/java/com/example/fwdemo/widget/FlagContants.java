package com.example.fwdemo.widget;

import android.support.annotation.StringDef;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Alan
 * @date 2019/5/24
 */
public class FlagContants {

    public static final String UNDEFINE = "undefine";
    public static final String OK = "ok";
    public static final String ERROR = "error";

    private @FlagDef
    String flag = UNDEFINE;

    @Documented // 表示开启Doc文档
    @StringDef({
            OK,
            ERROR
    }) //限定为 FlagContants.OK, FlagContants.ERROR
    @Target({
            ElementType.PARAMETER,
            ElementType.FIELD,
            ElementType.METHOD,
    }) //表示注解作用范围，参数注解，成员注解，方法注解
    @Retention(RetentionPolicy.SOURCE) //表示注解所存活的时间,在运行时,而不会存在 .class 文件中
    public @interface FlagDef { //接口，定义新的注解类型
    }

    public @FlagDef
    String getFlag() {
        return flag;
    }

    public void setFlag(@FlagDef String flag) {
        this.flag = flag;
    }
}
