package com.jqorz.test.view.click

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.jqorz.common.Logg

/**
 * @author  jqorz
 * @since  2021/8/21
 */
class TouchView constructor(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    init {
        isEnabled = false
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        return true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Logg.i("TouchView", event.action)
        when (event.action) {
            MotionEvent.ACTION_CANCEL -> {
                Logg.i("TouchView", "onTouchEvent ACTION_CANCEL")
            }
            MotionEvent.ACTION_OUTSIDE -> {
                Logg.i("TouchView", "onTouchEvent ACTION_OUTSIDE")
            }
            MotionEvent.ACTION_UP -> {
                Logg.i("TouchView", "onTouchEvent ACTION_UP")
            }

        }
        return super.onTouchEvent(event)
    }
}