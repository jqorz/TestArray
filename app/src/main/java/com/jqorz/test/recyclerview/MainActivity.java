package com.jqorz.test.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jqorz.test.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int GRID_COLUMN_SIZE = 2;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.mRecyclerView);
        final List<CourseBean> mData = new ArrayList<>();
        initData(mData);
        initRecyclerView(mData);
    }


    private void initData(List<CourseBean> mData) {
        //获取数据
        for (int i = 0; i < 3; i++) {
            mData.add(new CourseBean("9月6日"));
        }
        for (int i = 0; i < 2; i++) {
            mData.add(new CourseBean("9月5日"));
        }
        for (int i = 0; i < 1; i++) {
            mData.add(new CourseBean("9月4日"));
        }
        for (int i = 0; i < 4; i++) {
            mData.add(new CourseBean("9月3日"));
        }
        //将数据分组，保存每个数据在该组的位置
        int temp = 0;
        for (int i = 0; i < mData.size(); i++) {
            if (i > 0) {
                if (mData.get(i).getDate().equals(mData.get(i - 1).getDate())) {
                    temp++;
                } else {
                    temp = 0;
                }
                mData.get(i).setNum(temp);
            } else {
                mData.get(i).setNum(0);
            }
        }
    }

    private void initRecyclerView(final List<CourseBean> mData) {
        CourseAdapter courseAdapter = new CourseAdapter(mData);
        mRecyclerView.setAdapter(courseAdapter);
        TitleItemDecoration titleItemDecoration = new TitleItemDecoration(this) {
            @Override
            public boolean calculateShouldHaveHeader(int position) {
                return mData.get(position).getNum() >= 0 && mData.get(position).getNum() < GRID_COLUMN_SIZE;
            }

            @Override
            public String getTag(int position) {
                return mData.get(position).getDate();
            }
        };
        mRecyclerView.addItemDecoration(titleItemDecoration);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, GRID_COLUMN_SIZE);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //通过getSpanSize进行占位
                if (position < mData.size() - 1) {
                    if (!mData.get(position).getDate().equals(mData.get(position + 1).getDate()) && mData.get(position).getNum() % 2 == 0) {
                        return GRID_COLUMN_SIZE;
                    }
                }
                return 1;
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }

}
