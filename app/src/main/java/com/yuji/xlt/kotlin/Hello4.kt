package com.yuji.xlt.kotlin

/**
 * 字符串模板<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/9]
 * @since V1.0.0
 */
fun main() {
    val origin = "Jack"
    val dest = "Rose"
    println("$origin love $dest")
    val flag = false
    println("Answer is:${if (flag) "我可以" else "对不起"}")
}
