package com.jqorz.common.videoview;

import android.view.View;

import com.jqorz.common.R;
import com.jqorz.common.base.BaseActivity;

/**
 * @author jqorz
 * @since 2018/8/17
 * 用于测试原生WebView和videoView对不同视频的支持情况
 */
public class SelectActivity extends BaseActivity {
    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_videoview_select;
    }

    public void onVideoSelect(View v) {
        //微课视频（需要第三方播放引擎）
        String uri = "http://fs.datedu.cn/aliba/resources/2018/06/13/mp4/2018-08-11-5ff92717-3a71-4398-8411-ea6dcc35d4d4/ware/video.mp4";
        VideoViewActivity.start(this, uri);
    }

    public void onVideoSelect2(View view) {
        String uri = "http://zbywsvod.weclassroom.com/vod/zby/_2382331_154555_7.mp4";
        VideoViewActivity.start(this, uri);
    }

    public void onWebSelect(View v) {
        String uri = "file:///android_asset/videohtml.html";
        WebViewPlayActivity.start(this, uri);
    }

    public void onWebSelect2(View view) {
        String uri = "file:///android_asset/videohtml2.html";
        WebViewPlayActivity.start(this, uri);
    }


}
