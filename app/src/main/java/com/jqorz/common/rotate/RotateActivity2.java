package com.jqorz.common.rotate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.widget.TextView;

import com.jqorz.common.R;
import com.jqorz.common.base.BaseActivity;

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2019/10/29
 */
public class RotateActivity2 extends BaseActivity {
    private int fromOrientation;

    public static void start(Context context) {
        Intent starter = new Intent(context, RotateActivity2.class);
        context.startActivity(starter);
    }

    @Override
    protected void init() {
        TextView text = findViewById(R.id.text);
        text.setText("2");
        fromOrientation = getIntent().getIntExtra("fromOrientation", ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (android.os.Build.VERSION.SDK_INT >= 27 && fromOrientation != ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
            setRequestedOrientation(fromOrientation);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_rotate;
    }
}
