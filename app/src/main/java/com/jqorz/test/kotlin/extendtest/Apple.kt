package com.jqorz.test.kotlin.extendtest

/**
 * @author jqorz
 * @since 2021/9/8
 */
internal class Apple : IFruit {
    var big: Boolean

    constructor(name: String?, big: Boolean) : super(name!!) {
        this.big = big
    }

    constructor(id: Int, big: Boolean) : super(id) {
        this.big = big
    }

    fun test() {
        big = true
    }
}