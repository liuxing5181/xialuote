package com.yuji.xlt.kotlin

/**
 * 抽象 <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
abstract class Gun(val runge: Int) {
    protected open fun doSomething() {
        println("doSomething")
    }

    abstract fun trigger(): String
}

class AK47(_price: Int) : Gun(runge = 100) {
    override fun trigger(): String {
        return "AK47 shooting..."
    }

    override fun doSomething() {
        return doSomething()
    }
}

fun main() {
    val g: Gun = AK47(500)
    println(g.trigger())
}