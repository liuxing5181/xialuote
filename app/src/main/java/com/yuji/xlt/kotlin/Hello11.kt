package com.yuji.xlt.kotlin

import java.io.File

/**
 * 标准库函数 <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
fun main() {
    //Todo apply 配置函数，你可以传入一个接收者，然后调用一系列函数来配置它以便使用，它会返回配置好的接收者
    val data = UserDataBean("zhangsan", 12, "zhangsan1")
    data.apply { println("data = $name;$age $nikeName") }

    //todo let 能使某个变量作用于其lambda表达式里，让it关键字能引用它。返回 最后一行/return的表达式
    val letResult = listOf(3, 2, 1).first().let { it * it }
    println("letResult = $letResult")

    //todo run 和apply类似，返回结果不同，它返回的是lambda或者最后一行。
//    var file = File("E://xxxx.txt")
//    val runResult = file.run { readText().contains("great") }
//    println(runResult)
    // 链式调用
    val runResult = "The people‘s Republic of China."
        .run(::isLong)
        .run(::showMessage)
        .run(::myPrint)

    //todo with函数是run的变体，功能行为是一样的。但with的调用方式不同，调用with需要（值参作为其第一个参数传入）。

    val runResult1 = "The people‘s Republic of China.".run { length > 10 }
    println("runResult1 =$runResult1")
    val runResult2 = with("The people‘s Republic of China.") {
        length > 10
    }
    println("runResult2 =$runResult2")

    //todo also[也] 功能和let类似，返回类型不同 它返回传入者对象本身
    //todo also 适合针对同一原始对象，利用副作用做事，可以基于原始接收者对象执行额外的链式调用。
    var fileContents: List<String>
    File("E://xx.txt")
        .also { println(it.name) }
        .also { fileContents = it.readLines() }
    println(fileContents)
}

private fun isLong(name: String) = name.length >= 10
private fun showMessage(isLong: Boolean): String {
    return if (isLong) {
        "Name is too long"
    } else {
        "please rename."
    }
}

private fun myPrint(str: String) {
    println("runResult = $str")
}

class UserDataBean() {
    constructor(name: String, age: Int, nikeName: String) : this() {
        this.name = name
        this.age = age
        this.nikeName = nikeName
    }

    var name: String = ""
    var age: Int = 0
    var nikeName: String? = null
}


