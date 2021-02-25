package com.jqorz.test.coil

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jqorz.common.FileUtil
import com.jqorz.common.base.BaseActivity
import com.jqorz.test.R

/**
 * @author  jqorz
 * @since  2021/2/25
 */
class CoilTestActivity : BaseActivity() {

    override fun init() {

        findViewById<View>(R.id.btn_clear).setOnClickListener {
            FileUtil.deleteFileOrFolder(CustomManager.getCachePath())
        }

        val mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView.apply {
            adapter = PicAdapter()
                    .apply {
                        setList(arrayListOf("https://img.tuguaishou.com/ips_templ_small/58/fb/e5/sm_3686559_1614232463_60373b8f73c8d.jpg!w216_new?auth_key=2246630400-0-0-98ebe8380b736e0ba9bbc8d877e6fa5c",
                                "https://img1.tuguaishou.com/ips_templ_small/25/37/11/sm_3713684_1614232457_60373b8966989.jpg!w216_new?auth_key=2246630400-0-0-8769cbbf2c4da2b4432d9900f89f79fc",
                                "https://img2.tuguaishou.com/ips_templ_small/6a/9c/c8/sm_3296722_1614232460_60373b8cc9e73.jpg!w216_new?auth_key=2246630400-0-0-1b9f0c4ccd989d2b5c51efe31ed01a4e",
                                "https://img.tuguaishou.com/ips_templ_small/ad/06/0b/sm_3775377_1614232469_60373b952aac9.jpg!w216_new?auth_key=2246630400-0-0-a96fc61f1853f50f878138d4f87912ed",
                                "https://img1.tuguaishou.com/ips_templ_small/5f/1e/60/sm_3770864_1614232466_60373b9284a40.jpg!w216_new?auth_key=2246630400-0-0-eb15e38845e484d259f4135143dabb4e",
                                "https://img2.tuguaishou.com/ips_templ_preview/w216_q100/88/e5/a1/lg_3225970_1592969643_5ef2c9ab2d7ae.jpg?auth_key=2246630400-0-0-1a6db49a51678c145aa37984ec67aea0",
                                "https://img.tuguaishou.com/ips_templ_preview/w216_q100/b0/0c/06/lg_3743663_1611811129_6012493906eb0.jpg?auth_key=2246630400-0-0-930a51b6402d5ed0229d624e1bc5e261",
                                "https://img1.tuguaishou.com/ips_templ_preview/w216_q100/9d/31/d4/lg_3668536_1609378851_5fed2c23e076b.jpg?auth_key=2246630400-0-0-ce5ff0855965ac152d20bcb208d191a6",
                                "https://img2.tuguaishou.com/ips_templ_preview/w216_q100/80/d4/22/lg_3747759_1612257743_601919cfab987.jpg?auth_key=2246630400-0-0-7f0d7a9f3d420042a1eda55fa5c0e696",
                                "https://img.tuguaishou.com/ips_templ_preview/w216_q100/f7/b9/b7/lg_3750990_1612124245_6017105539aaf.jpg?auth_key=2246630400-0-0-6fa1aa68698337f7b2e56107968be619",
                                "https://img1.tuguaishou.com/ips_templ_preview/w216_q100/b4/4d/f7/lg_3761365_1612533352_601d4e68a469a.jpg?auth_key=2246630400-0-0-d9b182405873a713a438934afa0cb257",
                                "https://img2.tuguaishou.com/ips_templ_preview/w216_q100/3a/68/ab/lg_3763186_1612660412_601f3ebc535be.jpg?auth_key=2246630400-0-0-b820bdeb2da1f93fdd42cd4e75a0c3db",
                                "https://img.tuguaishou.com/ips_templ_preview/w216_q100/2b/ed/15/lg_3687734_1610000674_5ff6a92202037.jpg?auth_key=2246630400-0-0-d407773a26e7085ea2d5a82df57f5383",
                                "https://img1.tuguaishou.com/ips_templ_preview/w216_q100/c5/32/30/lg_3699221_1610369174_5ffc489613be6.jpg?auth_key=2246630400-0-0-6621b0654c437130fa16f21f6ef9c004"))
                    }
            layoutManager = LinearLayoutManager(this@CoilTestActivity)
        }
    }

    override fun getLayoutResId() = R.layout.activity_coil

    inner class PicAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_coil_image) {
        override fun convert(holder: BaseViewHolder, item: String) {
            val iv = holder.getView<ImageView>(R.id.iv_Pic)
            val text = holder.getView<TextView>(R.id.mRingProgressView)

            val localPath = CustomManager.getCoilFilePath(item)
            val serial = (holder.adapterPosition + 1).toString()
            val request = ImageRequest.Builder(context)
                    .data(item)
                    .target(ProgressTarget(text, iv, item, serial))
                    .build()
            context.imageLoader.enqueue(request)

            holder.setText(R.id.tv_serial, serial)
        }


    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, CoilTestActivity::class.java)
            context.startActivity(starter)
        }


    }
}