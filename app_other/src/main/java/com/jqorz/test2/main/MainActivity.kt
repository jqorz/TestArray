package com.jqorz.test2.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jqorz.common.base.BaseActivity
import com.jqorz.test2.R
import com.jqorz.test2.contentprovider.ContentProviderClientActivity
import com.jqorz.test2.music.MusicPlayActivity

class MainActivity : BaseActivity() {
    private fun initRecyclerView() {
        val mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView.layoutManager = GridLayoutManager(this, 3)
        val mAdapter: BaseQuickAdapter<ItemBean, BaseViewHolder> = object : BaseQuickAdapter<ItemBean, BaseViewHolder>(R.layout.item_main_select) {
            override fun convert(holder: BaseViewHolder, item: ItemBean) {
                val btn = holder.getView<TextView>(R.id.btn)
                btn.text = item.text
                btn.setOnClickListener { v: View? -> item.runnable.run() }
            }
        }
        mRecyclerView.adapter = mAdapter
        mAdapter.addData(ItemBean("ContentProvider客户端") { ContentProviderClientActivity.start(this) })
        mAdapter.addData(ItemBean("音乐播放") { MusicPlayActivity.start(mContext!!) })
    }

    override fun init() {
        initRecyclerView()

    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }
}