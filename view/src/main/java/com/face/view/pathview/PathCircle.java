package com.face.view.pathview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.face.library.base.BaseCusView;

/**
 * @author Alan
 * @date 2019/5/31
 */
public class PathCircle extends BaseCusView {

    private Path mPath = new Path();
    private float mLength;
    private Path mDst;
    private float animatedValue;
    private PathMeasure pathMeasure;


    public PathCircle(Context context) {
        this(context,null);
    }

    public PathCircle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    private void initView() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(7);

        mPath.addCircle(400,400,100, Path.Direction.CW);

        pathMeasure = new PathMeasure();
        pathMeasure.setPath(mPath,true);

        mLength = pathMeasure.getLength();

        mDst = new Path();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(2000);
        valueAnimator.addUpdateListener(animation -> {
            animatedValue = (float) animation.getAnimatedValue();
            invalidate();
        });

        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mDst.reset();
        mDst.lineTo(0,0);
        float stop = mLength* animatedValue;
        float start = (float) (stop - (0.5 - Math.abs(animatedValue - 0.5))*mLength);
        pathMeasure.getSegment(start,stop,mDst,true);
        canvas.drawPath(mDst,paint);
    }
}
