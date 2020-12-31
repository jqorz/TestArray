package com.jqorz.common.popup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jqorz.common.R;

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
        View view = findViewById(R.id.btn_1);
        view.setOnClickListener(v -> new TestPopup(PopupActivity.this).showPopupWindow(view));
    }


}
