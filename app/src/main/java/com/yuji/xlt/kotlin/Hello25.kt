package com.yuji.xlt.kotlin

/**
 * map <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
fun main() {
    //todo zip
    val listOf1 = listOf("jack", "jason", "tommy")
    val listOf2 = listOf("large", "x-large", "medium")
    val zip = listOf1.zip(listOf2)
    println("listOf1 = $listOf1")
    println("listOf2 = $listOf2")
    println("zip = $zip")

    //todo fold
    val fold = listOf(1, 2, 3, 4).fold(0) { accumulator, number ->
        println("Accumulated value:$accumulator")
        accumulator + (number * 3)
    }
    //fold(0) -从0开始 number- 是每一个元素1234 accumulator-保存计算的值
    //第一次 0*3 = 0
    //第二次 0+1*3 = 3
    //第三次 3+2*3= 9
    //第四次 9+3*3 = 18
    //
    println("final value:$fold")
}