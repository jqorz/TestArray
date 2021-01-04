package com.jqorz.test2.contentprovider

import android.net.Uri


/**
 * @author  jqorz
 * @since  2020/12/29
 */
object ProviderConstant {
    const val AUTHORITIES = "com.datedu.launcher.provider"
    const val PATH_USER_INFO = "userinfo"


    const val TABLE_NAME = "user"
    const val COLUMN_USER_ID = "userid"
    const val COLUMN_USER_INFO = "userinfo"

    val BASE_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITIES")

    val CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER_INFO).build()


}