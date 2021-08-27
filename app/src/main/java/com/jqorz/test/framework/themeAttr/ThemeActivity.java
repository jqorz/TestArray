package com.jqorz.test.framework.themeAttr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;

import com.jqorz.test.R;
import com.jqorz.common.base.BaseActivity;

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2020/1/17
 */
public class ThemeActivity extends BaseActivity implements View.OnClickListener {


    public static void start(Context context) {
        Intent starter = new Intent(context, ThemeActivity.class);
        context.startActivity(starter);
    }

    private int getAttrSize(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.ViewHeight, typedValue, true);
        return typedValue.data;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Height_500);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        View btn_1 = findViewById(R.id.btn_1);
        View btn_2 = findViewById(R.id.btn_2);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_theme;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_1) {
            setTheme(R.style.AppTheme_Height_100);
        } else if (v.getId() == R.id.btn_2) {
            setTheme(R.style.AppTheme_Height_500);
        }
        recreate();
    }

}
