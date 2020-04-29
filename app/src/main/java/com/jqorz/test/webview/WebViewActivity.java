package com.jqorz.test.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jqorz.test.R;
import com.jqorz.test.base.BaseActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        mWebView.setWebViewClient(new WebViewClient() {
            private String lastTime = "";

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                try {
                    URL urlStr = new URL(request.getUrl().toString());
                    URLConnection rulConnection = urlStr.openConnection();
                    rulConnection.addRequestProperty("If-Modified-Since", lastTime);
                    HttpURLConnection conn = (HttpURLConnection) rulConnection;
                    // Read input
                    String charset = conn.getContentEncoding() != null ? conn.getContentEncoding() : Charset.defaultCharset().displayName();
                    String mime = conn.getContentType();
                    InputStream isContents = conn.getInputStream();
                    WebResourceResponse response = new WebResourceResponse(mime, charset, isContents);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        //设置状态码
                        response.setStatusCodeAndReasonPhrase(conn.getResponseCode(), conn.getResponseMessage());
                        Map<String, List<String>> conResponseHeader = conn.getHeaderFields();
                        Map<String, String> responseHeader = new HashMap<>();
                        for (Map.Entry<String, List<String>> entry : conResponseHeader.entrySet()) {
                            if (!TextUtils.isEmpty(entry.getKey())) {
                                responseHeader.put(entry.getKey(), entry.getValue().get(0));
                            }
                        }
                        response.setResponseHeaders(responseHeader);
                    }
                    String resTime = conn.getHeaderField("Last-Modified");
                    lastTime = resTime;
                    return response;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_webview;
    }

}
