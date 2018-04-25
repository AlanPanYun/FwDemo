package com.example.fwdemo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fwdemo.R;
import com.example.fwdemo.utils.QRCore;

public class QRCoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_core);


        final ImageView ivCore = (ImageView) findViewById(R.id.iv_core);
        Button btnPlay = (Button) findViewById(R.id.btn_start);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap qrImage = QRCore.createQRImage("12354669666", 300,
                        BitmapFactory.decodeResource(
                                QRCoreActivity.this.getResources(),
                                R.mipmap.ic_launcher_round));
                ivCore.setImageBitmap(qrImage);
            }
        });
    }
}
