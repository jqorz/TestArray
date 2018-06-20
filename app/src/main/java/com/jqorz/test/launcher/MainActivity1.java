package com.jqorz.test.launcher;

import android.content.Intent;
import android.view.View;

import com.jqorz.test.R;
import com.jqorz.test.base.BaseActivity;

public class MainActivity1 extends BaseActivity {


    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    public void onClickBtn(View view) {
        startActivity(new Intent(this, MainActivity2.class));
        finish();
    }
}
