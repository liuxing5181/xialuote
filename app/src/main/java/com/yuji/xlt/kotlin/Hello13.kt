package com.yuji.xlt.kotlin

/**
 * set <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
fun main() {
    //todo 1 set
    val set: Set<String> = setOf("java", "kotlin")
    println("set =" + set.elementAt(1))
    //todo 2 去重
    val list = listOf("java", "java", "kotlin")
    println("list = $list")
    val list1 = list.toSet().toList()
    println("list1 = $list1")

    println("list2 "+listOf("java", "java", "kotlin").distinct())
}

