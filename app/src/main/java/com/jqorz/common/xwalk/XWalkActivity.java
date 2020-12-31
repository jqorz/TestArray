package com.jqorz.common.xwalk;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.xwalk.core.XWalkActivityDelegate;
import org.xwalk.core.XWalkView;

/**
 * @author jqorz
 * @since 2018/7/13
 */
public class XWalkActivity extends AppCompatActivity {
    Runnable cancelCommand = new Runnable() {
        @Override
        public void run() {
            finish();
        }
    };
    private XWalkView walkView;
    Runnable completeCommand = new Runnable() {
        @Override
        public void run() {
            onXWalkReady();
        }

        private void onXWalkReady() {
            walkView.loadUrl("http://csdn.net");
        }
    };
    private XWalkActivityDelegate mActivityDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityDelegate = new XWalkActivityDelegate(this, cancelCommand, completeCommand);

        LinearLayout linearLayout = new LinearLayout(this);

        walkView = new XWalkView(this);

        linearLayout.addView(walkView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(linearLayout);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mActivityDelegate.onResume();//这个必须加
    }
}