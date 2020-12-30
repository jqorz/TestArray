package com.jqorz.test2.aidl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jqorz.test2.R;

public class AIDLServerActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, AIDLServerActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}