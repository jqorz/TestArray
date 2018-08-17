package com.jqorz.test.videoview;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jqorz.test.R;
import com.jqorz.test.base.BaseActivity;

/**
 * @author jqorz
 * @since 2018/8/17
 */
public class WebViewActivity extends BaseActivity {
    WebView mWebView;


    @Override
    protected void init() {
        mWebView = findViewById(R.id.webView);
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
        //android assets目录下html文件路径url为 file:///android_asset/profile.html
        mWebView.loadUrl(getIntent().getStringExtra("url"));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_webview;
    }


}
