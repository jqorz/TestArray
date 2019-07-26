package com.jqorz.test.kotlin

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
                "not in 10-15"
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
}

fun main(args: Array<String>) {
    KotlinTest(User(age = 12, sexIsMale = false)).getWhen("1")
}

data class User(val name: String? = "", val age: Int = 0, val sexIsMale: Boolean = true) {
    var a = """a\na"""//不支持转义
    var b = "a\na"//支持转义
}
