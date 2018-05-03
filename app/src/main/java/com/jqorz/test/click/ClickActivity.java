package com.jqorz.test.click;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jqorz.test.R;
import com.jqorz.test.ToastUtil;

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2018/4/4
 */
public class ClickActivity extends AppCompatActivity {
    private String TAG = "ClickActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);
        Log.i("jq", Build.MODEL);
    }

    public void onClickParent(View v) {
        Log.i(TAG, "click parent");
        ToastUtil.showToast(this, "parent");
    }

    public void onClickChild(View v) {
        Log.i(TAG, "click child");
        ToastUtil.showToast(this, "child");
    }
}
