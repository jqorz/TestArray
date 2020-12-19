package com.jqorz.test.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 基类Activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static String IS_START_ANIM = "IS_START_ANIM";
    protected Context mContext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        setContentView(getLayoutResId());


        init();


    }


    public void reload(boolean anim) {
        Intent intent = getIntent();
        if (!anim) {
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.putExtra(BaseActivity.IS_START_ANIM, false);
        }
        finish();
        if (!anim) {
            overridePendingTransition(0, 0);
        }
        startActivity(intent);
    }


    protected abstract void init();

    protected abstract int getLayoutResId();


}
