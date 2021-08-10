package com.jqorz.common.fundot

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import android.util.Log

/**
 * @author  jqorz
 * @since  2020/12/31
 */
class ProviderManger(private val contentResolver: ContentResolver) {


    private val TAG = "ProviderManger"
    fun testInsert(packageName: String, show: Int): String {
        val contentResolver: ContentResolver = contentResolver
        val insertUri: Uri = ProviderConstant.CONTENT_URI
        val values = ContentValues()
        values.put(ProviderConstant.COLUMN_PACKAGE_NAME, packageName)
        values.put(ProviderConstant.COLUMN_ALLOW_SHOW, show)
        Log.i(TAG, "testInsert1 = $insertUri")
        val uri = contentResolver.insert(insertUri, values)
        Log.i(TAG, "testInsert2 = " + uri?.toString())
        return uri?.toString() ?: "空数据"
    }

    //更新内容提供者中的数据
    fun testUpdate(packageName: String, show: Int) {
        val contentResolver: ContentResolver = contentResolver
        var updateUri: Uri = ProviderConstant.CONTENT_URI
        val values = ContentValues()
        values.put(ProviderConstant.COLUMN_PACKAGE_NAME, packageName)
        values.put(ProviderConstant.COLUMN_ALLOW_SHOW, show)
        Log.i(TAG, "testUpdate1 = $updateUri")
        val i = contentResolver.update(updateUri, values, "${ProviderConstant.COLUMN_PACKAGE_NAME} = ? ", arrayOf(packageName))
        Log.i(TAG, "testUpdate2 = $i")

    }

    //从内容提供者中删除数据
    fun testDelete(packageName: String) {
        val contentResolver: ContentResolver = contentResolver
        val deleteUri: Uri = ProviderConstant.CONTENT_URI
        Log.i(TAG, "testDelete1 = $deleteUri")
        val i = contentResolver.delete(deleteUri, "${ProviderConstant.COLUMN_PACKAGE_NAME} = ? ", arrayOf(packageName))
        Log.i(TAG, "testDelete2 = $i")
    }

    fun testDeleteAll() {
        val contentResolver: ContentResolver = contentResolver
        val deleteUri: Uri = ProviderConstant.CONTENT_URI
        Log.i(TAG, "testDelete1 = $deleteUri")
        val i = contentResolver.delete(deleteUri, null, null)
        Log.i(TAG, "testDelete2 = $i")
    }

    fun testDeleteAllAndCreate(packageName: String, show: Int) {
        val contentResolver: ContentResolver = contentResolver
        val selectUri: Uri = ProviderConstant.CONTENT_URI
        val i = contentResolver.delete(selectUri, null, null) //先删除再添加
        Log.i(TAG, "testDeleteAllAndCreate1 = $i")
        val values = ContentValues()
        values.put(ProviderConstant.COLUMN_PACKAGE_NAME, packageName)
        values.put(ProviderConstant.COLUMN_ALLOW_SHOW, show)
        contentResolver.insert(selectUri, values)
        Log.i(TAG, "testDeleteAllAndCreate2 = $i")
    }

    //获取内容提供者中的数据
    fun testFind(): String {
        val contentResolver: ContentResolver = contentResolver
        val selectUri: Uri = ProviderConstant.CONTENT_URI
        val cursor = contentResolver.query(selectUri, null, null, null, null)
        val builder = StringBuilder()
        while (cursor?.moveToNext() == true) {
            val id: String = cursor.getString(cursor.getColumnIndex(ProviderConstant.COLUMN_PACKAGE_NAME))
            val info: String = cursor.getString(cursor.getColumnIndex(ProviderConstant.COLUMN_ALLOW_SHOW))
            Log.i(TAG, "查找数据 id=$id , info=$info ")
            builder.append("id=$id , info=$info \n")
        }
        cursor?.close()
        return builder.toString()
    }
}