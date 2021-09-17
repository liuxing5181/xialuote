package com.yuji.xlt.kotlin

/**
 * let also with run apply<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
fun main() {
    var str: String? = "butterfly"
    //Todo object?.let{//表示object不为null的条件下，才会去执行let函数体
    str = str?.let {
//        非空白的字符串
        if (it.isNotBlank()) {
            it.capitalize()
        } else {
            "butterfly"
        }
    }
    println(str)
    //todo 空合并操作符
    str = null
    println(str ?: "jack")

    //todo let+?: 如果字符串不为null就执行let语句，否则执行”butterfly“
//    str = "jack"
    str = null
    str = str?.let { it.capitalize() } ?: "butterfly"
    println(str)
}


