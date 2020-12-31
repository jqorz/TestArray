package com.jqorz.test.webview;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jqorz.test.R;
import com.jqorz.test.base.BaseActivity;

/**
 * copyright datedu
 * 测试webview加载本地资源
 *
 * @author jqorz
 * @since 2019/7/31
 */
public class WebView3Activity extends BaseActivity {
    public static final String INTENT_URL = "INTENT_URL";

    public static void start(Context context, String url) {
        Intent starter = new Intent(context, WebView3Activity.class);
        starter.putExtra(INTENT_URL, url);
        context.startActivity(starter);
    }

    @Override
    protected void init() {
        WebView mWebView = findViewById(R.id.mWebView);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//滚动条风格，为0指滚动条不占用空间，直接覆盖在网页上
        //得到webview设置
        WebSettings webSettings = mWebView.getSettings();
        //允许使用javascript
        webSettings.setJavaScriptEnabled(true);
        //设置字符编码
        webSettings.setDefaultTextEncodingName("UTF-8");
        //支持缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        mWebView.loadUrl("http://192.168.101.7:8080/pages/inquiry1/learningWall/learningWall?userId=00f14ac05c794042af49f185e36ae525&userid=00f14ac05c794042af49f185e36ae525&schoolId=4e61e5bf095c4255b9f478edeb70c195&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJEQVRFRFUtU0VDVVJJVFktQ0VOVEVSIiwic3ViIjoiMDBmMTRhYzA1Yzc5NDA0MmFmNDlmMTg1ZTM2YWU1MjUiLCJhdWQiOiJ1c2VyIiwiaWF0IjoxNjA4NzA2NjA0LCJleHAiOjE2MDkzMTE0MDQsIm5iZiI6MTYwODcwNjYwNH0.CprUwM82-cacx049f02aAZ4GSt7azT4x8cw_8HY7Tyc");

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_webview;
    }

}
