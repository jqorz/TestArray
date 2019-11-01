package com.jqorz.test.popup;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jqorz.test.R;
import com.jqorz.test.base.BaseActivity;

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2019/11/1
 */
public class PopupActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, PopupActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void init() {
        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TestPopup(PopupActivity.this).showPopupWindow(R.id.btn1);
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_popup;
    }
}
