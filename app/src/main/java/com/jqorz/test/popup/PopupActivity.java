package com.jqorz.test.popup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jqorz.test.R;

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2019/11/1
 */
public class PopupActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, PopupActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TestPopup(PopupActivity.this).showPopupWindow(R.id.btn_1);
            }
        });
    }


}
