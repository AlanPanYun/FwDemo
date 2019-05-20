package com.face.like;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
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

        Path path = new Path();
        path.moveTo(20,20);
        path.lineTo(40,40);
        path.addArc(new RectF(10,10,100,100),8,90);

        paint.setTextSize(20);
        canvas.drawText("haha",4,40,paint);
        canvas.save();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.carpool_banner_show);

//        Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        paint.setShader(shader);
//        canvas.drawCircle(200,200,100,paint);
//
//
//        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.mipmap.carpool_banner_show);
//        BitmapShader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        paint.setShader(shader1);
//        canvas.drawCircle(100,1,1,paint);

        canvas.clipPath(path);
//        canvas.clipRect(80,20,900,200);
        canvas.drawBitmap(bitmap,2,2,paint);
        canvas.restore();
    }
}
