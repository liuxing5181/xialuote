package com.yuji.xlt.kotlin

/**
 * map <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
fun main() {
    //todo filter
    val listOf = listOf("jack", "jimmy", "rose", "tom")
    val result = listOf.filter { it.contains("j") }
    println("listOf = $listOf")
    println("result = $result")
    //todo filter+flatMap
    val listOf1 = listOf(
        listOf("red apple", "green apple", "blue apple"),
        listOf("red fish", "blue fish"),
        listOf("yellow banana", "teal banana")
    )
    val flatMap = listOf1.flatMap {
        it.filter { it.contains("red") }
    }
    println("listOf1 = $listOf1")
    println("flatMap = $flatMap")

    //todo 找素数
    val listOf2 = listOf(7, 4, 8, 4, 3, 22, 19, 11)
    val filter = listOf2.filter { number ->
        //2 until number ->2到number
        // number % it ->number/遍历的数
        // none ->没有一个是==0的
        (2 until number).map { number % it }.none {
            it == 0
        }
    }
    println("listOf2 = $listOf2")
    println("filter = $filter")
}