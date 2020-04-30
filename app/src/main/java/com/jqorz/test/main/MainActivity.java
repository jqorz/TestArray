package com.jqorz.test.main;

import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqorz.test.R;
import com.jqorz.test.aidl.AIDLActivity;
import com.jqorz.test.base.BaseActivity;
import com.jqorz.test.click.ClickActivity;
import com.jqorz.test.dashboard.DashActivity;
import com.jqorz.test.floatView.ControlActivity;
import com.jqorz.test.gson.GsonTestActivity;
import com.jqorz.test.mac.MacActivity;
import com.jqorz.test.popup.PopupActivity;
import com.jqorz.test.rotate.RotateActivity1;
import com.jqorz.test.themeAttr.ThemeActivity;
import com.jqorz.test.webview.WebViewActivity;
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        BaseQuickAdapter<ItemBean, BaseViewHolder> mAdapter = new BaseQuickAdapter<ItemBean, BaseViewHolder>(R.layout.item_main_select) {

            @Override
            protected void convert(BaseViewHolder helper, final ItemBean item) {
                Button btn = helper.getView(R.id.btn);
                btn.setText(item.getText());
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item.getRunnable().run();
                    }
                });
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addData(new ItemBean("WebView拦截", new Runnable() {
            @Override
            public void run() {
                WebViewActivity.start(mContext, "https://www.baidu.com");
            }
        }));
        mAdapter.addData(new ItemBean("wifi连接", new Runnable() {
            @Override
            public void run() {
                WifiConnectActivity.start(mContext);
            }
        }));
        mAdapter.addData(new ItemBean("Gson测试", new Runnable() {
            @Override
            public void run() {
                GsonTestActivity.start(mContext);
            }
        }));
        mAdapter.addData(new ItemBean("仪表盘动画", new Runnable() {
            @Override
            public void run() {
                DashActivity.start(mContext);
            }
        }));
        mAdapter.addData(new ItemBean("view属性", new Runnable() {
            @Override
            public void run() {
                ClickActivity.start(mContext);
            }
        }));
        mAdapter.addData(new ItemBean("屏幕旋转", new Runnable() {
            @Override
            public void run() {
                RotateActivity1.start(mContext);
            }
        }));
        mAdapter.addData(new ItemBean("悬浮窗", new Runnable() {
            @Override
            public void run() {
                ControlActivity.start(mContext);
            }
        }));
        mAdapter.addData(new ItemBean("popup", new Runnable() {
            @Override
            public void run() {
                PopupActivity.start(mContext);
            }
        }));
        mAdapter.addData(new ItemBean("点击测试", new Runnable() {
            @Override
            public void run() {
                onDisplaySettingButton();
            }
        }));
        mAdapter.addData(new ItemBean("获取mac", new Runnable() {
            @Override
            public void run() {
                MacActivity.start(mContext);
            }
        }));
        mAdapter.addData(new ItemBean("测试主题", new Runnable() {
            @Override
            public void run() {
                ThemeActivity.start(mContext);
            }
        }));
        mAdapter.addData(new ItemBean("测试AIDL", new Runnable() {
            @Override
            public void run() {
                AIDLActivity.start(mContext);
            }
        }));
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
