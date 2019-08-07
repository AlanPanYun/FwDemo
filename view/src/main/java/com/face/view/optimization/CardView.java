package com.face.view.optimization;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.face.view.R;

/**
 * @author Alan
 * @date 2019/8/5
 */
public class CardView extends View {

    private Bitmap[] bitmaps = new Bitmap[3];
    private int[] ids = new int[]{R.drawable.amap_bus, R.drawable.amap_car, R.drawable.amap_man};

    public CardView(Context context) {
        super(context);
        initBitmap();
    }

    public CardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initBitmap();
    }

    private void initBitmap() {
        for (int i = 0; i < ids.length; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), ids[i]);
            bitmaps[i] = Bitmap.createScaledBitmap(bitmap, 510, 510, false);
        }
        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();

        canvas.translate(50, 200);

        for (int i = 0; i < bitmaps.length; i++) {
            canvas.translate(150, 0);
            canvas.save();
            if (i < bitmaps.length - 1) {
                canvas.clipRect(0, 0, 150, bitmaps[i].getHeight());
            }
            canvas.drawBitmap(bitmaps[i], 0, 0, null);
            canvas.restore();
        }

        canvas.restore();
    }
}
