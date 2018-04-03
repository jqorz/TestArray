package com.jqorz.test.videoServer;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.jqorz.test.R;

import java.io.IOException;

public class VideoActivity extends AppCompatActivity {
    private static final String DEFAULT_FILE_PATH = Environment.getExternalStorageDirectory() + "/movie.mp4";
    private static final int VIDEO_WIDTH = 320;
    private static final int VIDEO_HEIGHT = 240;

    private VideoServer mVideoServer;
    private TextView mTipsTextView;

    public static String getLocalIpStr(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = null;
        if (wifiManager != null) {
            wifiInfo = wifiManager.getConnectionInfo();
            return intToIpAddr(wifiInfo.getIpAddress());
        }
        return "null";
    }

    private static String intToIpAddr(int ip) {
        return (ip & 0xff) + "." + ((ip >> 8) & 0xff) + "." + ((ip >> 16) & 0xff) + "." + ((ip >> 24) & 0xff);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mTipsTextView = findViewById(R.id.TipsTextView);
        mVideoServer = new VideoServer(DEFAULT_FILE_PATH, VIDEO_WIDTH, VIDEO_HEIGHT, VideoServer.DEFAULT_SERVER_PORT);
        mTipsTextView.setText("请在远程浏览器中输入:\n\n" + getLocalIpStr(this) + ":" + VideoServer.DEFAULT_SERVER_PORT);

    }

    public void onClickBtn(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mVideoServer.start();
                } catch (final IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTipsTextView.setText(e.getMessage());
                        }
                    });
                }
            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        if (mVideoServer.isAlive()) {
            mVideoServer.stop();
        }
        super.onDestroy();
    }

}
