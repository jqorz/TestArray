package com.jqorz.test.rotate;

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
 * @since 2019/10/29
 */
public class RotateActivity1 extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, RotateActivity1.class);
        context.startActivity(starter);
    }

    @Override
    protected void init() {
        TextView text = findViewById(R.id.text);
        text.setText("1");

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RotateActivity2.start(RotateActivity1.this);
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_rotate;
    }
}
