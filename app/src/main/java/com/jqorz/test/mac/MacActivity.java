package com.jqorz.test.mac;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.jqorz.test.R;
import com.jqorz.test.base.BaseActivity;

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2020/1/17
 */
public class MacActivity extends BaseActivity implements View.OnClickListener {

    public static void start(Context context) {
        Intent starter = new Intent(context, MacActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void init() {
        View btn_mac = findViewById(R.id.btn_mac);
        btn_mac.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mac;
    }

    @Override
    public void onClick(View v) {
        String mac = DeviceUtils.getMacAddress();
        TextView tv_mac_info = findViewById(R.id.tv_mac_info);
        tv_mac_info.setText(String.format("mac = %s", mac));
    }
}
