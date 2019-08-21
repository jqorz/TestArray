package com.jqorz.test.js;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.jqorz.test.R;

public class JsActivity extends AppCompatActivity {

    private static final String LOG_TAG = "WebViewDemo";
    private WebView mWebView;
    private Handler mHandler = new Handler();

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_web_view);

        mWebView = findViewById(R.id.mWebView);


        WebSettings webSettings = mWebView.getSettings();
        webSettings.setSaveFormData(false);
        // 下面的一句话是必须的，必须要打开javaScript不然所做一切都是徒劳的
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);


        mWebView.setWebChromeClient(new MyWebChromeClient());

        // 看这里用到了 addJavascriptInterface 这就是我们的重点中的重点
        // 我们再看他的DemoJavaScriptInterface这个类。还要这个类一定要在主线程中
        mWebView.addJavascriptInterface(new DemoJavaScriptInterface(), "asdasd");

        mWebView.loadUrl("file:///android_asset/activity_htutwlan_main.html");
    }



    // 这是他定义由 addJavascriptInterface 提供的一个Object
     class DemoJavaScriptInterface {

        @JavascriptInterface
        public void clickOnAndroid() {
            mHandler.post(new Runnable() {
                public void run() {
                    // 此处调用 HTML 中的javaScript 函数
                    mWebView.loadUrl("javascript:wave()");
                }
            });
        }
    }

    /**
     * Provides a hook for calling "alert" from javascript. Useful for
     * 从javascript中提供了一个叫“提示框” 。这是很有用的
     * debugging your javascript.
     * 调试你的javascript。
     */
    final class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d(LOG_TAG, message);
            result.confirm();
            return true;
        }
    }

}