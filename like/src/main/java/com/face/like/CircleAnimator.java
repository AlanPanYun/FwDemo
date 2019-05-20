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

    final float radius = AppUtils.dpToPixel(80);

    float progress = 0;
    RectF rectF = new RectF();


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
        paint.setTextSize(AppUtils.dpToPixel(40));
        paint.setTextAlign(Paint.Align.CENTER);
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

        float centerx = getWidth()/2;
        float centery = getHeight()/2;

        paint.setColor(Color.parseColor("#Ef1E63"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(AppUtils.dpToPixel(15));
        rectF.set(centerx-radius,centery-radius,centerx+radius,centery+radius);
        canvas.drawArc(rectF,135,progress*2.7f,false,paint);

    }
}
