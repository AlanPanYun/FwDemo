package com.face.like;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Alan
 * @date 2019/5/12
 */
public class CirlceView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CirlceView(Context context) {
        super(context);
    }

    public CirlceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.carpool_banner_show);

        Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        canvas.drawCircle(200,200,100,paint);
    }
}
