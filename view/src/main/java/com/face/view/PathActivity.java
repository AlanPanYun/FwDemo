package com.face.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Scroller;

/**
 * @author Alan
 * @date 2019/5/31
 */
public class PathActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_path);

        Scroller scroller = new Scroller(this);


    }

    private void smoothScrollTo(int destX,int destY){

//        int scollx = getSco
    }
}
