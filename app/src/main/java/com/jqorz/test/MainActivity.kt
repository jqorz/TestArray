package com.jqorz.test

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jqorz.common.Logg
import com.jqorz.common.base.BaseActivity
import com.jqorz.test.basemodule.activity.rotate.RotateActivity1
import com.jqorz.test.basemodule.broadcast.SendBroadActivity
import com.jqorz.test.basemodule.content_provider.ContentProviderActivity
import com.jqorz.test.basemodule.service.ServiceTestActivity
import com.jqorz.test.basemodule.service.aidl.AIDLActivity
import com.jqorz.test.framework.mac.MacActivity
import com.jqorz.test.framework.networkstate.NetworkStateActivity
import com.jqorz.test.framework.themeAttr.ThemeActivity
import com.jqorz.test.framework.thread.ThreadTestActivity
import com.jqorz.test.framework.wifi.WifiConnectActivity
import com.jqorz.test.fundot.FundotActivity
import com.jqorz.test.io.file_concat.FileConcatActivity
import com.jqorz.test.jni.JniActivity
import com.jqorz.test.library.coil.CoilTestActivity
import com.jqorz.test.library.gson.GsonTestActivity
import com.jqorz.test.thread.crash.CrashActivity
import com.jqorz.test.view.click.ClickActivity
import com.jqorz.test.view.dashboard.DashActivity
import com.jqorz.test.view.webview.WebView2Activity
import com.jqorz.test.windows.floatView.ControlActivity
import com.jqorz.test.windows.popup.PopupActivity
import java.util.*

class MainActivity : BaseActivity() {
    private val mHits = ArrayList<Long>()
    override fun init() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView.layoutManager = GridLayoutManager(mContext, 3)
        val mAdapter: BaseQuickAdapter<ItemBean, BaseViewHolder> =
            object : BaseQuickAdapter<ItemBean, BaseViewHolder>(R.layout.item_main_select) {
                protected override fun convert(helper: BaseViewHolder, item: ItemBean) {
                    val btn = helper.getView<Button>(R.id.btn)
                    btn.text = item.text
                    btn.setOnClickListener { v: View? -> item.runnable.run() }
                }
            }
        mRecyclerView.adapter = mAdapter
        mAdapter.addData(ItemBean("Jni测试") { JniActivity.start(mContext) })
        mAdapter.addData(ItemBean("WebView拦截") {
            WebView2Activity.start(
                mContext,
                "https://fs.iclass30.com/aliba/resources/2021/03/08/1854ba91666140518d2af552c200b7f8/2b7ef17465014f2ca7198116c7b52bf3/3d9bb7d727ec52449e286f12bc617b98f2b9/f.txt"
            )
        })
        mAdapter.addData(ItemBean("wifi连接") { WifiConnectActivity.start(mContext) })
        mAdapter.addData(ItemBean("Gson测试") { GsonTestActivity.start(mContext!!) })
        mAdapter.addData(ItemBean("仪表盘动画") { DashActivity.start(mContext) })
        mAdapter.addData(ItemBean("view属性") { ClickActivity.start(mContext) })
        mAdapter.addData(ItemBean("屏幕旋转") { RotateActivity1.start(mContext) })
        mAdapter.addData(ItemBean("悬浮窗") { ControlActivity.start(mContext) })
        mAdapter.addData(ItemBean("popup") { PopupActivity.start(mContext) })
        mAdapter.addData(ItemBean("点击测试") { onDisplaySettingButton() })
        mAdapter.addData(ItemBean("获取mac") { MacActivity.start(mContext) })
        mAdapter.addData(ItemBean("测试主题") { ThemeActivity.start(mContext) })
        mAdapter.addData(ItemBean("测试AIDL") { AIDLActivity.start(mContext) })
        mAdapter.addData(ItemBean("测试CP") { ContentProviderActivity.start(mContext!!) })
        mAdapter.addData(ItemBean("文件合并") { FileConcatActivity.start(mContext!!) })
        mAdapter.addData(ItemBean("Coil测试") { CoilTestActivity.start(mContext!!) })
        mAdapter.addData(ItemBean("Crash测试") { CrashActivity.start(mContext!!) })
        mAdapter.addData(ItemBean("发送广播测试") { SendBroadActivity.start(mContext!!) })
        mAdapter.addData(ItemBean("网络状态监听") { NetworkStateActivity.start(mContext!!) })
        mAdapter.addData(ItemBean("测试丰豆") { FundotActivity.start(mContext!!) })
        mAdapter.addData(ItemBean("线程测试") { ThreadTestActivity.start(mContext!!) })
        mAdapter.addData(ItemBean("测试丰豆") { FundotActivity.start(mContext!!) })
        mAdapter.addData(ItemBean("service") { ServiceTestActivity.start(mContext!!) })


    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    private fun onDisplaySettingButton() {
        val current = SystemClock.uptimeMillis()
        if (mHits.size > 1 && current - mHits[mHits.size - 1] > 1000) {
            mHits.clear()
        }
        mHits.add(current)
        println("current" + current + " last" + (current - mHits[mHits.size - 1]))
        if (mHits.size > 5) {
            println("点了" + mHits.size)
            mHits.clear()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Logg.i("onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Logg.i("onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onStop() {
        Logg.i("onStop")
        super.onStop()
    }

    override fun onPause() {
        Logg.i("onPause")
        super.onPause()
    }

    override fun onDestroy() {
        Logg.i("onDestroy")
        super.onDestroy()
    }

    class ItemBean(val text: String, val runnable: Runnable)
}