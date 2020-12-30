package com.jqorz.test2.contentprovider

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jqorz.test2.R


/**
 * @author  jqorz
 * @since  2020/12/29
 */
class ContentProviderActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "ContentProviderActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.btn_query).setOnClickListener(this)
        findViewById<View>(R.id.btn_update).setOnClickListener(this)
        findViewById<View>(R.id.btn_insert).setOnClickListener(this)
        findViewById<View>(R.id.btn_delete).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_query -> {
                testFind()
            }
            R.id.btn_update -> {
                testUpdate()
            }
            R.id.btn_insert -> {
                testInsert()
            }
            R.id.btn_delete -> {
                testDelete()
            }
        }
    }

    @Throws(Throwable::class)
    fun testInsert() {
        val contentResolver: ContentResolver = contentResolver
        val insertUri: Uri = ProviderConstant.CONTENT_URI
        val values = ContentValues()
        values.put(ProviderConstant.COLUMN_USER_ID, "白萝卜")
        values.put(ProviderConstant.COLUMN_USER_INFO, "age=12")
        val uri = contentResolver.insert(insertUri, values)
        Log.i(TAG, "testInsert = " + uri?.toString())
    }

    //更新内容提供者中的数据   
    @Throws(Throwable::class)
    fun testUpdate() {
        val contentResolver: ContentResolver = contentResolver
        val updateUri: Uri = ProviderConstant.CONTENT_URI
        val values = ContentValues()
        values.put(ProviderConstant.COLUMN_USER_ID, "白萝卜")
        values.put(ProviderConstant.COLUMN_USER_INFO, "age=13")
        contentResolver.update(updateUri, values, null, null)
    }

    //从内容提供者中删除数据   
    @Throws(Throwable::class)
    fun testDelete() {
        val contentResolver: ContentResolver = contentResolver
        val deleteUri: Uri = ProviderConstant.CONTENT_URI
        contentResolver.delete(deleteUri, null, null)
    }

    //获取内容提供者中的数据   
    @Throws(Throwable::class)
    fun testFind() {
        val contentResolver: ContentResolver = contentResolver
        val selectUri: Uri = ProviderConstant.CONTENT_URI
        val cursor = contentResolver.query(selectUri, null, null, null, null)
        while (cursor?.moveToNext() == true) {
            val id: Int = cursor.getInt(cursor.getColumnIndex(ProviderConstant.COLUMN_USER_ID))
            val info: String = cursor.getString(cursor.getColumnIndex(ProviderConstant.COLUMN_USER_INFO))
            Log.i(TAG, "id=$id , info=$info ")
        }
        cursor?.close()
    }


}