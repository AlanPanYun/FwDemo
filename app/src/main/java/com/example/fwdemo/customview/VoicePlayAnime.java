package com.example.fwdemo.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class VoicePlayAnime extends View {


    Paint paint;


    public VoicePlayAnime(Context context) {
        super(context);

        init();
    }

    private void init() {
        paint = new Paint(Color.GREEN);
        paint.setAntiAlias(true);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
}
