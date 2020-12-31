package com.jqorz.common.popup

import android.content.Context
import android.view.Gravity
import android.view.View
import com.jqorz.common.R
import razerdp.basepopup.BasePopupWindow

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2019/11/1
 */
class TestPopup(context: Context?) : BasePopupWindow(context) {
    override fun onCreateContentView(): View {
        return createPopupById(R.layout.layout_popup)
    }

    init {
        setPopupGravity(GravityMode.RELATIVE_TO_ANCHOR, Gravity.END or Gravity.CENTER_VERTICAL)
    }
}