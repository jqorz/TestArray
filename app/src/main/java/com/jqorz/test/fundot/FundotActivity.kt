package com.jqorz.test.fundot

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hi.dhl.binding.viewbind
import com.jqorz.common.fundot.ProviderManger
import com.jqorz.test.R


/**
 * @author  jqorz
 * @since  2020/12/29
 */
class FundotActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var providerManger: ProviderManger

    private val binding by viewbind<com.jqorz.test.databinding.ActivityFundotTestBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        providerManger = ProviderManger(contentResolver)

        binding.btnQuery.setOnClickListener(this)
        binding.btnDelete.setOnClickListener(this)
        binding.btnDeleteAll.setOnClickListener(this)
        binding.btnInsert.setOnClickListener(this)
        binding.btnUpdate.setOnClickListener(this)
        binding.btnClearLog.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        val packageName: String = binding.edtPackage.text.toString()
        val isShow = if (binding.rbShow.isChecked) 1 else 0
        when (v.id) {
            R.id.btn_query -> {
                val result = providerManger.testFind()
                binding.tvResult.append(result + "\n")
            }
            R.id.btn_insert -> {
                val result = providerManger.testInsert(packageName, isShow)
                binding.tvResult.append(result + "\n")
            }
            R.id.btn_delete -> {
                providerManger.testDelete(packageName)
            }
            R.id.btn_clear_log -> {
                binding.tvResult.text = ""
            }
            R.id.btn_delete_all -> {
                providerManger.testDeleteAll()
            }
            R.id.btn_update -> {
                providerManger.testUpdate(packageName, isShow)
            }
        }
    }


    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, FundotActivity::class.java)
            context.startActivity(starter)
        }
    }


}