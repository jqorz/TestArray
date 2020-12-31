package com.jqorz.test.dashboard;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jqorz.test.R;
import com.jqorz.test.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2019/8/22
 */
public class DashActivity extends BaseActivity {
    private SimpleDateFormat mDateFormat;
    private int index = 0;

    public static void start(Context context) {
        Intent starter = new Intent(context, DashActivity.class);
        context.startActivity(starter);
    }


    @Override
    protected void init() {
        final DashboardView dashboardView1 = findViewById(R.id.dashboardView1);
        int[] bgColorIds = {R.color.color_red, R.color.color_orange, R.color.color_yellow, R.color.color_green, R.color.color_blue};
        String[] tips = {"信用较差", "信用中等", "信用良好", "信用优秀", "信用极好"};
        String bottom = getFormatTimeStr();
        final int[] minValues = {500, 550, 600, 650, 700};
        final int[] maxValues = {549, 599, 649, 699, 749};
        List<DashboardView.DataBean> dataBeans = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dataBeans.add(new DashboardView.DataBean(minValues[i], maxValues[i], tips[i], bottom, bgColorIds[i]));
        }
        dashboardView1.setDataBeans(dataBeans);
        dashboardView1.setMin(0);
        dashboardView1.setMax(900);
        final DashboardView2 dashboardView2 = findViewById(R.id.dashboardView2);
        final DashboardView3 dashboardView3 = findViewById(R.id.dashboardView3);
        dashboardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = (minValues[index] + maxValues[index]) / 2;
                dashboardView1.setCreditValueWithAnim(value);
                dashboardView2.setCreditValueWithAnim(value);
                dashboardView3.setCreditValue(value);
                index++;
                if (index > minValues.length - 1) index = 0;
            }
        });
    }

    private String getFormatTimeStr() {
        if (mDateFormat == null) {
            mDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        }
        return String.format("评估时间:%s", mDateFormat.format(new Date()));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_dashboard;
    }


}
