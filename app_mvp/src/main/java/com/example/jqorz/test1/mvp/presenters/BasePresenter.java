package com.example.jqorz.test1.mvp.presenters;



import com.example.jqorz.test1.mvp.MvpView;

import java.lang.ref.WeakReference;

/**
 * 避免内存泄漏，使用弱引用，并在退出Activity时销毁持有的Activity
 *
 * @param <V>
 */
public abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private WeakReference<V> reference;


    @Override
    public void attachView(V view) {
        if (view == null)
            throw new NullPointerException("view can not be null when in attachview() in BasePresenter");
        else {
            if (reference == null)
                reference = new WeakReference<>(view);//将View置为弱引用，当view被销毁回收时，依赖于view的对象（即Presenter）也会被回收，而不会造成内存泄漏

        }

    }

    @Override
    public void detachView() {

        if (reference != null) {
            reference.clear();
            reference = null;
        }
    }

    public V getMvpView() {
        if (isAttach())
            return reference.get();
        else
            throw new NullPointerException("have you ever called attachView() in BasePresenter");
    }

    public boolean isAttach() {
        return reference != null && reference.get() != null;
    }
}