package com.face.library.base.inter;

import android.view.View;

/**
 * @author Alan
 * @date 2019/6/17
 */
public interface IBaseView extends View.OnClickListener {



    void onWedigetClick(View view);


    void showVisiable(View...views);


}
