package com.jqorz.test2.contentprovider

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jqorz.common.contentprovider.ProviderManger
import com.jqorz.test2.R


/**
 * @author  jqorz
 * @since  2020/12/29
 */
class ContentProviderClientActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "ContentProviderActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)

        findViewById<View>(R.id.btn_query).setOnClickListener(this)
        findViewById<View>(R.id.btn_update).setOnClickListener(this)
        findViewById<View>(R.id.btn_insert).setOnClickListener(this)
        findViewById<View>(R.id.btn_delete).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_query -> {
                ProviderManger(contentResolver).testFind()
            }
            R.id.btn_update -> {
                ProviderManger(contentResolver).testUpdate()
            }
            R.id.btn_insert -> {
                ProviderManger(contentResolver).testInsert()
            }
            R.id.btn_delete -> {
                ProviderManger(contentResolver).testDelete()
            }
        }
    }


    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ContentProviderClientActivity::class.java)
            context.startActivity(starter)
        }
    }
}