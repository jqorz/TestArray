package com.jqorz.test.io.file_concat

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import com.jqorz.common.base.BaseActivity
import com.jqorz.test.R
import com.jqorz.test.util.coroutine.Coroutine
import com.jqorz.test.util.permission.PermissionUtils
import com.yanzhenjie.permission.runtime.Permission
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.RandomAccessFile

/**
 * @author  jqorz
 * @since  2021/1/9
 */
class FileConcatActivity : BaseActivity() {
    override fun init() {
        findViewById<View>(R.id.btn_start).setOnClickListener {
            com.jqorz.test.util.permission.PermissionUtils.readyPermission(this, {
                Coroutine.async {
                    val sourceDir = "/sdcard/Download/test"
                    val srcFilePath = "/sdcard/Download/test.0"
                    merge(sourceDir, srcFilePath)
                }
            }, Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE)

        }
    }

    override fun getLayoutResId() = R.layout.activity_file_concat

    suspend fun merge(sourceDir: String, srcFilePath: String): Boolean {
        return withContext(Dispatchers.IO) {
            val sourceFileList = File(sourceDir).listFiles().filter { it.name.toIntOrNull() != null }.sortedBy { it.name.toInt() }

            var raf: RandomAccessFile? = null
            try {
                raf = RandomAccessFile(srcFilePath, "rw")
                sourceFileList.forEachIndexed { index, file ->
                    val reader = RandomAccessFile(file, "r")
                    val b = ByteArray(1024)
                    var n: Int
                    //先读后写
                    while (reader.read(b).also { n = it } != -1) { //读
                        raf.write(b, 0, n) //写
                    }
                    Log.i(TAG, "合并第${index + 1}/${sourceFileList.size}个文件 ${file.name}")
                }
                Log.i(TAG, "完成")
                return@withContext true
            } catch (e: Exception) {
                Log.e(TAG, "异常 ${e.localizedMessage}")
            } finally {
                raf?.close()
            }
            return@withContext false
        }
    }

    companion object {
        private const val TAG = "FileConcatActivity"

        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, FileConcatActivity::class.java)
            context.startActivity(starter)
        }
    }

}