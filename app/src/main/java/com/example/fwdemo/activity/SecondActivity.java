package com.example.fwdemo.activity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fwdemo.R;
import com.example.fwdemo.utils.AudioRecoderUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by qk14 on 2018/3/22.
 */

public class SecondActivity extends AppCompatActivity {

    private TextView tv;
    private AudioRecoderUtils mAudioRecoderUtils;
    private String recodPath = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv = (TextView) findViewById(R.id.tv_test);

        initData();

    }

    private void initPlay() {
        if (TextUtils.isEmpty(recodPath)) {
            Toast.makeText(this, "路径为空", Toast.LENGTH_LONG).show();
            return;
        }
        Button btnPlay = (Button) findViewById(R.id.btn_play);
        final MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(recodPath);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initData() {
        //当前布局文件的根layout
        final ConstraintLayout rl = (ConstraintLayout) findViewById(R.id.constraint_layout);

        final Button mButton = (Button) findViewById(R.id.button);

        //PopupWindow的布局文件
//        final View view = View.inflate(this, R.layout.layout_microphone, null);

//        final PopupWindowFactory mPop = new PopupWindowFactory(this,view);
//
//        //PopupWindow布局文件里面的控件
//        mImageView = (ImageView) view.findViewById(R.id.iv_recording_icon);
//        mTextView = (TextView) view.findViewById(R.id.tv_recording_time);

        mAudioRecoderUtils = new AudioRecoderUtils();

        //录音回调
        mAudioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {

            //录音中....db为声音分贝，time为录音时长
            @Override
            public void onUpdate(double db, long time) {
                //根据分贝值来设置录音时话筒图标的上下波动，下面有讲解
//                mImageView.getDrawable().setLevel((int) (3000 + 6000 * db / 100));
//                mTextView.setText(TimeUtils.long2String(time));
            }

            //录音结束，filePath为保存路径
            @Override
            public void onStop(String filePath) {
                recodPath = filePath;
                Toast.makeText(SecondActivity.this, "录音保存在：" + filePath, Toast.LENGTH_SHORT).show();
//                mTextView.setText(TimeUtils.long2String(0));
            }
        });

        //Button的touch监听
        mButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

//                        mPop.showAtLocation(rl, Gravity.CENTER,0,0);
//
//                        mButton.setText("松开保存");
                        mAudioRecoderUtils.startRecord();


                        break;

                    case MotionEvent.ACTION_UP:

                        mAudioRecoderUtils.stopRecord();        //结束录音（保存录音文件）
//                        mAudioRecoderUtils.cancelRecord();    //取消录音（不保存录音文件）
//                        mPop.dismiss();
                        mButton.setText("按住说话");

                        initPlay();

                        break;
                }
                return true;
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        File file = new File(recodPath);
        deleteFile(file);

    }

    public void deleteFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    this.deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                }
            }
        } else {

        }
    }
}
