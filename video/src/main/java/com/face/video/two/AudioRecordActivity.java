package com.face.video.two;

import android.Manifest;
import android.annotation.SuppressLint;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.face.library.base.activity.BaseActivity;
import com.face.library.utils.ToastUtils;
import com.face.video.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.functions.Consumer;

import static com.face.video.two.GlobalConfig.AUDIO_FORMAT;
import static com.face.video.two.GlobalConfig.CHANNEL_CONFIG;
import static com.face.video.two.GlobalConfig.SAMPLE_RATE_INHZ;

/**
 * @author Alan
 * @date 2019/8/12
 */
public class AudioRecordActivity extends BaseActivity {


    private AudioRecord audioRecord;
    private boolean isReording;
    private AudioTrack audioTrack;
    private FileInputStream fileInputStream;
    private byte[] audioData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_record);


        findViewById(R.id.tv_conver).setOnClickListener(this);
        findViewById(R.id.tv_play).setOnClickListener(this);
        findViewById(R.id.tv_record).setOnClickListener(this);
        findViewById(R.id.tv_stop).setOnClickListener(this);
    }

    public void startRecord() {
        final int minBUfferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE_INHZ,
                GlobalConfig.CHANNEL_CONFIG, AUDIO_FORMAT);
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
                SAMPLE_RATE_INHZ, GlobalConfig.CHANNEL_CONFIG, AUDIO_FORMAT, minBUfferSize);

        final byte data[] = new byte[minBUfferSize];
        final File file = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), "audio/test.pcm");
        if (!file.mkdirs()) {

        }

        if (file.exists()) {
            file.delete();
        }

        audioRecord.startRecording();
        isReording = true;

        new Thread(new Runnable() {
            @Override
            public void run() {

                FileOutputStream os = null;

                try {
                    os = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                if (os != null) {
                    while (isReording) {
                        int read = audioRecord.read(data, 0, minBUfferSize);
                        if (AudioRecord.ERROR_INVALID_OPERATION != read) {
                            try {
                                os.write(data);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }


                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    public void stopRecord() {

        isReording = false;
        if (audioRecord != null) {
            audioRecord.stop();
            audioRecord.release();
            audioRecord = null;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void playInMOdeStream() {
        int channelConfig = AudioFormat.CHANNEL_OUT_MONO;
        final int minBufferSize = AudioTrack.getMinBufferSize(SAMPLE_RATE_INHZ,
                channelConfig, AUDIO_FORMAT);

        audioTrack = new AudioTrack(
                new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build(),
                new AudioFormat.Builder().setSampleRate(SAMPLE_RATE_INHZ)
                        .setEncoding(AUDIO_FORMAT)
                        .setChannelMask(channelConfig)
                        .build(),
                minBufferSize,
                AudioTrack.MODE_STREAM,
                AudioManager.AUDIO_SESSION_ID_GENERATE);
        audioTrack.play();

        File file = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), "test.pcm");

        try {
            fileInputStream = new FileInputStream(file);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        byte[] tempBuffer = new byte[minBufferSize];
                        while (fileInputStream.available() > 0) {

                            int readCount = fileInputStream.read(tempBuffer);
                            if (readCount == AudioTrack.ERROR_INVALID_OPERATION ||
                                    readCount == AudioTrack.ERROR_BAD_VALUE) {
                                continue;
                            }

                            if (readCount != 0 && readCount != -1) {
                                audioTrack.write(tempBuffer, 0, readCount);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 播放，使用static模式
     */
    @SuppressLint("StaticFieldLeak")
    private void playInModeStatic() {
        // static模式，需要将音频数据一次性write到AudioTrack的内部缓冲区

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
//                    InputStream in = getResources().openRawResource(R.raw.ding);
                    InputStream in = null;
                    try {
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        for (int b; (b = in.read()) != -1; ) {
                            out.write(b);
                        }
                        audioData = out.toByteArray();
                    } finally {
                        in.close();
                    }
                } catch (IOException e) {
                }
                return null;
            }


            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            protected void onPostExecute(Void v) {

                // R.raw.ding铃声文件的相关属性为 22050Hz, 8-bit, Mono
                audioTrack = new AudioTrack(
                        new AudioAttributes.Builder()
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .build(),
                        new AudioFormat.Builder().setSampleRate(22050)
                                .setEncoding(AudioFormat.ENCODING_PCM_8BIT)
                                .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                                .build(),
                        audioData.length,
                        AudioTrack.MODE_STATIC,
                        AudioManager.AUDIO_SESSION_ID_GENERATE);
                audioTrack.write(audioData, 0, audioData.length);
                audioTrack.play();
            }

        }.execute();

    }



    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.tv_conver) {
            PcmToWavUtil pcmToWavUtil = new PcmToWavUtil(SAMPLE_RATE_INHZ, CHANNEL_CONFIG, AUDIO_FORMAT);
            File pcmFile = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), "test.pcm");
            File wavFile = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), "test.wav");


            if (!wavFile.mkdirs()) {

            }

            if (wavFile.exists()) {
                wavFile.delete();
            }

            pcmToWavUtil.pcmToWav(pcmFile.getAbsolutePath(), wavFile.getAbsolutePath());
            ToastUtils.showShort("conver");

        } else if (i == R.id.tv_record) {
            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            if (aBoolean) {
                                startRecord();
                                ToastUtils.showShort("record");
                            } else {

                            }
                        }
                    });


        } else if (i == R.id.tv_play) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                playInMOdeStream();
                ToastUtils.showShort("play");

            }

        } else if (i == R.id.tv_stop) {
            ToastUtils.showShort("stop");
            stopRecord();

        }
    }
}
