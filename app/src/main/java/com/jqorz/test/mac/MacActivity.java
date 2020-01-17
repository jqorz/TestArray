package com.jqorz.test.mac;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jqorz.test.R;
import com.jqorz.test.base.BaseActivity;
import com.jqorz.test.util.permission.PermissionUtils;
import com.yanzhenjie.permission.Permission;

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2020/1/17
 */
public class MacActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_mac_info;

    public static void start(Context context) {
        Intent starter = new Intent(context, MacActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void init() {
        View btn_mac = findViewById(R.id.btn_mac);
        btn_mac.setOnClickListener(this);
        tv_mac_info = findViewById(R.id.tv_mac_info);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mac;
    }

    @Override
    public void onClick(View v) {
        tv_mac_info.setText("");
        PermissionUtils.readyPermission(this, new PermissionUtils.IPermissionListener() {
            @Override
            public void onSucceed() {
                String mac = DeviceUtils.getMacAddress();
                tv_mac_info.setText(String.format("mac = %s", mac));
                Log.e("mac2 ", mac);
            }
        }, Permission.READ_PHONE_STATE);

    }
}
