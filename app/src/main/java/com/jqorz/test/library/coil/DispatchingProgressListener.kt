package com.jqorz.test.library.coil

import android.os.Handler
import android.os.Looper
import com.androidnetworking.interfaces.DownloadProgressListener
import java.util.*

/**
 * @author  jqorz
 * @since  2021/2/25
 */
class DispatchingProgressListener(val url: String) : DownloadProgressListener {

    /**
     * Control how often the listener needs an update. 0% and 100% will always be dispatched.
     *
     * @return in percentage (0.2 = call [.onProgress] around every 0.2 percent of progress)
     */
    private val mGranularityPercentage = 1f

    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun onProgress(bytesRead: Long, contentLength: Long) {
//        Log.i("jqjq", String.format("加载 %s: %d/%d = %.2f%%%n", url, bytesRead, contentLength, (100f * bytesRead) / contentLength));
        val key = url
        val listener: DownloadProgressListener = LISTENERS[key]
                ?: return
        if (contentLength <= bytesRead) {
            forget(key)
        }
        if (needsDispatch(key, bytesRead, contentLength, mGranularityPercentage)) {
            handler.post { listener.onProgress(bytesRead, contentLength) }
        }
    }


    private fun needsDispatch(key: String, current: Long, total: Long, granularity: Float): Boolean {
        if (granularity == 0f || current == 0L || total == current) {
            return true
        }
        val percent = 100f * current / total
        val currentProgress = (percent / granularity).toLong()
        val lastProgress = PROGRESSES[key]
        return if (lastProgress == null || currentProgress != lastProgress) {
            PROGRESSES[key] = currentProgress
            true
        } else {
            false
        }
    }

    companion object {
        private val LISTENERS: MutableMap<String, DownloadProgressListener> = HashMap<String, DownloadProgressListener>()
        private val PROGRESSES: MutableMap<String, Long> = HashMap()

        fun forget(url: String) {
            LISTENERS.remove(url)
            PROGRESSES.remove(url)
        }

        fun expect(url: String, listener: DownloadProgressListener) {
            LISTENERS[url] = listener
        }
    }


}