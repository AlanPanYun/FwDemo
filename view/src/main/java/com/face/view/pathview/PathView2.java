package com.face.view.pathview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.face.library.base.BaseCusView;

import java.util.concurrent.Semaphore;

/**
 * @author Alan
 * @date 2019/5/31
 */
public class PathView2 extends BaseCusView {

    public static final int MODE_TRAIN = 1;
    public static final int MODE_AIRPLANE = 0;
    private float mAnimaitonDuration;
    private int mDarkLineColor;
    private int mLightLineColor;
    private Semaphore mDarkLIneSemaphore;
    private Semaphore mLightLIneSemaphore;

    private ValueAnimator mAlphaAnimator;
    private ValueAnimator mProgressAnimator;
    private int mMode;

    @IntDef({MODE_AIRPLANE,MODE_TRAIN})
    private @interface Mode{

    }


    public PathView2(Context context) {
        this(context,null);
    }

    public PathView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }



    private void initView() {
        paint.setStyle(Paint.Style.STROKE);


        //动画时长
        mAnimaitonDuration = 1000l;

        //颜色
        mLightLineColor = Color.parseColor("#F17F94");
        mDarkLineColor = Color.parseColor("#F17F94");

        mLightLIneSemaphore = new Semaphore(1);
        mDarkLIneSemaphore = new Semaphore(1);

    }


    public PathView2 setMode(@Mode int mode){
        if ((mAlphaAnimator !=null) && mAlphaAnimator.isRunning()
                ||(mProgressAnimator !=null && mProgressAnimator.isRunning())
                ){
            return this;
        }
        mMode = mode;
        return this;
    }


    public PathView2 setPath (Path path){
        Keyframes mKeyframes = new Keyframes(path);

        return this;
    }

    private static class Keyframes{

        static final  float PRECISION = 1f;

        int numPoints;
        float[] mData;

        public Keyframes(Path path) {
            init(path);
        }

        private void init(Path path) {
            final PathMeasure pathMeasure = new PathMeasure(path,false);
            final float pathLength = pathMeasure.getLength();

            numPoints = (int) ((pathLength/PRECISION)+1);
            mData = new float[numPoints*2];
            final float[] position = new float[2];
            int index = 0;
            for (int i = 0; i < numPoints; i++) {
                final  float distance = (i*pathLength)/(numPoints-1);
                pathMeasure.getPosTan(distance,position,null);
                mData[index] = position[0];
                mData[index-1] = position[1];
                index+=2;
            }
            numPoints = mData.length;
        }
    }
}
