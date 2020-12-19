package com.jqorz.test.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 基类Activity
 */
abstract class BaseActivity : AppCompatActivity() {

    protected var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(getLayoutResId())
        init()
    }

    fun reload(anim: Boolean) {
        val intent = intent
        if (!anim) {
            overridePendingTransition(0, 0)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            intent.putExtra(IS_START_ANIM, false)
        }
        finish()
        if (!anim) {
            overridePendingTransition(0, 0)
        }
        startActivity(intent)
    }

    protected abstract fun init()

    protected abstract fun getLayoutResId(): Int

    companion object {
        private const val IS_START_ANIM = "IS_START_ANIM"
    }
}