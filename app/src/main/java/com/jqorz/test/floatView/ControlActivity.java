package com.jqorz.test.floatView;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.jqorz.test.R;
import com.jqorz.test.base.BaseActivity;
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
    private View tv_left;
    private View tv_switcher;
    private LinearLayout mFloatLayout;
    private boolean mHasShown;

    public static void start(Context context) {
        Intent starter = new Intent(context, ControlActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void init() {
        createFloatView();
        show();
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
        LayoutInflater inflater = LayoutInflater.from(getApplication());
        // 获取浮动窗口视图所在布局
        mFloatLayout = (LinearLayout) inflater.inflate(R.layout.layout_float_view, null);

        // 浮动窗口按钮

        tv_switcher = mFloatLayout.findViewById(R.id.tv_switcher);

        mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));


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

            new TestDialog().show(getSupportFragmentManager(), "123");
        }
    }


    public void show() {
        if (!mHasShown && mFloatLayout != null && mWindowManager != null) {
            mWindowManager.addView(mFloatLayout, wmParams);
        }
        mHasShown = true;
    }
}
