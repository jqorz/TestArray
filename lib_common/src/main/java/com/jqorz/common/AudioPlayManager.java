package com.jqorz.common;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;

/**
 * <pre>
 *     copyright: datedu
 *     author : br2ant3
 *     e-mail : xxx@xx
 *     time   : 2019/04/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class AudioPlayManager {

    private static final String RESOURCE_BASE = "android.resource://";
    private static AudioPlayManager instance;
    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;

    private AudioFocusRequest mFocusRequest;
    private AudioAttributes mAudioAttributes;

    private boolean pause;

    private String mPlaySource;
    private int duration;
    private OnAudioPlayListener mAudioPlayListener;

    private AudioPlayManager(Context context) {
        initAudioManager(context);
    }

    public static AudioPlayManager getInstance(Context context) {
        if (instance == null) {
            synchronized (AudioPlayManager.class) {
                if (instance == null) {
                    instance = new AudioPlayManager(context);
                }
            }
        }
        return instance;
    }

    /**
     * 初始化音频管理器
     */
    private void initAudioManager(Context context) {
        //在线程中初始化会导致回调也会走在线程中
//        new Thread(){
//            @Override
//            public void run() {
//                Looper.prepare();
//                mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//                mAudioManager.setMode(AudioManager.MODE_IN_CALL);
//                mAudioManager.setSpeakerphoneOn(true);//默认为扬声器播放
//            }
//        }.start();
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setMode(AudioManager.MODE_IN_CALL);
        mAudioManager.setSpeakerphoneOn(true);//默认为扬声器播放

    }

    /**
     * 切换到外放
     */
    public void changeToSpeaker() {
        if (mAudioManager == null) {
            return;
        }
        mAudioManager.setMode(AudioManager.MODE_NORMAL);
        mAudioManager.setSpeakerphoneOn(true);
    }

    /**
     * 切换到耳机模式
     */
    public void changeToHeadset() {
        if (mAudioManager == null) {
            return;
        }
        mAudioManager.setSpeakerphoneOn(false);
    }

    /**
     * 切换到听筒
     */
    public void changeToReceiver() {
        if (mAudioManager == null) {
            return;
        }
        mAudioManager.setSpeakerphoneOn(false);
        mAudioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);//获取当前通话最大音量
        mAudioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, maxVolume, 0); //设置为0

    }

    //播放录音

    /**
     * @param filePath 支持本地音频地址，网络音频地址，本地uri
     * @param listener 虽然MediaPlayer可以直接播放网络音频，但是底层会wait与java同步，造成UI卡顿，所以这里先下载到本地再播放
     */
    public void playSound(Context context, String filePath, final OnAudioPlayListener listener) {

        //释放音频焦点
        if (mAudioPlayListener != listener) {
            release();
        }

        mAudioPlayListener = listener;

        //暂停后重播
        if (pause) {
            start();
            mAudioPlayListener.onReStart();
            return;
        }

        if (isPlaying()) {
            release();
        }

        if (TextUtils.isEmpty(filePath)) {
            mAudioPlayListener.onError();
            return;
        }

        mPlaySource = filePath;

        mAudioPlayListener.onStart();

        requestAudioFocus(context, filePath);



    }

    private void requestAudioFocus(Context context, String filePath) {
        int result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (mFocusRequest == null) {
                if (mAudioAttributes == null) {
                    mAudioAttributes = new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build();
                }
                mFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                        .setAudioAttributes(mAudioAttributes)
                        .setWillPauseWhenDucked(true)
                        .setOnAudioFocusChangeListener(mAudioPlayListener)
                        .build();
            }
            result = mAudioManager.requestAudioFocus(mFocusRequest);
        } else {
            result = mAudioManager.requestAudioFocus(mAudioPlayListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        }
        switch (result) {
            case AudioManager.AUDIOFOCUS_REQUEST_GRANTED://成功
                playLocalSound(context, filePath);
                break;
            case AudioManager.AUDIOFOCUS_REQUEST_FAILED: //失败
            case AudioManager.AUDIOFOCUS_REQUEST_DELAYED: //延迟获取
                if (mAudioPlayListener != null) mAudioPlayListener.onError();
                break;
            default:
                break;
        }
    }

    private void playLocalSound(Context context, String filePath) {

        if (!(new File(filePath).exists()) && !isRawResUri(filePath)) {
            if (mAudioPlayListener != null) mAudioPlayListener.onError();
            return;
        }
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
            //播放错误 防止崩溃
            mMediaPlayer.setOnErrorListener((mp, what, extra) -> {
                mMediaPlayer.reset();
                if (mAudioPlayListener != null) mAudioPlayListener.onError();
                return false;
            });
            mMediaPlayer.setOnPreparedListener(mp -> {
                duration = mMediaPlayer.getDuration();
                if (mAudioPlayListener != null)
                    mAudioPlayListener.onPrepared(duration);
            });
        } else {
            mMediaPlayer.reset();
        }
        try {
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnCompletionListener(mp -> {
                if (mAudioPlayListener != null) mAudioPlayListener.onCompletion();
            });
            if (isRawResUri(filePath)) {
                mMediaPlayer.setDataSource(context, Uri.parse(filePath));
            } else {
                mMediaPlayer.setDataSource(filePath);
            }
            mMediaPlayer.prepare();
            mMediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     */
    public void pause() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            pause = true;
        }
    }


    public void start() {
        if (mMediaPlayer != null && pause) {
            mMediaPlayer.start();
            pause = false;
        }
    }


    public void seekTo(int msec) {
        pause();
        mMediaPlayer.seekTo(msec);
        start();
    }

    public void pauseSeekTo(int msec) {
        if (pause) {
            mMediaPlayer.seekTo(msec);
        }
    }


    /**
     * activity 被销毁  释放
     */
    public void release() {
        if (mAudioPlayListener != null) {
            mAudioPlayListener.onRelease();
        }

        if (mAudioPlayListener != null) {
            mAudioManager.abandonAudioFocus(mAudioPlayListener);
        }

        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

        pause = false;
    }

    public boolean isPlaying() {
        return mMediaPlayer != null && mMediaPlayer.isPlaying();
    }

    public boolean isPause() {
        return mMediaPlayer != null && pause;
    }

    public boolean isRelease() {
        return mMediaPlayer == null || (!mMediaPlayer.isPlaying() && !pause);
    }

    public int getCurrentProgress() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public String getPlaySource() {
        return mPlaySource;
    }

    public int getDuration() {
        return duration;
    }

    private boolean isRawResUri(String uri) {
        return (null != uri) && uri.startsWith(RESOURCE_BASE);
    }

    public interface OnAudioPlayListener extends AudioManager.OnAudioFocusChangeListener {
        void onStart();

        void onDownLoadSuccess(String path);

        @Override
        void onAudioFocusChange(int focusChange);

        void onCompletion();

        void onError();

        void onPrepared(int duration);

        void onReStart();

        void onRelease();
    }

}
