package com.jqorz.test.kotlin.kotlin

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import java.util.*
import kotlin.math.max

/**
 * copyright datedu
 * @author  jqorz
 * @since  2019/7/22
 */
class KotlinTest(val user: User) {
    fun main(args: Array<User>) {
        println((args.indices.any { it % 2 == 0 }))
    }

    fun greet() {
        println("Hello, ${user.a}")
        println("Hello, " + user.a == user.b)
    }

    fun getIf(a: Int, b: Int): Int {
//        return if (a>b) a else b
        return max(a, b)
    }

    fun getFor() {
        //1-5 步进2
        for (a in 1..5 step 2) {
            print(a)
        }
    }

    fun getWhen(a: String) {
        var b = when (a) {
            "1", "2" -> {
                val c = "b1$a"
                print(c)
                c
            }
            else -> "b no"
        }
    }

    fun getWhen1(a: Int?) {
        //检测某个值是否在区间或者集合中
        var result = when (a) {
            in 1..15 -> {
                "in 1-15"
            }
            !in 10..15 -> {
                //全闭区间
                "not in 10-15"
            }
            in 15 until 250 -> {
                //左闭右开区间
                "in 15-250"
            }
            else -> {
                "in else"
            }
        }
    }

    fun getWhen2(a: Int?) {
        //检测某个值是否是某种类型
        when (a) {
            is Int -> print("is Int")
            !is Int -> print("is not Int")
        }
    }

    fun getWhen3(a: Int) {
        when {
            a > 0 -> print("a>0")
            a < 5 -> print("0<a<5")
        }
    }

    fun getList() {
        val list = listOf(User(name = "1"), User(name = "12", age = 12));
        for ((index, user) in list.withIndex()) {
            print("index = $index name = (${user.name})")
        }

        val map = TreeMap<String, Int>()
        map["1"] = 22
        map["123"] = 12
        print(map["1231"])
    }

    fun sum(a: Int, b: Int) = "${a + b}+a"

    fun sum2(a: Int, b: Int): Int {
        return a + b
    }

    fun testFun() {
        var sum = { x: Int, y: Int -> x + y }
        var sum2: (Int, Int) -> Int = { x, y -> x + y }
    }

    fun testException() {
        try {
            print("dd".toInt())

        } catch (e: Exception) {

        }

    }

    fun testCancel() {
        GlobalScope.launch {
            val f = flow {
                for (i in 1..3) {
                    delay(500)
                    Log.e(TAG, "emit $i")
                    emit(i)
                }
            }
            withTimeoutOrNull(1600) {
                f.collect {
                    delay(500)
                    Log.e(TAG, "consume $it")
                }
            }
            Log.e(TAG, "cancel")
        }
    }
}

private const val TAG = "KotlinTest"

fun main() {
    KotlinTest(User(age = 12, sexIsMale = false)).testFun()
}

data class User(val name: String? = "", val age: Int = 0, val sexIsMale: Boolean = true) {
    var a = """a\na"""//不支持转义
    var b = "a\na"//支持转义
}
