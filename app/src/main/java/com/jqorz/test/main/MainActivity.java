package com.jqorz.test.main;

import android.os.SystemClock;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jqorz.common.base.BaseActivity;
import com.jqorz.test.R;
import com.jqorz.test.aidl.AIDLActivity;
import com.jqorz.test.click.ClickActivity;
import com.jqorz.test.coil.CoilTestActivity;
import com.jqorz.test.content_provider.ContentProviderActivity;
import com.jqorz.test.dashboard.DashActivity;
import com.jqorz.test.file_concat.FileConcatActivity;
import com.jqorz.test.floatView.ControlActivity;
import com.jqorz.test.gson.GsonTestActivity;
import com.jqorz.test.jni.JniActivity;
import com.jqorz.test.mac.MacActivity;
import com.jqorz.test.popup.PopupActivity;
import com.jqorz.test.rotate.RotateActivity1;
import com.jqorz.test.themeAttr.ThemeActivity;
import com.jqorz.test.webview.WebView2Activity;
import com.jqorz.test.wifi.WifiConnectActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ArrayList<Long> mHits = new ArrayList<>();

    @Override
    protected void init() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        BaseQuickAdapter<ItemBean, BaseViewHolder> mAdapter = new BaseQuickAdapter<ItemBean, BaseViewHolder>(R.layout.item_main_select) {

            @Override
            protected void convert(BaseViewHolder helper, final ItemBean item) {
                Button btn = helper.getView(R.id.btn);
                btn.setText(item.getText());
                btn.setOnClickListener(v -> item.getRunnable().run());
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addData(new ItemBean("Jni测试", () -> JniActivity.start(mContext)));
        mAdapter.addData(new ItemBean("WebView拦截", () -> WebView2Activity.start(mContext, "https://datedu.oss-cn-hangzhou.aliyuncs.com/Android/test/test_jq.html")));
        mAdapter.addData(new ItemBean("wifi连接", () -> WifiConnectActivity.start(mContext)));
        mAdapter.addData(new ItemBean("Gson测试", () -> GsonTestActivity.start(mContext)));
        mAdapter.addData(new ItemBean("仪表盘动画", () -> DashActivity.start(mContext)));
        mAdapter.addData(new ItemBean("view属性", () -> ClickActivity.start(mContext)));
        mAdapter.addData(new ItemBean("屏幕旋转", () -> RotateActivity1.start(mContext)));
        mAdapter.addData(new ItemBean("悬浮窗", () -> ControlActivity.start(mContext)));
        mAdapter.addData(new ItemBean("popup", () -> PopupActivity.start(mContext)));
        mAdapter.addData(new ItemBean("点击测试", this::onDisplaySettingButton));
        mAdapter.addData(new ItemBean("获取mac", () -> MacActivity.start(mContext)));
        mAdapter.addData(new ItemBean("测试主题", () -> ThemeActivity.start(mContext)));
        mAdapter.addData(new ItemBean("测试AIDL", () -> AIDLActivity.start(mContext)));
        mAdapter.addData(new ItemBean("测试CP", () -> ContentProviderActivity.start(mContext)));
        mAdapter.addData(new ItemBean("文件合并", () -> FileConcatActivity.start(mContext)));
        mAdapter.addData(new ItemBean("Coil测试", () -> CoilTestActivity.start(mContext)));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    private void onDisplaySettingButton() {
        long current = SystemClock.uptimeMillis();
        if (mHits.size() > 1 && current - mHits.get(mHits.size() - 1) > 1000) {
            mHits.clear();
        }
        mHits.add(current);
        System.out.println("current" + current + " last" + (current - mHits.get(mHits.size() - 1)));
        if (mHits.size() > 5) {
            System.out.println("点了" + mHits.size());
            mHits.clear();
        }

    }
}
