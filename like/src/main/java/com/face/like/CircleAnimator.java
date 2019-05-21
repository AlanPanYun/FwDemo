package com.face.like;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.face.library.base.BaseCusView;
import com.face.library.utils.AppUtils;

/**
 * @author Alan
 * @date 2019/5/20
 */
public class CircleAnimator extends BaseCusView {

    float radius = AppUtils.dpToPixel(80);

    RectF rectF = new RectF();
    float progress ;


    public CircleAnimator(Context context) {
        super(context);
    }

    public CircleAnimator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleAnimator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    {
        paint.setColor(Color.GRAY);
    }


    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int cx = getWidth()/2;
        int cy = getHeight()/2;

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(AppUtils.dpToPixel(20));
        paint.setStrokeCap(Paint.Cap.ROUND);

        rectF.set(cx-radius,cy-radius,cx+radius,cy+radius);
        canvas.drawArc(rectF,135,progress*2.7f,false,paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(AppUtils.dpToPixel(20));
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText((int)progress+"%",cx,cy-(paint.ascent()+paint.descent())/2,paint);
    }
}
