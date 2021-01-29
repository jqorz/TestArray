package com.jqorz.test2.contentprovider

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jqorz.common.contentprovider.ProviderManger
import com.jqorz.test2.R


/**
 * @author  jqorz
 * @since  2020/12/29
 */
class ContentProviderClientActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var providerManger: ProviderManger

    private val TAG = "ContentProviderActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)

        providerManger = ProviderManger(contentResolver)

        findViewById<View>(R.id.btn_query).setOnClickListener(this)
        findViewById<View>(R.id.btn_update).setOnClickListener(this)
        findViewById<View>(R.id.btn_insert).setOnClickListener(this)
        findViewById<View>(R.id.btn_delete).setOnClickListener(this)
        findViewById<View>(R.id.btn_delete_all).setOnClickListener(this)
        findViewById<View>(R.id.btn_clear_log).setOnClickListener(this)
        findViewById<View>(R.id.btn_clear_create).setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_query -> {
                val result = providerManger.testFind()
                findViewById<TextView>(R.id.tv_result).append(result + "\n")
            }
            R.id.btn_update -> {
                providerManger.testUpdate()
            }
            R.id.btn_insert -> {
                val result = providerManger.testInsert()
                findViewById<TextView>(R.id.tv_result).append(result + "\n")
            }
            R.id.btn_delete -> {
                providerManger.testDelete()
            }
            R.id.btn_clear_log -> {
                findViewById<TextView>(R.id.tv_result).text = ""
            }
            R.id.btn_delete_all -> {
                providerManger.testDeleteAll()
            }
            R.id.btn_clear_create -> {
                providerManger.testDeleteAllAndCreate()
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