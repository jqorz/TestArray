package com.jqorz.test.hfutwlan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.jqorz.test.R;
import com.jqorz.test.base.BaseActivity;

public class HfutWlanMainActivity extends BaseActivity implements OnClickListener, CompoundButton.OnCheckedChangeListener {
    private EditText password_box, account_box;
    private CheckBox ckbox1;
    private Button mybut1, mybut2;
    private ToggleButton togglebut;
    private ImageView wifiimage;
    private CompoundButton.OnCheckedChangeListener listener;
    private WifiAdmin mWifiAdmin;
    private TextView wifiTip;
    private SharedPreferences data;
    private Handler mHandler = new Handler();
    private boolean pass = false;
    //退出提示
    private int mBackKeyPressedTimes = 0;

    @Override
    protected void init() {
        mWifiAdmin = new WifiAdmin(HfutWlanMainActivity.this);

        initView();
        initEvent();
        initial_show();
        getLocalData();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_htutwlan_main;
    }

    private void initView() {
        account_box = findViewById(R.id.account_box);
        password_box = findViewById(R.id.password_box);
        mybut1 = findViewById(R.id.mybut1);
        mybut2 = findViewById(R.id.mybut2);
        ckbox1 = findViewById(R.id.ckbox1);
        togglebut = findViewById(R.id.togglebut);
        wifiimage = findViewById(R.id.wifiimage);
        wifiTip = findViewById(R.id.wifiTip);
    }

    public void initEvent() {

        mybut1.setOnClickListener(this);
        mybut2.setOnClickListener(this);
        ckbox1.setOnCheckedChangeListener(this);

    }

    public void getLocalData() {//读取储存的账号与密码
        data = getSharedPreferences("data", MODE_PRIVATE);
        String getaccountdata = data.getString("account", "");
        String getpassworddata = data.getString("password", "");
        if (getaccountdata != null && getpassworddata != null) {
            account_box.setText(getaccountdata);
            password_box.setText(getpassworddata);
        } else {
            account_box.setText("");
            password_box.setText("");
        }
    }

    //启动软件时的文字与图标状态
    public void initial_show() {
        int wifiState = mWifiAdmin.checkState();
        if (wifiState == 1) {
            wifiTip.setText("wifi已关闭");
            togglebut.setText("点击开启wifi");
            wifiimage.setBackgroundResource(R.mipmap.ic_wifi_off);
        } else if (wifiState == 3) {
            wifiTip.setText("wifi已开启");
            togglebut.setText("点击关闭wifi");
            wifiimage.setBackgroundResource(R.mipmap.ic_wifi_on);
        }
    }

    //wifi状态更新
    public void state() {
        int wifiState = mWifiAdmin.checkState();
        if (wifiState == 3) {
            wifiTip.setText("wifi已关闭");
            togglebut.setText("点击开启wifi");
            wifiimage.setBackgroundResource(R.mipmap.ic_wifi_off);
        } else if (wifiState == 1) {
            wifiTip.setText("wifi已开启");
            togglebut.setText("点击关闭wifi");
            wifiimage.setBackgroundResource(R.mipmap.ic_wifi_on);
        }
    }

    //wifi方法声明
    public void start() {
        mWifiAdmin.openWifi();
    }

    public void end() {
        mWifiAdmin.closeWifi();
    }

    public void startScan() {
        mWifiAdmin.startScan();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.togglebut://通过点击togglebutton更改wifi开关和更换文字与wifi状态图标
                int wifiState = mWifiAdmin.checkState();

                if (wifiState == 3) {
                    end();
                    this.state();
                    Toast.makeText(HfutWlanMainActivity.this, "关闭中", Toast.LENGTH_SHORT).show();
                } else if (wifiState == 1) {
                    start();
                    this.state();
                    Toast.makeText(HfutWlanMainActivity.this, "打开中", Toast.LENGTH_SHORT).show();
                    startScan();
                }
                return;

            case R.id.mybut1://记录用户名于xml文件中
                final ProgressDialog prodialog = new ProgressDialog(this);
                prodialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                prodialog.setMessage("登录中");
                prodialog.setCanceledOnTouchOutside(false);
                prodialog.show();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);

                            mHandler.post(new Runnable() {

                                @Override
                                public void run() {
                                    prodialog.cancel();

                                }


                            });

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();


                if (pass) {

                    Editor editor = data.edit();
                    String getaccount = account_box.getText().toString().trim();
                    String getpassword = password_box.getText().toString().trim();
                    editor.putString("account", getaccount);
                    editor.putString("password", getpassword);
                    editor.commit();
                }
                return;

            case R.id.mybut2:
                Intent intent = new Intent(this, Surfing.class);
                startActivity(intent);
                pass = true;

                //	setIpWithTfiStaticIp();
        }
    }

    public void onBackPressed() {
        if (mBackKeyPressedTimes == 0) {
            Toast.makeText(this, "你真要退出吗？<T_T>", Toast.LENGTH_SHORT).show();
            mBackKeyPressedTimes = 1;
            new Thread() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        mBackKeyPressedTimes = 0;
                    }
                }
            }.start();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (ckbox1.isChecked()) {//设置为明文显示
            password_box.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {//设置为密文显示
            password_box.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

}

