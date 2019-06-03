package com.face.view.pathview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.face.library.base.BaseCusView;
import com.face.view.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alan
 * @date 2019/5/31
 */
public class CanvasView2 extends BaseCusView {

    private Bitmap mBitmap;
    private Path mPath = new Path();
    private Canvas mCanvas;

    public CanvasView2(Context context) {
        super(context);
    }

    public CanvasView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.colorAccent));
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(7);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_4444);
        mCanvas = new Canvas(mBitmap);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()){
            return false;
        }

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(),event.getY());
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                break;
        }
        mCanvas.drawPath(mPath,paint);

        invalidate();
        return true;

    }

    public Path[] getPath(){
        List<Path> paths = new ArrayList<>();
        PathMeasure pathMeasure = new PathMeasure();
        pathMeasure.setPath(mPath,false);

        Path path;
        do {
            path = new Path();
            path.rLineTo(0,0);
            pathMeasure.getSegment(0,pathMeasure.getLength(),path,true);
            if (!path.isEmpty()){
                paths.add(path);
            }



        }while (pathMeasure.nextContour());

        return paths.toArray(new Path[]{});
    }

    public void clear(){
        setPath(new Path());
    }

    public void setPath (Path path){
        mPath = path;
        mBitmap = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_4444);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawPath(mPath,paint);
        invalidate();
    }

    public void setLineWidth(int width){
        paint.setStrokeWidth(width);
        invalidate();
    }
}
