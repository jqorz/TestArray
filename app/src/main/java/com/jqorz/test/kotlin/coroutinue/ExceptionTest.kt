package com.jqorz.test.kotlin.coroutinue

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

/**
 * @author  jqorz
 * @since  2021/8/31
 */
object ExceptionTest {
    @JvmStatic
    fun main(args: Array<String>) {
        GlobalScope.launch {
            supervisorScope() {
                launch {
                    delay(100)
                    throw Exception("100")
                }
                launch {
                    delay(200)
                    println("200")
                }
                println("300")
            }
        }
        Thread.sleep(500)
    }
}