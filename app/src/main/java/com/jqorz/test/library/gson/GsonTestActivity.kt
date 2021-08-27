package com.jqorz.test.library.gson

import android.content.Context
import android.content.Intent
import android.view.View
import com.jqorz.common.base.BaseActivity
import com.jqorz.test.R
import com.jqorz.test.util.GsonUtil

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2019/8/8
 * 测试Gosn2Map方法的泛型参数
 * 如果使用T,在编译版本28，手机版本7.0的时候会崩溃，其他版本正常
 * 如果使用Object，都正常
 */
class GsonTestActivity : BaseActivity() {
    fun onClickBtn1(view: View?) {
        //字符串2 用T
        val s = "{\"code\":1,\"data\":\"{\\\"author\\\":\\\"\\\",\\\"c1\\\":\\\"\\\",\\\"id\\\":8,\\\"sentence\\\":\\\"自信是成功的第一秘诀。——爱默生\\\"}\",\"msg\":\"操作成功\",\"responsetime\":1565238517000}"
        val map = com.jqorz.test.util.GsonUtil.json2Map<Any>(s)
        if (map != null) {
            println("jqjq 111" + map["code"])
        }
    }

    fun onClickBtn2(view: View?) {
        val test = "{\"name\":\"1\"}"
        val bean = com.jqorz.test.util.GsonUtil.json2Bean(test, BigBean::class.java)
        println("bean$bean")
    }

    override fun init() {}
    override fun getLayoutResId(): Int {
        return R.layout.activity_gson
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, GsonTestActivity::class.java)
            context.startActivity(starter)
        }
    }
}