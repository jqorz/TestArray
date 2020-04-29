package com.jqorz.test.js;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jqorz.test.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;

public class WebViewActivity extends AppCompatActivity {
    @BindView(R.id.mWebView)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView = findViewById(R.id.mWebView);
        String url = "http://xc.hfut.edu.cn/";
        initWebView(url);
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new MyWebViewClient());//自定义的网页视图
        mWebView.addJavascriptInterface(new JavascriptInterface(), "imagelistner");//参数1是JavascriptInterface对象，参数2是一个在JavaScript中传入的别名

        WebSettings mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);//允许网页使用js
        mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }


    /**
     * 从assets文件夹下里的文件读取js函数
     * 注意:在Android studio中编辑js，会出现换行变为/r/n，使得js无法正常使用，因此只能在windows资源管理器中编辑
     *
     * @return js函数
     */
    public String readJS(String fileName) {
        try {
            InputStream inStream = getAssets().open(fileName);//写入输入流
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inStream.read(bytes)) > 0) {
                outStream.write(bytes, 0, len);
            }
            return outStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 自定义的网页视图
     * 教程:http://blog.csdn.net/ruancoder/article/details/53958773
     */
    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView webView, String s) {
            mWebView.loadUrl("javascript:(" + readJS("pic_fullscreen.js") + ")()");
        }
    }


    /**
     * 自定义js接口，执行assets文件夹下的js文件可以调用此函数
     * 我们需要编写一个函数，遍历所有的<img>标签，为每个img对象添加onclick事件。
     * 当img执行onclick事件时，调用Java代码启动显示大图页面，并传递自身的src属性。
     * 教程http://blog.csdn.net/ruancoder/article/details/53958773
     */
    private class JavascriptInterface {
        @android.webkit.JavascriptInterface
        public void startPhotoActivity(String imageUrl) {
            if (!imageUrl.contains("icon")) {//图片url包含"icon"表明是小图标，不需要放大查看

            }
        }
    }
}
