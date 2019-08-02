package com.jqorz.test.wifi;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jqorz.test.R;


/**
 * @author jqorz
 * @since 2018/8/7
 */


public class WifiInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = WifiInfoActivity.class.getName();
    private TextView textView1, textView2;
    private WifiManager mWifiManager;
    private ConnectivityManager mConnectivityManager;

    public static void start(Context context) {
        Intent starter = new Intent(context, WifiInfoActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        initView();
    }


    private void initView() {
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        mWifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mConnectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                showBtn1();
                break;
            case R.id.btn2:
                showBtn2();
                break;

        }
    }


    public void showBtn1() {
        try {
            //WiFi是否连接
            NetworkInfo wifiInfo = mConnectivityManager.getActiveNetworkInfo();
            //手机网络是否连接
            if (wifiInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                textView1.setText(String.format("WIFI连接信息：名称：%s 信息：%s", wifiInfo.getExtraInfo(), wifiInfo.toString()));
            } else if (wifiInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                textView1.setText(String.format("移动网络信息：%s%s", textView1.getText(), wifiInfo.getExtraInfo()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void showBtn2() {
        /*
     getBSSID() 获取BSSID,在手机WIFI中，就是MAC地址
     getSSID() 获取SSID
     getDetailedStateOf() 获取客户端的连通性
     getHiddenSSID() 获得SSID是否被隐藏
     getIpAddress() 获取IP地址
     getLinkSpeed() 获得连接的速度
     getMacAddress() 获得Mac地址
     getRssi() 获得802.11n网络的信号
     getSupplicanState() 返回具体客户端状态的信息
     */
        WifiInfo mWifiInfo = mWifiManager.getConnectionInfo();
        Log.i(TAG, mWifiInfo.getBSSID());//8c:a6:df:07:7e:90
        Log.i(TAG, mWifiInfo.getSSID()); //"LIAIDI"  名称
        Log.i(TAG, mWifiInfo.getIpAddress() + "M"); //1694607552M
        Log.i(TAG, mWifiInfo.getLinkSpeed() + "S");  //65S
        Log.i(TAG, mWifiInfo.getIpAddress() + "ADD");
        Log.i(TAG, mWifiInfo.getRssi() + "802.11n网络的信号");
        textView2.setText(String.format("WIFI信息：%s", mWifiInfo.toString()));
    }


}
