package com.jqorz.test.servlet;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jqorz.test.R;
import com.jqorz.test.base.BaseActivity;
import com.jqorz.test.util.OkUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

public class ServletActivity extends BaseActivity {
    private TextView tv_Result;

    @Override
    protected void init() {
        tv_Result = findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Map<String, String> params = new HashMap<>();
                        params.put("name", "jq");
                        params.put("password", "123456");
                        params.put("message", "地瓜地瓜，我是土豆");

                        OkUtil.postAsynWithUTF("http://192.168.40.50:65432/login", new OkUtil.ResultCallback<String>() {
                            @Override
                            public void onError(Request request, final Exception e) {
                                Log.e("error", e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_Result.setText(e.getMessage());
                                    }
                                });
                            }

                            @Override
                            public void onResponse(final String response) {
                                Log.e("response", response);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_Result.setText(response);
                                    }
                                });
                            }
                        }, params);
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
