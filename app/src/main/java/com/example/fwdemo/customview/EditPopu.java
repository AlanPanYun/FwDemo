package com.example.fwdemo.customview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.fwdemo.R;

/**
 * @author Alan
 * @date 2019/4/16
 */
public class EditPopu extends PopupWindow {


    public EditPopu(Context context) {
        super(context);

        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_popu_edit, null);


        setContentView(inflate);

        setFocusable(true);
        setOutsideTouchable(true);
        setTouchable(true);
    }


}
