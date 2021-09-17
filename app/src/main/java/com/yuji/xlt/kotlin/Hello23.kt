package com.yuji.xlt.kotlin

/**
 * map <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
fun main() {
    //todo map
    val animals: List<String> = listOf("zebra", "giraffe", "elephant", "rat")
    val babies = animals
        .map { animals -> "A baby $animals" }
        .map { baby -> "$baby,with the cutest little tail ever!" }
    println("animals = $animals")
    println("babies = $babies")

    val animalsLength = animals.map { it.length }
    println("animalsLength = $animalsLength")

    //todo flatmap
    val result = listOf(listOf(1, 2, 3), listOf(4, 5, 6)).flatMap { it }
    println("result = $result")
}