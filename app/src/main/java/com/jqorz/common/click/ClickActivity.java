package com.jqorz.common.click;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.jqorz.common.R;
import com.jqorz.common.util.ToastUtil;

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2018/4/4
 */
public class ClickActivity extends AppCompatActivity {
    private String TAG = "ClickActivity";

    public static void start(Context context) {
        Intent starter = new Intent(context, ClickActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);
        findViewById(R.id.parent).setEnabled(false);
        findViewById(R.id.parent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "click parent");
                ToastUtil.showToast(ClickActivity.this, "parent");
            }
        });
        final View child = findViewById(R.id.child);
        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "click child");
                ToastUtil.showToast(ClickActivity.this, "child");
            }
        });
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showText(child);
                child.setTranslationX(dp2px(20));
                showText(child);
            }
        });
    }

    private void showText(View view) {

        StringBuilder builder = new StringBuilder();
        builder.append("getLeft = ").append(view.getLeft())
                .append(" getX = ").append(view.getX())
                .append(" getTranslationX = ").append(view.getTranslationX());
        Log.i(TAG, builder.toString());
    }

    private int dp2px(float dp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics()));
    }
}
