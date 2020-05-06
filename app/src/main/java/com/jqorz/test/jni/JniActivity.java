package com.jqorz.test.jni;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.jqorz.jni.JniGet;
import com.jqorz.test.R;
import com.jqorz.test.base.BaseActivity;

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2020/5/6
 */
public class JniActivity extends BaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, JniActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void init() {
        TextView tv_text = findViewById(R.id.tv_text);
        tv_text.setText(JniGet.getHello());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_jni;
    }
}
