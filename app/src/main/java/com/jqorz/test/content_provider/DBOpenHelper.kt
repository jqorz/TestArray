package com.jqorz.test.content_provider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2018/5/19
 */
class DBOpenHelper(context: Context?) : SQLiteOpenHelper(context, DBNAME, null, DB_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {
        val sql = "CREATE TABLE ${ProviderConstant.TABLE_NAME} (${ProviderConstant.COLUMN_USER_ID} TEXT primary key, ${ProviderConstant.COLUMN_USER_INFO} TEXT)"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, arg1: Int, arg2: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${ProviderConstant.TABLE_NAME}")
        onCreate(db)
    }

    companion object {
        private const val DBNAME = "user.db" //数据库名称
        private const val DB_VERSION = 1 //数据库版本
    }
}