package com.yuji.xlt.kotlin

import android.os.Build
import androidx.annotation.RequiresApi

/**
 * List <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
@RequiresApi(Build.VERSION_CODES.N)
fun main() {
    //todo 1.getOrNull getOrElse
    val list: List<String> = listOf("Jason", "Jack", "Jacky")
    println(list[0])
    println(list.getOrElse(1) { "" })
    println(list.getOrNull(3))

    //todo 2.可变列表 mutableListOf
    val mutableList: MutableList<String> = mutableListOf("java", "kotlin")
    mutableList.add("aaa")
    mutableList.remove("java")
    println("可变列表 = $mutableList")

    //todo 3.mutator函数
    mutableList += "Jimmy"
    mutableList += "aac"
    mutableList += "aad"
    mutableList -= "aab"
    println("mutator函数 = $mutableList")

    //删除元素包含"aa"的元素
    mutableList.removeIf {
        it.contains("aa")
    }
    println("mutator函数2 = $mutableList")

    //todo 4.遍历
    for (s: String in list) {
        println("遍历1:$s")
    }
    list.forEach { println("遍历2:$it") }
    list.forEachIndexed { index, s ->
        println("遍历3:$index  $s")
    }

}


