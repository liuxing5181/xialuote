package com.yuji.xlt.kotlin

/**
 * 条件语句<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/9]
 * @since V1.0.0
 */
fun main() {
    val age = 4
    if (age in 0..3) {
        println("婴幼儿")
    } else if (age in 3..12) {
        println("少儿")
    } else {
        println("未知")
    }

    when (age) {
        in 0..3 -> {
            println("婴幼儿")
        }
        in 3..12 -> {
            println("少儿")
        }
        else -> {
            println("未知")
        }
    }
}
