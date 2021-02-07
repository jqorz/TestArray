package com.jqorz.test.js

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.BindView
import com.jqorz.test.R
import com.jqorz.common.base.BaseActivity
import java.io.ByteArrayOutputStream
import java.io.IOException

class WebViewActivity : BaseActivity() {
    @BindView(R.id.mWebView)
    var mWebView: WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        mWebView = findViewById(R.id.mWebView)
        val url = "http://xc.hfut.edu.cn/"
        initWebView(url)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(url: String) {
        if (TextUtils.isEmpty(url)) {
            return
        }
        mWebView!!.loadUrl(url)
        mWebView!!.webViewClient = MyWebViewClient() //自定义的网页视图
        mWebView!!.addJavascriptInterface(JavascriptInterface(), "imagelistner") //参数1是JavascriptInterface对象，参数2是一个在JavaScript中传入的别名
        val mWebSettings = mWebView!!.settings
        mWebSettings.javaScriptEnabled = true //允许网页使用js
        mWebSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
    }

    /**
     * 从assets文件夹下里的文件读取js函数
     * 注意:在Android studio中编辑js，会出现换行变为/r/n，使得js无法正常使用，因此只能在windows资源管理器中编辑
     *
     * @return js函数
     */
    fun readJS(fileName: String?): String? {
        try {
            val inStream = assets.open(fileName) //写入输入流
            val outStream = ByteArrayOutputStream()
            val bytes = ByteArray(1024)
            var len: Int
            while (inStream.read(bytes).also { len = it } > 0) {
                outStream.write(bytes, 0, len)
            }
            return outStream.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    override fun init() {}
    override fun getLayoutResId(): Int {
        return 0
    }

    /**
     * 自定义的网页视图
     * 教程:http://blog.csdn.net/ruancoder/article/details/53958773
     */
    private inner class MyWebViewClient : WebViewClient() {
        override fun onPageFinished(webView: WebView, s: String) {
            mWebView!!.loadUrl("javascript:(" + readJS("pic_fullscreen.js") + ")()")
        }
    }

    /**
     * 自定义js接口，执行assets文件夹下的js文件可以调用此函数
     * 我们需要编写一个函数，遍历所有的<img></img>标签，为每个img对象添加onclick事件。
     * 当img执行onclick事件时，调用Java代码启动显示大图页面，并传递自身的src属性。
     * 教程http://blog.csdn.net/ruancoder/article/details/53958773
     */
    private inner class JavascriptInterface {
        @android.webkit.JavascriptInterface
        fun startPhotoActivity(imageUrl: String) {
            if (!imageUrl.contains("icon")) { //图片url包含"icon"表明是小图标，不需要放大查看
            }
        }
    }
}