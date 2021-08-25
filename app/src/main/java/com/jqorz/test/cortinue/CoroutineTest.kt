package com.jqorz.test.cortinue

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.pow

/**
 * @author  jqorz
 * @since  2021/8/25
 */
object CoroutineTest {
    @JvmStatic
    fun main(args: Array<String>) {
//        GlobalScope.launch(Dispatchers.Unconfined) {
//            Log.d("AA", "协程初始化完成，时间: " + System.currentTimeMillis())
//            for (i in 1..3) {
//                Log.d("AA", "协程任务1打印第$i 次，时间: " + System.currentTimeMillis())
//            }
//            delay(500)
//            for (i in 1..3) {
//                Log.d("AA", "协程任务2打印第$i 次，时间: " + System.currentTimeMillis())
//            }
//        }
//
//        Log.d("AA", "主线程 sleep ，时间: " + System.currentTimeMillis())
//        Thread.sleep(1000)
//        Log.d("AA", "主线程运行，时间: " + System.currentTimeMillis())
//
//        for (i in 1..3) {
//            Log.d("AA", "主线程打印第$i 次，时间: " + System.currentTimeMillis())
//        }

        GlobalScope.launch {
            var s = ""
            for (i in 0..1000) {
                s += "${i.toDouble().pow(100)}"
            }
            println("1.launch....[当前线程为：${Thread.currentThread().name}] ")
        }
        println("2.launch.... ")

        runBlocking {
            var s = ""
            for (i in 0..1000) {
                s += "${i.toDouble().pow(100)}"
            }
            println("1.runBlocking....[当前线程为：${Thread.currentThread().name}] ")
        }
        println("2.runBlocking.... ")
    }
}