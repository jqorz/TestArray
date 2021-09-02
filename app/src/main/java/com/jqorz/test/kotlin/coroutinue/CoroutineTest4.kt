package com.jqorz.test.kotlin.coroutinue

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author  jqorz
 * @since  2021/8/25
 */
object CoroutineTest4 {
    @JvmStatic
    fun main(args: Array<String>) {
        GlobalScope.launch {
            val a1 = async {
                println("11")
                delay(50)
                println("22")
            }
            val b1 = async {
                println("33")
                delay(100)
                println("44")
            }
            launch {

            }
            println("55")
            a1.await()
            println("66")
            b1.await()
            println("77")
        }
        Thread.sleep(500)
    }

}