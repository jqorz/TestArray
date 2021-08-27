package com.jqorz.test.view.click;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.jqorz.test.R;
import com.jqorz.test.util.ToastUtil;

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
        findViewById(R.id.parent).setOnClickListener(v -> {
            Log.i(TAG, "click parent");
            ToastUtil.showToast(ClickActivity.this, "parent");
        });
        final TouchView child = findViewById(R.id.child);
        child.setOnClickListener(v -> {
            Log.i("jqjq", "click child");
        });
//        child.setOnTouchListener((v,event) ->{
//            Log.i("jqjq", "touchListener");
//            return true;
//        });
        findViewById(R.id.btn_1).setOnClickListener(v -> {
            showText(child);
            child.setTranslationX(dp2px(20));
            showText(child);
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
