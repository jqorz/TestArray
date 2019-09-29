package com.jqorz.test.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * 基类Activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static String IS_START_ANIM = "IS_START_ANIM";
    protected Context mContext;



    @Override
    final public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//禁止横屏

        mContext = this;

        setContentView(getLayoutResId());

        initCommonEvent();

        init();


    }

    /**
     * 处理一些通用的时间，比如返回按钮退出
     */
    private void initCommonEvent() {
        //统一左上角返回键的处理
//        View iv_Back = findViewById(R.id.iv_);
//        if (iv_Back != null) {
//            iv_Back.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finish();
//                }
//            });
//        }


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
