package com.jqorz.test.content_provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.jqorz.test2.contentprovider.ProviderConstant


class UserInfoProvider : ContentProvider() {
    companion object {
        //常量UriMatcher.NO_MATCH表示不匹配任何路径的返回码
        private val MATCHER = UriMatcher(UriMatcher.NO_MATCH)
        private const val USER_INFO_CODE = 101

        init {
            MATCHER.addURI(ProviderConstant.AUTHORITIES, ProviderConstant.PATH_USER_INFO, USER_INFO_CODE)
        }
    }

    private lateinit var dbOpenHelper: DBOpenHelper

    override fun onCreate(): Boolean {
        dbOpenHelper = DBOpenHelper(this.context)
        return false
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val db = dbOpenHelper.writableDatabase
        return when (MATCHER.match(uri)) {
            USER_INFO_CODE -> {
                db.delete(ProviderConstant.TABLE_NAME, selection, selectionArgs)
            }
            else -> throw IllegalArgumentException("Unknown Uri:$uri")
        }
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri {
        val db = dbOpenHelper.writableDatabase
        return when (MATCHER.match(uri)) {
            USER_INFO_CODE -> {
                val rowid = db.insert(ProviderConstant.TABLE_NAME, null, values)
                ContentUris.withAppendedId(uri, rowid) //得到代表新增记录的Uri
            }
            else -> throw IllegalArgumentException("Unknown Uri:$uri")
        }
    }


    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?,
                       sortOrder: String?): Cursor {
        val db = dbOpenHelper.readableDatabase
        return when (MATCHER.match(uri)) {
            USER_INFO_CODE -> {
                db.query(ProviderConstant.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder)
            }
            else -> throw IllegalArgumentException("Unknown Uri:$uri")
        }
    }


    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        val db = dbOpenHelper.writableDatabase
        return when (MATCHER.match(uri)) {
            USER_INFO_CODE -> {
                db.update(ProviderConstant.TABLE_NAME, values, selection, selectionArgs)
            }
            else -> throw IllegalArgumentException("Unknown Uri:$uri")
        }
    }

    override fun getType(uri: Uri): String {
        return when (MATCHER.match(uri)) {
            USER_INFO_CODE -> "vnd.android.cursor.dir/user"
            else -> throw java.lang.IllegalArgumentException("Unknown Uri:$uri")
        }
    }
}