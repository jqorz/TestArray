package com.jqorz.common.contentprovider

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import android.util.Log
import com.jqorz.test2.contentprovider.ProviderConstant

/**
 * @author  jqorz
 * @since  2020/12/31
 */
class ProviderManger(private val contentResolver: ContentResolver) {
    private val TAG = "ProviderManger"
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
    fun testUpdate() {
        val contentResolver: ContentResolver = contentResolver
        val updateUri: Uri = ProviderConstant.CONTENT_URI
        val values = ContentValues()
        values.put(ProviderConstant.COLUMN_USER_ID, "白萝卜")
        values.put(ProviderConstant.COLUMN_USER_INFO, "age=13")
        contentResolver.update(updateUri, values, null, null)
    }

    //从内容提供者中删除数据
    fun testDelete() {
        val contentResolver: ContentResolver = contentResolver
        val deleteUri: Uri = ProviderConstant.CONTENT_URI
        contentResolver.delete(deleteUri, null, null)
    }

    //获取内容提供者中的数据
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