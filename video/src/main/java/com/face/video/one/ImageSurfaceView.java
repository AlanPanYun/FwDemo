package com.face.video.one;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.face.video.R;

/**
 * @author Alan
 * @date 2019/7/5
 */
public class ImageSurfaceView extends SurfaceView
        implements SurfaceHolder.Callback2,Runnable{

    private Canvas mCanvas;
    private SurfaceHolder holder;
    private Thread thread;
    private boolean isRunning;
    private Paint paint;


    private void init(){
        holder = getHolder();
        holder.addCallback(this);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
    }


    public ImageSurfaceView(Context context) {
        super(context);
    }

    public ImageSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ImageSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void surfaceRedrawNeeded(SurfaceHolder holder) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning = true;
        thread = new Thread(this);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;
    }



    private void draw(){
        try {

            mCanvas = holder.lockCanvas();

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.amap_bus);
            mCanvas.drawBitmap(bitmap,0,0,paint);
        }catch (Exception e){

        }finally {
            if (mCanvas !=null){
                holder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    @Override
    public void run() {
        while (isRunning){
            draw();
        }
    }
}
