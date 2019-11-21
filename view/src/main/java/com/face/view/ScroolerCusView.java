package com.face.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

/**
 * @author Alan
 * @date 2019/9/21
 */
public class ScroolerCusView extends View {

    private Context context;
    private Scroller scroller;

    public ScroolerCusView(Context context) {
        super(context);
    }

    public ScroolerCusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void smoothScrollTo(int destX,int dextY){
        scroller = new Scroller(context);

        int scollX = getScrollX();
        int deltaX = destX - scollX;

        scroller.startScroll(scollX,0,deltaX,0,1000);
        invalidate();
    }

    @Override
    public void computeScroll() {

        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
    }
}
