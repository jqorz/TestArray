package com.jqorz.test2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;


import com.jqorz.test.aidl.IMyAidlInterface;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2020/4/29
 */
public class AIDLServer extends Service {
    private static final String TAG = "AIDLReceiver";
    IMyAidlInterface.Stub mStub = new IMyAidlInterface.Stub() {

        @Override
        public String sendData(String aString) throws RemoteException {

            try {
                return aString + "数据已接收 111 进程名 = " + getCurrentProcessName();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return " error";
        }
    };

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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }
}
