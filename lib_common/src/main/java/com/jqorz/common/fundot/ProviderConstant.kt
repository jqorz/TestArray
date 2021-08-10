package com.jqorz.common.fundot

import android.net.Uri


/**
 * @author  jqorz
 * @since  2020/12/29
 */
object ProviderConstant {
    const val AUTHORITIES = "com.p4bu.packageprovider"
    const val TABLE_NAME = "package_status"

    const val PATH_PACKAGE_STATUS = "package_status"


    const val COLUMN_ID = "id"
    const val COLUMN_PACKAGE_NAME = "package_name"
    const val COLUMN_ALLOW_SHOW = "allow_show"

    val CONTENT_URI = Uri.parse("content://$AUTHORITIES/$PATH_PACKAGE_STATUS")


}