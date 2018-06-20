package com.jqorz.test.anim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jqorz.test.R;

public class AnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);


    }

    public void onClickBtn(View view) {
        new CheerViewToast(this).show();
    }
}
