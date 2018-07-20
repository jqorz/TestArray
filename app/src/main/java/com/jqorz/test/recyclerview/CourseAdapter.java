package com.jqorz.test.recyclerview;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqorz.test.R;

import java.util.List;

/**
 * @author jqorz
 * @since 2018/7/20
 */
public class CourseAdapter extends BaseQuickAdapter<CourseBean, BaseViewHolder> {
    public CourseAdapter(@Nullable List<CourseBean> data) {
        super(R.layout.item_course, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseBean item) {
        helper.setText(R.id.tv_name, "" + helper.getAdapterPosition());
    }
}
