package com.jqorz.test.content_provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.jqorz.common.contentprovider.ProviderConstant


class UserInfoProvider : ContentProvider() {
    companion object {
        //常量UriMatcher.NO_MATCH表示不匹配任何路径的返回码
        private val MATCHER = UriMatcher(UriMatcher.NO_MATCH)

        private const val CODE_USER_INFO_DIR = 101
        private const val CODE_USER_INFO_ITEM = 102

        init {
            MATCHER.addURI(ProviderConstant.AUTHORITIES, ProviderConstant.PATH_USER_INFO, CODE_USER_INFO_DIR)
            //#号为通配符
            MATCHER.addURI(ProviderConstant.AUTHORITIES, ProviderConstant.PATH_USER_INFO + "/#", CODE_USER_INFO_ITEM)
        }
    }

    private lateinit var dbOpenHelper: DBOpenHelper

    override fun onCreate(): Boolean {
        dbOpenHelper = DBOpenHelper(this.context)
        return false
    }

    private fun appendIdParamsIfNeed(uri: Uri, selection: String?): String? {
        val id = ContentUris.parseId(uri)
        if (id == -1L) {
            return selection
        }
        var where = "${ProviderConstant.COLUMN_ID} = $id"
        if (selection?.isNotEmpty() == true) {
            where = "$selection and $where"
        }
        return where
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val db = dbOpenHelper.writableDatabase
        return when (MATCHER.match(uri)) {
            CODE_USER_INFO_DIR, CODE_USER_INFO_ITEM -> {
                val count = db.delete(ProviderConstant.TABLE_NAME, appendIdParamsIfNeed(uri, selection), selectionArgs)
                context?.contentResolver?.notifyChange(uri, null)
                count
            }
            else -> throw IllegalArgumentException("Unknown Uri:$uri")
        }
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = dbOpenHelper.writableDatabase
        when (MATCHER.match(uri)) {
            CODE_USER_INFO_DIR -> {
                val rowId = db.insert(ProviderConstant.TABLE_NAME, null, values)
                context?.contentResolver?.notifyChange(uri, null)
                return ContentUris.withAppendedId(uri, rowId)
            }
            CODE_USER_INFO_ITEM -> {
                throw IllegalArgumentException("Invalid URI, cannot insert with ID: $uri")
            }
            else -> throw IllegalArgumentException("Unknown Uri:$uri")
        }
    }


    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?,
                       sortOrder: String?): Cursor? {
        val db = dbOpenHelper.readableDatabase
        return when (MATCHER.match(uri)) {
            CODE_USER_INFO_DIR,CODE_USER_INFO_ITEM -> {
                db.query(ProviderConstant.TABLE_NAME, projection, appendIdParamsIfNeed(uri, selection), selectionArgs, null, null, sortOrder)
            }
            else -> throw IllegalArgumentException("Unknown Uri:$uri")
        }
    }


    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        val db = dbOpenHelper.writableDatabase
        when (MATCHER.match(uri)) {
            CODE_USER_INFO_DIR, CODE_USER_INFO_ITEM -> {
                val count = db.update(ProviderConstant.TABLE_NAME, values, appendIdParamsIfNeed(uri, selection), selectionArgs)
                context?.contentResolver?.notifyChange(uri, null)
                return count
            }
            else -> throw IllegalArgumentException("Unknown Uri:$uri")
        }
    }

    override fun getType(uri: Uri): String {
        return when (MATCHER.match(uri)) {
            CODE_USER_INFO_DIR -> "vnd.android.cursor.dir/${ProviderConstant.TABLE_NAME}"
            CODE_USER_INFO_ITEM -> "vnd.android.cursor.item/${ProviderConstant.TABLE_NAME}"
            else -> throw  IllegalArgumentException("Unknown Uri:$uri")
        }
    }
}