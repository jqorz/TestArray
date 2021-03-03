package com.jqorz.test

import android.os.SystemClock
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jqorz.common.base.BaseActivity
import com.jqorz.test.aidl.AIDLActivity
import com.jqorz.test.click.ClickActivity
import com.jqorz.test.coil.CoilTestActivity
import com.jqorz.test.content_provider.ContentProviderActivity
import com.jqorz.test.crash.CrashActivity
import com.jqorz.test.dashboard.DashActivity
import com.jqorz.test.file_concat.FileConcatActivity
import com.jqorz.test.floatView.ControlActivity
import com.jqorz.test.gson.GsonTestActivity
import com.jqorz.test.jni.JniActivity
import com.jqorz.test.mac.MacActivity
import com.jqorz.test.popup.PopupActivity
import com.jqorz.test.rotate.RotateActivity1
import com.jqorz.test.themeAttr.ThemeActivity
import com.jqorz.test.webview.WebView2Activity
import com.jqorz.test.wifi.WifiConnectActivity
import java.util.*

class MainActivity : BaseActivity() {
    private val mHits = ArrayList<Long>()
    override fun init() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView.layoutManager = GridLayoutManager(mContext, 3)
        val mAdapter: BaseQuickAdapter<ItemBean, BaseViewHolder> = object : BaseQuickAdapter<ItemBean, BaseViewHolder >(R.layout.item_main_select) {
            protected override fun convert(helper: BaseViewHolder, item: ItemBean) {
                val btn = helper.getView<Button>(R.id.btn)
                btn.text = item.text
                btn.setOnClickListener { v: View? -> item.runnable.run() }
            }
        }
        mRecyclerView.adapter = mAdapter
        mAdapter.addData(ItemBean("Jni测试") { JniActivity.start(mContext) })
        mAdapter.addData(ItemBean("WebView拦截") { WebView2Activity.start(mContext, "https://datedu.oss-cn-hangzhou.aliyuncs.com/Android/test/test_jq.html") })
        mAdapter.addData(ItemBean("wifi连接") { WifiConnectActivity.start(mContext) })
        mAdapter.addData(ItemBean("Gson测试") { GsonTestActivity.start(mContext) })
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

    class ItemBean(val text: String, val runnable: Runnable)
}