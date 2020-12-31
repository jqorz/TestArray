package com.jqorz.common.wifi;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jqorz.common.R;
import com.jqorz.common.hfutwlan.WifiAdmin;
import com.jqorz.common.util.ToastUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


/**
 * @author jqorz
 * @since 2018/8/7
 */


public class WifiConnectActivity extends AppCompatActivity {

    private static final String TAG = WifiConnectActivity.class.getName();
    private TextView tv_info;
    private WifiAdmin mWifiAdmin;
    private List<ScanResult> mData = new ArrayList<>();
    private WifiAdapter mAdapter;
    private Handler mHandler;

    public static void start(Context context) {
        Intent starter = new Intent(context, WifiConnectActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_connect);
        mHandler = new MyHandler(this);
        initView();
    }

    private void initView() {
        tv_info = findViewById(R.id.tv_info);
        RecyclerView rv_wifi = findViewById(R.id.rv_wifi);
        rv_wifi.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new WifiAdapter(this, mData);
        rv_wifi.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ScanResult scanResult = mData.get(position);
                List<WifiConfiguration> configurationList = mWifiAdmin.getConfiguration();
                WifiConfiguration used = null;
                for (WifiConfiguration configuration : configurationList) {
                    if (TextUtils.equals(configuration.SSID, "\"" + scanResult.SSID + "\"")) {
                        used = configuration;
                        break;
                    }
                }
                if (used != null) {
//                    for (WifiConfiguration configuration : configurationList) {
//                        mWifiAdmin.disconnectWifi(configuration.networkId);
//                    }
                    if (used.SSID.startsWith("\"") && used.SSID.endsWith("\"")) {
                        used.SSID = used.SSID.substring(1, used.SSID.length() - 1);
                    }
                    boolean result = mWifiAdmin.addNetwork(used);
                    ToastUtil.showToast(WifiConnectActivity.this, "连接" + result);
                } else {
                    ToastUtil.showToast(WifiConnectActivity.this, "没有此wifi的密码");
                }
            }
        });

        mWifiAdmin = new WifiAdmin(this);
        mWifiAdmin.startScan();
        refreshData();
        mHandler.sendEmptyMessage(0);

    }

    private void refreshData() {
        mData.clear();
        mData.addAll(mWifiAdmin.getWifiList());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    public Handler getHandler() {
        return mHandler;
    }

    private interface OnItemClickListener {
        void onItemClick(int position);
    }

    private static class MyHandler extends Handler {
        private WeakReference<WifiConnectActivity> mOuter;

        public MyHandler(WifiConnectActivity activity) {
            mOuter = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final WifiConnectActivity outer = mOuter.get();
            if (outer != null) {
                outer.refreshData();
                outer.getHandler().sendEmptyMessageDelayed(0, 1000);
            }
        }
    }

    private static class WifiViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;

        public WifiViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }

    private static class WifiAdapter extends RecyclerView.Adapter<WifiViewHolder> {
        private Context mContext;
        private List<ScanResult> mData;
        private OnItemClickListener onItemClickListener;

        public WifiAdapter(Context mContext, List<ScanResult> mData) {
            this.mContext = mContext;
            this.mData = mData;
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        @NonNull
        @Override
        public WifiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_wifi, viewGroup, false);
            return new WifiViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull WifiViewHolder wifiViewHolder, final int position) {
            wifiViewHolder.tv_name.setText(mData.get(position).SSID);
            wifiViewHolder.tv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

}
