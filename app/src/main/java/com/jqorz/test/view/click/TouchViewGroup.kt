package com.jqorz.test.view.click

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import com.jqorz.common.Logg

/**
 * @author  jqorz
 * @since  2021/8/21
 */
class TouchViewGroup constructor(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Logg.i("TouchViewGroup", event.action)
        when (event.action) {
            MotionEvent.ACTION_CANCEL -> {
                Logg.i("TouchViewGroup", "onTouchEvent ACTION_CANCEL")
            }
            MotionEvent.ACTION_OUTSIDE -> {
                Logg.i("TouchViewGroup", "onTouchEvent ACTION_OUTSIDE")
            }
            MotionEvent.ACTION_UP -> {
                Logg.i("TouchViewGroup", "onTouchEvent ACTION_UP")
            }

        }
        return super.onTouchEvent(event)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        requestLayout()
    }
}