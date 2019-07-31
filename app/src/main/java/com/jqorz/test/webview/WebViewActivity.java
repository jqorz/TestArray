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
 * 测试webview拦截get/post请求
 *
 * @author jqorz
 * @since 2019/7/31
 */
public class WebViewActivity extends BaseActivity {
    public static final String INTENT_URL = "INTENT_URL";

    public static void start(Context context, String url) {
        Intent starter = new Intent(context, WebViewActivity.class);
        starter.putExtra(INTENT_URL, url);
        context.startActivity(starter);
    }

    @Override
    protected void init() {
        WebView mWebView = findViewById(R.id.webView);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//滚动条风格，为0指滚动条不占用空间，直接覆盖在网页上
        //得到webview设置
        WebSettings webSettings = mWebView.getSettings();
        //允许使用javascript
//        webSettings.setJavaScriptEnabled(true);
        //设置字符编码

//        webSettings.setDefaultTextEncodingName("UTF-8");
        //支持缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        mWebView.loadUrl(getIntent().getStringExtra(INTENT_URL));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_webview;
    }

}
