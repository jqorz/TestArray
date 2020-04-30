package com.jqorz.test.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jqorz.test.R;
import com.jqorz.test.base.BaseActivity;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2020/4/29
 */
public class AIDLActivity extends BaseActivity implements View.OnClickListener {
    private Button mBindBtn;
    private Button mUnbindBtn;
    private Button mSendRequestBtn;
    private TextView mResultTv;
    private IMyAidlInterface mIMyAidlInterface;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIMyAidlInterface = null;
        }
    };

    public static void start(Context context) {
        Intent starter = new Intent(context, AIDLActivity.class);
        context.startActivity(starter);
    }

    /**
     * 返回当前的进程名
     */
    public static String getCurrentProcessName() {
        FileInputStream in = null;
        try {
            String fn = "/proc/self/cmdline";
            in = new FileInputStream(fn);
            byte[] buffer = new byte[256];
            int len = 0;
            int b;
            while ((b = in.read()) > 0 && len < buffer.length) {
                buffer[len++] = (byte) b;
            }
            if (len > 0) {
                String s = new String(buffer, 0, len, "UTF-8");
                return s;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void init() {
        mBindBtn = findViewById(R.id.bindBtn);
        mBindBtn.setOnClickListener(this);
        mUnbindBtn = findViewById(R.id.unbindBtn);
        mUnbindBtn.setOnClickListener(this);
        mSendRequestBtn = findViewById(R.id.sendRequestBtn);
        mSendRequestBtn.setOnClickListener(this);
        mResultTv = findViewById(R.id.resultTv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bindBtn:
                bindService(this);
                mResultTv.append("绑定服务\n");
                break;
            case R.id.unbindBtn:
                unbindService(this);
                mResultTv.append("解除绑定\n");
                break;
            case R.id.sendRequestBtn:
                String request = request("char");
                mResultTv.append("当前进程");
                mResultTv.append(getCurrentProcessName());
                mResultTv.append("\n");
                if (!TextUtils.isEmpty(request)) {
                    mResultTv.append("接收到返回数据");
                    mResultTv.append(request);
                    mResultTv.append("\n");
                }
                break;
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_aidl;
    }

    private void bindService(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.jqorz.test2.start");
        intent.setPackage("com.jqorz.test");
        context.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unbindService(Context context) {
        if (mIMyAidlInterface == null) {
            return;
        }
        context.unbindService(mServiceConnection);
    }

    private String request(String data) {
        if (mIMyAidlInterface == null) {
            return null;
        }
        try {
            return mIMyAidlInterface.sendData(data);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

}
