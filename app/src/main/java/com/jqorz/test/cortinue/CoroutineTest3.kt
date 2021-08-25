package com.jqorz.test.cortinue

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.pow

/**
 * @author  jqorz
 * @since  2021/8/25
 */
object CoroutineTest3 {
    @JvmStatic
    fun main(args: Array<String>) {
        GlobalScope.launch {
            println("1.launch....")
            run()
            println("2.launch....")
        }
        println("3.launch.... ")
        Thread.sleep(500)
    }

    suspend fun run() {
        delay(10)
        var s = ""
        for (i in 0..1000) {
            s += "${i.toDouble().pow(100)}"
        }
        println("end")
    }
}