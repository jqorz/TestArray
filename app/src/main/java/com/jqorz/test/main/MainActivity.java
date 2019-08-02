package com.jqorz.test.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqorz.test.R;
import com.jqorz.test.base.BaseActivity;
import com.jqorz.test.webview.WebViewActivity;
import com.jqorz.test.wifi.WifiInfoActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected void init() {
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
        mAdapter.addData(new ItemBean("wifi信息查看", new Runnable() {
            @Override
            public void run() {
                WifiInfoActivity.start(mContext);
            }
        }));
        mAdapter.addData(new ItemBean("kotlin wifi信息查看", new Runnable() {
            @Override
            public void run() {
                WifiInfoActivity.start(mContext);
            }
        }));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }


}