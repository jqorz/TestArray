package com.jqorz.test.webview;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jqorz.test.R;
import com.jqorz.common.base.BaseActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * copyright datedu
 * 测试webview加载本地资源
 *
 * @author jqorz
 * @since 2019/7/31
 */
public class WebView2Activity extends BaseActivity {
    public static final String INTENT_URL = "INTENT_URL";

    public static void start(Context context, String url) {
        Intent starter = new Intent(context, WebView2Activity.class);
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
//        webSettings.setJavaScriptEnabled(true);
        //设置字符编码

//        webSettings.setDefaultTextEncodingName("UTF-8");
        //支持缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        mWebView.loadUrl(getIntent().getStringExtra(INTENT_URL));
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                FileInputStream input;

                String url = webResourceRequest.getUrl().toString();

                String key = "localfile:";
                /*如果请求包含约定的字段 说明是要拿本地的图片*/
                if (url.contains(key)) {
                    String imgPath = url.replace(key, "");
                    Log.i("本地图片路径：", imgPath.trim());
                    try {
                        /*重新构造WebResourceResponse  将数据已流的方式传入*/
                        input = new FileInputStream(new File(imgPath.trim()));

                        /*返回WebResourceResponse*/
                        return new WebResourceResponse("image/jpg", "UTF-8", input);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();

                    }

                }
                return super.shouldInterceptRequest(webView, webResourceRequest);
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_webview;
    }

}
