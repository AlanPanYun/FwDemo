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
    private long mAnimationDuration = 2000;
    private int mAlpha;
    private ValueAnimator mAlphaAnimator; // 动画透明度


    public PathCircle(Context context) {
        this(context, null);
    }

    public PathCircle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    private void initView() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(7);

        mPath.addCircle(400, 400, 100, Path.Direction.CW);

        pathMeasure = new PathMeasure();
        pathMeasure.setPath(mPath, true);

        mLength = pathMeasure.getLength();

        mDst = new Path();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1).setDuration(mAnimationDuration);
        valueAnimator.addUpdateListener(animation -> {
            animatedValue = (float) animation.getAnimatedValue();
            if (animatedValue > 0.8f) {
                // 时长是总时长的30%
//                mAlphaAnimator = ValueAnimator.ofInt(255, 0).setDuration((long) (mAnimationDuration * 0.2f));
//                mAlphaAnimator.addUpdateListener(animation1 -> {
//                    mAlpha = (int) animation1.getAnimatedValue();
//                });
            }else {
//                mAlphaAnimator = ValueAnimator.ofInt(10, 255).setDuration((long) (mAnimationDuration * 0.8f));
//                mAlphaAnimator.addUpdateListener(animation1 -> {
//                    mAlpha = (int) animation1.getAnimatedValue();
//                });
            }
            mAlphaAnimator.start();
            invalidate();
        });

        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mAlphaAnimator !=null){
            paint.setAlpha(mAlpha);
        }

        mDst.reset();
        mDst.lineTo(0, 0);
        float stop = mLength * animatedValue;
        float start = (float) (stop - (0.5 - Math.abs(animatedValue - 0.5)) * mLength);
        pathMeasure.getSegment(start, stop, mDst, true);
        canvas.drawPath(mDst, paint);
    }
}
