package com.jqorz.test.kotlin.coroutinue

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author  jqorz
 * @since  2021/8/25
 */
object CoroutineTest2 {
    @JvmStatic
    fun main(args: Array<String>) {
        GlobalScope.launch(Dispatchers.Unconfined) {
            for (i in 1..6) {
                println("当前 协程任务打印第$i 次，时间: ${System.nanoTime()} 线程=${Thread.currentThread().name}")
            }
        }

        for (i in 1..6) {
            println("当前 主主线程打印第$i 次，时间: ${System.nanoTime()}")
        }

        Thread.sleep(500)

        GlobalScope.launch(Dispatchers.Default) {
            for (i in 1..6) {
                println("默认 协程任务打印第$i 次，时间: ${System.nanoTime()} 线程=${Thread.currentThread().name}")
            }
        }

        for (i in 1..6) {
            println("默认 主主线程打印第$i 次，时间: ${System.nanoTime()}")
        }

        Thread.sleep(500)
    }
}