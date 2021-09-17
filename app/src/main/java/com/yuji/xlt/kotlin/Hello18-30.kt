package com.yuji.xlt.kotlin

/**
 * 泛型 定义<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
class MagicBox1<T>(item: T) {
    private var subject: T = item
    fun print() {
        println("$subject")
    }
}

class Boy1(val name: String, val age: Int) {
}

class Dog1(val weight: Int)

fun main() {
    val box1: MagicBox1<Boy1> = MagicBox1(Boy1("jack", 20))
    val box2: MagicBox1<Dog1> = MagicBox1(Dog1(18))
    println(box1.print())
    println(box2.print())
}
