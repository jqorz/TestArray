package com.jqorz.test.base;

import com.jqorz.test.MainEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * 使用EventBus的基类
 */
public abstract class BaseEventActivity extends BaseActivity {


    /**
     * 使用final可以让这个类的子类无法重写此方法，保证java的封装性
     */
    @Override
    final protected void init() {
        EventBus.getDefault().register(this);//初始化EventBus
        init0();
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void post(MainEvent event) {
        EventBus.getDefault().post(event);
    }

    protected abstract void init0();

    protected abstract int getLayoutResId();


}
