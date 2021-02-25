package com.jqorz.test.coil

import android.content.Context
import android.util.Log
import coil.EventListener
import coil.ImageLoader
import coil.request.ImageRequest
import com.androidnetworking.internal.ResponseProgressBody
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.util.Util
import com.jqorz.test.util.AppConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Response
import okio.ByteString.Companion.encodeUtf8
import java.io.File
import java.security.MessageDigest

/**
 * @author  jqorz
 * @since  2021/2/25
 */
object CustomManager {

    fun getCoilCacheKey(url: String) = url.encodeUtf8().md5().hex() + ".1"

    fun getGlideSafeKey(url: String): String {
        try {
            val messageDigest = MessageDigest.getInstance("SHA-256")
            GlideUrl(url).updateDiskCacheKey(messageDigest)
            val safeKey = Util.sha256BytesToHex(messageDigest.digest())
            return "$safeKey.0"
        } catch (e: Exception) {
        }
        return ""
    }

    fun getCachePath(): File {
        return AppConfig.getApp().getExternalFilesDir("pics")!!.apply { mkdirs() }
    }

    fun getGlideFilePath(onlineUrl: String) = getCachePath().path + File.separator + getGlideSafeKey(onlineUrl)

    fun getCoilFilePath(onlineUrl: String) = getCachePath().path + File.separator + getCoilCacheKey(onlineUrl)

    fun convertGlide2Coil() {

    }

    private fun createDefaultCache(): Cache {
        val cacheDirectory = CustomManager.getCachePath()
        val cacheSize = 20 * 1024 * 1024 * 1024L
        return Cache(cacheDirectory, cacheSize)
    }

    fun createProgressLoader(context: Context): ImageLoader {
        return ImageLoader.Builder(context.applicationContext)
                .crossfade(true)
                .okHttpClient {
                    OkHttpClient.Builder()
                            .cache(createDefaultCache())
                            .addInterceptor {
                                val originalResponse: Response = it.proceed(it.request())
                                originalResponse.newBuilder()
                                        .body(ResponseProgressBody(originalResponse.body,
                                                DispatchingProgressListener(it.request().url.toString())))
                                        .build()
                            }
                            .build()
                }
                .eventListener(object : EventListener {
                    override fun onError(request: ImageRequest, throwable: Throwable) {
                        Log.e("jqjq", "error $throwable")
                        super.onError(request, throwable)
                    }
                })
                .build()
    }

}