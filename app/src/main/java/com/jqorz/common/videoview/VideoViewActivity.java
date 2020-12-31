package com.jqorz.common.videoview;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

import com.jqorz.common.R;
import com.jqorz.common.base.BaseActivity;
import com.jqorz.common.webview.WebViewActivity;

/**
 * @author jqorz
 * @since 2018/8/17
 */
public class VideoViewActivity extends BaseActivity {
    public static final String INTENT_URL = "INTENT_URL";

    public static void start(Context context, String url) {
        Intent starter = new Intent(context, WebViewActivity.class);
        starter.putExtra(INTENT_URL, url);
        context.startActivity(starter);
    }


    @Override
    protected void init() {
        VideoView videoview = findViewById(R.id.videoview);
//        String uri = "android.resource://" + getPackageName() + "/" + R.raw.vv;
//        String uri = "http://fs.datedu.cn/aliba/resources/2018/06/13/mp4/2018-08-11-5ff92717-3a71-4398-8411-ea6dcc35d4d4/ware/video.mp4";
        String uri = getIntent().getStringExtra(INTENT_URL);
        videoview.setVideoURI(Uri.parse(uri));
        videoview.requestFocus();
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                // TODO Auto-generated method stub
                mp.setLooping(true);//设置视频重复播放
            }
        });
        videoview.start();//播放视频
        MediaController medis = new MediaController(this);//显示控制条
        videoview.setMediaController(medis);
        medis.setMediaPlayer(videoview);//设置控制的对象
        medis.show();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_videoview;
    }
}
