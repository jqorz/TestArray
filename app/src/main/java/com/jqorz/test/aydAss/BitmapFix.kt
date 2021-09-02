package com.jqorz.test.aydAss

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import java.io.FileInputStream

object BitmapFix {
    var TARGET_COLOR = Color.BLACK
    var USELESS_COLOR = Color.WHITE

    fun fix(img: Bitmap) {
        val w = img.width
        val h = img.height
        var xOffset = 0
        var yOffset = 0
        var i = 0
        var times = 0
        var last = -1
        outter1@ while (i < w) {
            var j = 0
            var count = 0
            while (j < h) {
                if (img.getPixel(i, j) == TARGET_COLOR && ++count > 1) {
                    if (i == last + 1) {
                        if (++times == 4) {
                            xOffset = i - 3
                            break@outter1
                        }
                    } else {
                        times = 1
                    }
                    last = i
                    break
                }
                ++j
            }
            ++i
        }
        i = 0
        times = 0
        last = -1
        outter2@ while (i < h) {
            var j = 0
            var count = 0
            while (j < w) {
                if (img.getPixel(j, i) == TARGET_COLOR && ++count > 1) {
                    if (i == last + 1) {
                        if (++times == 4) {
                            yOffset = i - 3
                            break@outter2
                        }
                    } else {
                        times = 1
                    }
                    last = i
                    break
                }
                ++j
            }
            ++i
        }
        println("x=$xOffset y=$yOffset")
    }

    private fun isTarget(colorInt: Int): Boolean {
        val hsb = FloatArray(3)
        Color.colorToHSV(colorInt, hsb)
        return hsb[2] < 0.7f // b小于0.7
    }

    /**
     * 去噪
     *
     * @param bitmap 图形验证码文件
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun denoise(bitmap: Bitmap): Bitmap? {
        //没法直接setPixel必须重新创建一个
        val img = bitmap.copy(bitmap.config, true)
        val width = img.width
        val height = img.height
        for (x in 0 until width) {
            for (y in 0 until height) {
                if (x > 1 && x < width - 1 && y > 8 && y < height - 3 && isTarget(img.getPixel(x, y))) {
                    img.setPixel(x, y, TARGET_COLOR)
                } else {
                    img.setPixel(x, y, USELESS_COLOR)
                }
            }
        }
        for (x in 1 until width - 1) {
            for (y in 8 until height - 3) {
                if (img.getPixel(x, y) == TARGET_COLOR) {
                    var shotNum = 0
                    for (i in 0..8) {
                        shotNum += if (img.getPixel(
                                x - 1 + i % 3,
                                y - 1 + i / 3
                            ) == TARGET_COLOR
                        ) 1 else 0
                    }
                    if (shotNum <= 3) {
                        img.setPixel(x, y, USELESS_COLOR)
                    }
                }
            }
        }
        return img
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val bitmap = BitmapFactory.decodeFile("D:/a.jpg")
        fix(denoise(bitmap)!!)
    }
}