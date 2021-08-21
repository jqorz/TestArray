package com.jqorz.test.click

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * @author  jqorz
 * @since  2021/8/21
 */
class TouchView constructor(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
//        return false
    }
}