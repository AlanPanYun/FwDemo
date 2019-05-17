package com.face.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Alan
 * @date 2019/4/22
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class DrawOneView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    public DrawOneView(Context context) {
        super(context);
    }

    public DrawOneView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawOneView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10);

//        path.addArc(200,200,400,400,
//                -225,225);
//        path.arcTo(400,200,600,
//                400,-180,
//                225,false);
//        path.lineTo(400,542);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        float[] points = {0, 0, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50, 150, 100};
//// 绘制四个点：(50, 50) (50, 100) (100, 50) (100, 100)
//        canvas.drawPoints(points, 2 /* 跳过两个数，即前两个 0 */,
//                8 /* 一共绘制 8 个数（4 个点）*/, paint);
//
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawOval(30,30,100,300,paint);
//        canvas.drawRoundRect(100,100,500,300,120,50,paint);
//
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawArc(0,0,
//                800,510,-100,100,
//                true,paint);

//        canvas.drawPath(path,paint);
        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawArc(100,100,400,400,
//                -125,125,false,paint);

        path.cubicTo(100,150,300,-150,500,150);
        canvas.drawPath(path,paint);
    }

}
