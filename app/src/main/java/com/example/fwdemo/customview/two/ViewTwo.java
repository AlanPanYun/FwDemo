package com.example.fwdemo.customview.two;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import com.example.fwdemo.R;
import com.example.fwdemo.customview.BaseCusView;

/**
 * @author Alan
 * @date 2019/4/25
 */
public class ViewTwo extends BaseCusView {


    private Bitmap bitmap1;
    private Bitmap bitmap;
    private Xfermode xfermode;
    private Path path = new Path();
    private int width;

    public ViewTwo(Context context) {
        super(context);
    }

    public ViewTwo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewTwo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        Log.i("width", "width = " + width);
    }

    {

        paint.setStyle(Paint.Style.STROKE);

//        path.moveTo(50, 100);
//        path.rLineTo(50, 100);
//        path.rLineTo(80, -150);
//        path.rLineTo(100, 100);
//        path.rLineTo(70, -120);
//        path.rLineTo(150, 80);
//

        xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

        bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.app_logo);
        Shader shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);

        //        Shader shader = new LinearGradient(500,500,500,100,  Color.parseColor("#E91E63"),
        //          Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);

//        bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.amap_car);
//        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//
//        Shader shader2 = new ComposeShader(shader,shader1, PorterDuff.Mode.SRC_OVER);
//
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        paint.setStrokeWidth(10);
//        paint.setColor(Color.GRAY);
//        paint.setShader(shader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawCircle(100,100,100,paint);
//        canvas.drawBitmap(bitmap,0,0,paint);
//        paint.setXfermode(xfermode);
//        canvas.drawBitmap(bitmap1,0,0,paint);
//        paint.setXfermode(null);
// 使用 Paint.setPathEffect() 来设置不同的 PathEffect


        path.moveTo(10, 10);
        path.lineTo(width-1, 10);

        // 第一处：CornerPathEffect
        PathEffect pathEffect = new DashPathEffect(new float[]{5, 10, 5, 10}, 0);
        paint.setPathEffect(pathEffect);
        canvas.drawPath(path, paint);
//
//        canvas.save();
//        canvas.translate(500, 0);
//        // 第二处：DiscretePathEffect
//        canvas.drawPath(path, paint);
//        canvas.restore();
//
//        canvas.save();
//        canvas.translate(0, 200);
//        // 第三处：DashPathEffect
//        canvas.drawPath(path, paint);
//        canvas.restore();
//
//        canvas.save();
//        canvas.translate(500, 200);
//        // 第四处：PathDashPathEffect
//        canvas.drawPath(path, paint);
//        canvas.restore();
//
//        canvas.save();
//        canvas.translate(0, 400);
//        // 第五处：SumPathEffect
//        canvas.drawPath(path, paint);
//        canvas.restore();
//
//        canvas.save();
//        canvas.translate(500, 400);
//        // 第六处：ComposePathEffect
//        PathEffect pathEffect = new DashPathEffect(new float[]{5, 10, 5, 10}, 0);
//        paint.setPathEffect(pathEffect);
//        canvas.drawPath(path, paint);
//        canvas.restore();
    }
}
