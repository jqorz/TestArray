package com.jqorz.test.floatView;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.jqorz.common.AudioPlayManager;
import com.jqorz.common.base.BaseActivity;
import com.jqorz.test.R;
import com.jqorz.test.util.ToolUtil;


/**
 * copyright datedu
 *
 * @author j1997
 * @since 2019/9/29
 */
public class ControlActivity extends BaseActivity implements View.OnClickListener {

    private WindowManager.LayoutParams wmParams;
    private WindowManager mWindowManager;
    private LinearLayout mFloatLayout;
    private boolean mHasShown;

    public static void start(Context context) {
        Intent starter = new Intent(context, ControlActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void init() {
        findViewById(R.id.btn_show).setOnClickListener(this);
        createFloatView();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_float_view;
    }

    private void createFloatView() {


        wmParams = new WindowManager.LayoutParams();
        // 获取WindowManagerImpl.CompatModeWrapper
        mWindowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        // 设置window type
        wmParams.type = ToolUtil.getFloatWindowType();

        // 设置图片格式，效果为背景透明
        wmParams.format = PixelFormat.RGBA_8888;
        // 设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        // 调整悬浮窗显示的停靠位置为左侧置顶

        wmParams.gravity = Gravity.TOP | Gravity.START;

        // 以屏幕左上角为原点，设置x、y初始值
        wmParams.x = ToolUtil.getScreenWidth() / 2;
        wmParams.y = (ToolUtil.getScreenHeight()) / 2;


        // 设置悬浮窗口长宽数据
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.windowAnimations = R.anim.fade_in;
        // 获取浮动窗口视图所在布局
        mFloatLayout = new ControlFloatView(mContext, null);

        // 浮动窗口按钮


        mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        View tv_switcher = mFloatLayout.findViewById(R.id.tv_switcher);
        tv_switcher.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_switcher) {
//            View root = LayoutInflater.from(mContext).inflate(R.layout.layout_popup, null);
//            PopupWindow popupWindow = new PopupWindow(root, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            popupWindow.setOutsideTouchable(true);
//            popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.popup_bg));
//            popupWindow.showAsDropDown(view);
//            new TestPopup(this).showPopupWindow(tv_switcher);

//            new TestDialog().show(getSupportFragmentManager(), "123");
            playMusic();
        } else if (view.getId() == R.id.btn_show) {
            show();
        }
    }

    private void playMusic() {
        String path = "android.resource://" + getPackageName() + "/" + R.raw.test;
        AudioPlayManager.getInstance(mContext).playSound(mContext, path, new AudioPlayManager.OnAudioPlayListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onDownLoadSuccess(String path) {

            }

            @Override
            public void onAudioFocusChange(int focusChange) {
            }

            @Override
            public void onCompletion() {

            }

            @Override
            public void onError() {

            }

            @Override
            public void onPrepared(int duration) {

            }

            @Override
            public void onReStart() {

            }

            @Override
            public void onRelease() {

            }
        });
    }

    public void show() {
        if (!mHasShown && mFloatLayout != null && mWindowManager != null) {
            mWindowManager.addView(mFloatLayout, wmParams);
        }
        mHasShown = true;
    }
}
