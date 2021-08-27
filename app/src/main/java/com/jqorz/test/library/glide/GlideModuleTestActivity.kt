package com.jqorz.test.library.glide

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.jqorz.test.R
import com.jqorz.common.base.BaseActivity

/**
 * @author  jqorz
 * @since  2020/12/19
 */
class GlideModuleTestActivity : BaseActivity() {
    override fun init() {
        val image = findViewById<ImageView>(R.id.image)
        Glide.with(this).load(R.mipmap.ic_wifi_on).into(image)
    }

    override fun getLayoutResId() = R.layout.activity_glide
}