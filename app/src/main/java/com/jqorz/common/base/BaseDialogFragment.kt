package com.jqorz.common.base

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import androidx.fragment.app.DialogFragment
import com.jqorz.common.R

abstract class BaseDialogFragment(val layout: Int = 0,
                                  @StyleRes val style: Int = R.style.DialogMircoTheme,
                                  val fullScreen: Boolean = false,
                                  val cancel: Boolean = true) : DialogFragment() {
    protected lateinit var mRootView: View
    protected lateinit var mContext: Context
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!this::mRootView.isInitialized) {
            val id = if (layout == 0) getLayoutId() else layout
            if (id == 0) throw IllegalAccessException("layoutId 不能为空")

            mRootView = inflater.inflate(id, container, false)
            mContext = mRootView.context
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = cancel
        initView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, style)
    }

    override fun onStart() {
        super.onStart()
        if (fullScreen) {
            val dm = DisplayMetrics()
            activity?.windowManager?.defaultDisplay?.getMetrics(dm)
            dialog?.window?.setLayout(dm.widthPixels, dm.heightPixels)
        }
    }

    fun <T : View> findViewById(@IdRes id: Int): T {
        return mRootView.findViewById(id)
    }

    protected open fun getLayoutId(): Int = 0
    protected abstract fun initView()
}