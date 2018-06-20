package com.jqorz.test.servlet;

import android.view.View;
import android.widget.TextView;

import com.jqorz.test.R;
import com.jqorz.test.base.BaseActivity;
import com.jqorz.test.util.OkUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

public class MainActivity extends BaseActivity {
    TextView tv;

    @Override
    protected void init() {
        tv = findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Map<String, Object> map = new HashMap<>();
                        map.put("name", "孙红雷");
                        OkUtil.getAsyn("http://192.168.43.43:8080/test", new OkUtil.ResultCallback<String>() {

                            @Override
                            public void onError(Request request, final Exception e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv.setText(e.getMessage());
                                    }
                                });
                            }

                            @Override
                            public void onResponse(final String response) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv.setText(response);
                                    }
                                });
                            }
                        }, map);
                    }
                }).start();

            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_servlet;
    }
}
