package com.jqorz.test2.main;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jqorz.test2.R;
import com.jqorz.test2.contentprovider.ContentProviderClientActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Long> mHits = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();

    }

    private void initRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        BaseQuickAdapter<ItemBean, BaseViewHolder> mAdapter = new BaseQuickAdapter<ItemBean, BaseViewHolder>(R.layout.item_main_select) {

            @Override
            protected void convert(BaseViewHolder helper, final ItemBean item) {
                Button btn = helper.getView(R.id.btn);
                btn.setText(item.getText());
                btn.setOnClickListener(v -> item.getRunnable().run());
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addData(new ItemBean("ContentProvider客户端", () -> ContentProviderClientActivity.start(this)));
    }


}
