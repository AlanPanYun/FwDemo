package com.face.video.two;

import android.media.AudioFormat;

/**
 * @author Alan
 * @date 2019/8/12
 */
public class GlobalConfig {

    //采样率，现在能够保证在所有设备上使用的采样率是44100hz，但是其他采样率（22050，16000，11025）在一些设备上也可以使用
    public static final int SAMPLE_RATE_INHZ = 44100;

    //声道数 channel_in_mono and channel_in_stereo 其中channel_in_mono是保证在所有设备能够使用
    public static final int CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO;

    //返回的音频数据的格式，encoding_pcm_8bit,encoding_pcm_168it, and encoding_pcm_float
    public static final int AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;
}
