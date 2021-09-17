package com.yuji.xlt.kotlin

import java.io.File

/**
 * 扩展函数 <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
val String.numVowels
    get() = count { "aeiou".contains(it) }

fun String.addExt(amount: Int = 1) = this + "!".repeat(amount)


fun Any.easyPrint() = println(this)

fun <T> T.easyPrint1(): T {
    println(this)
    return this
}

fun String?.printWithDefault(default: String) = print(this ?: default)

fun main() {
    //todo 1给字符串添加扩展函数
    println("1 -----------")
    println("abcd".addExt(2))

    //todo 2超类添加扩展函数
    println("2 -----------")
    "abc".easyPrint()
    15.easyPrint()

    //todo 3 泛型扩展函数  -如果想在addExt扩展函数之前和之后分别打印字符串怎么办？
    println("3 -----------")
    "abc".easyPrint1().addExt(4).easyPrint1()

    //todo 4 let
    "abc".let {

    }
    //它接受一个lambda表达式，lambda接受者T作为值参，返回的R-lambda表达式返回的任何新类型。
//    public inline fun <T, R> T.let(block: (T) -> R): R { //todo (T)就是传入进来的”abc“
//        contract {
//            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
//        }
//        return block(this) //todo let返回一个新类型R
//    }
    println("5 -----------")
    //todo 5 扩展属性 给String类添加一个扩展，这个扩展属性可以统计字符串里有多少个元音字母。
    "The people's Republic of China".numVowels.easyPrint1()

    println("6 -----------")
    //todo 5 可空扩展
//    val nullString: String? = null
    val nullString: String? = "efd"
    nullString.printWithDefault("adc")

    //todo 10 apply
    "".apply { }
    val file: File = File("xx").apply {
        setReadable(true) //this.setReadable(true) 扩展函数里自带了接收者对象的this隐式调用
    }

    /**
     * 10-4 分解一下apply函数：fun <T> T.apply(block: T.() -> Unit): T{}
     */
    //1.定义扩展函数
    fun File.ext(): Unit {
        setReadable(true)
    }
    //2.给block变量赋值
    val block = File::ext
    //2.传入apply函数
    File("xx").apply {
        block
    }
}

/**
 * 10-1 apply 写法
 */
public inline fun <T> T.apply(block: T.() -> Unit): T {
    block()
    return this
}

//不带泛型的写法
public inline fun File.apply(block: File.() -> Unit): File {
    block()
    return this
}

/**
 * 10-2 泛型的扩展函数
 */
fun <T> T.easyPrint2() = println(this)

/**
 * 10-3
 * () -> Unit 匿名函数 无参返回Unit
 *
 * T.() -> Unit 泛型的扩展函数
 * ？为什么不是传普通的匿名函数,因为不用泛型就限制了apply使用范围，它应该式任意类型。File.apply就仅限于file。string.apply就无法使用了
 */
fun doSomething(fix: () -> Unit) {
}
