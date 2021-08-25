package com.jqorz.test.cortinue

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
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
        GlobalScope.launch {
            var s = ""
            for (i in 0..1000) {
                s += "${i.toDouble().pow(100)}"
            }
            println("1.launch....[当前线程为：${Thread.currentThread().name}] ")
            async {

            }.await()
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