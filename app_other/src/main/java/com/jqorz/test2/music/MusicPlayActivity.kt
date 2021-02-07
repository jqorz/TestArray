package com.jqorz.test2.music

import android.content.Context
import android.content.Intent
import android.os.Looper
import android.util.Log
import android.view.View
import com.jqorz.common.AudioPlayManager
import com.jqorz.common.base.BaseActivity
import com.jqorz.test2.R

/**
 * @author  jqorz
 * @since  2021/2/4
 */
class MusicPlayActivity : BaseActivity() {
    private val TAG = "MusicPlayActivity"
    override fun init() {
        findViewById<View>(R.id.btn_play).setOnClickListener {
            playMusic();
        }
    }

    private fun playMusic() {

        val path = "android.resource://" + packageName + "/" + R.raw.test
        AudioPlayManager.getInstance(mContext!!.applicationContext).playSound(mContext, path, object : AudioPlayManager.OnAudioPlayListener {
            override fun onStart() {}
            override fun onDownLoadSuccess(path: String) {}
            override fun onAudioFocusChange(focusChange: Int) {
                Log.i("jqjq", "收到变化 主线程=" + (Looper.getMainLooper() == Looper.myLooper()))
            }

            override fun onCompletion() {}
            override fun onError() {}
            override fun onPrepared(duration: Int) {}
            override fun onReStart() {}
            override fun onRelease() {}
        })
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_music
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, MusicPlayActivity::class.java)
            context.startActivity(starter)
        }
    }
}