package com.example.fwdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.fwdemo.hook.ClipHelper;
import com.face.library.ARouterConstant;

/**
 * @author Alan
 * @date 2019/9/25
 */
@Route(path = ARouterConstant.APP_HOOK)
public class HookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ClipHelper.bind();
        EditText editText = new EditText(this);

        setContentView(editText);

    }
}
