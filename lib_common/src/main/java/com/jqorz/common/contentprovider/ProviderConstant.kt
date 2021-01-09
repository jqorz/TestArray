package com.jqorz.common.contentprovider

import android.net.Uri
import android.provider.BaseColumns


/**
 * @author  jqorz
 * @since  2020/12/29
 */
object ProviderConstant {
    const val AUTHORITIES = "com.datedu.launcher.provider"
    const val PATH_USER_INFO = "userinfo"


    const val TABLE_NAME = "user"
    const val COLUMN_ID = BaseColumns._ID
    const val COLUMN_USER_ID = "userid"
    const val COLUMN_USER_INFO = "userinfo"

    val CONTENT_URI = Uri.parse("content://$AUTHORITIES/$PATH_USER_INFO")


}