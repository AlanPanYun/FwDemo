package com.face.library.base;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Alan
 * @date 2019/4/25
 */
public class BaseCusView extends View {

    protected Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public BaseCusView(Context context) {
        super(context);
    }

    public BaseCusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseCusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
