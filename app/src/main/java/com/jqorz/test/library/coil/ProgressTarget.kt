package com.jqorz.test.library.coil

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import coil.target.ImageViewTarget
import com.androidnetworking.interfaces.DownloadProgressListener

open class ProgressTarget(private val textView: TextView, override val view: ImageView, val url: String, val serial: String
) : ImageViewTarget(view), DownloadProgressListener {

    override fun onClear() {
        DispatchingProgressListener.forget(url)
    }

    @SuppressLint("SetTextI18n")
    override fun onStart(placeholder: Drawable?) {
        super.onStart(placeholder)
        DispatchingProgressListener.expect(url, this)
        textView.text = "load"
        Log.i("jqjq1", "serial=$serial onStart")
    }

    @SuppressLint("SetTextI18n")
    override fun onSuccess(result: Drawable) {
        super.onSuccess(result)
        DispatchingProgressListener.forget(url)
        textView.text = "ok"
        Log.i("jqjq1", "serial=$serial onSuccess")
    }

    @SuppressLint("SetTextI18n")
    override fun onError(error: Drawable?) {
        super.onError(error)
        DispatchingProgressListener.forget(url)
        textView.text = "error"
        Log.i("jqjq1", "serial=$serial onError")
    }

    override fun equals(other: Any?): Boolean {
        return (this === other) || (other is ProgressTarget && view == other.view)
    }

    override fun hashCode() = view.hashCode()

    override fun toString() = "ProgressTarget(view=$view)"

    override fun onProgress(bytesRead: Long, expectedLength: Long) {
        val percent = (100f * bytesRead / expectedLength).toInt()
        Log.i("jqjq2", "serial=$serial percent=$percent")
        textView.text = "$percent"
    }

}
