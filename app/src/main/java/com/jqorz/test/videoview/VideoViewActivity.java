package com.jqorz.test.videoview;

import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

import com.jqorz.test.R;
import com.jqorz.test.base.BaseActivity;

/**
 * @author jqorz
 * @since 2018/8/17
 */
public class VideoViewActivity extends BaseActivity {

    private VideoView videoview;


    @Override
    protected void init() {
        videoview = findViewById(R.id.videoview);
//        String uri = "android.resource://" + getPackageName() + "/" + R.raw.vv;
//        String uri = "http://fs.datedu.cn/aliba/resources/2018/06/13/mp4/2018-08-11-5ff92717-3a71-4398-8411-ea6dcc35d4d4/ware/video.mp4";
        String uri = getIntent().getStringExtra("url");
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
