package com.yuji.xlt.kotlin

/**
 * 接口 <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
interface Movable {
    //todo 默认赋值
    val maxSpeed: Int
        get() = (1..500).shuffled().last()
    var wheels: Int
    fun move(movable: Movable): String
}

class Car(_name: String, override var wheels: Int = 4) : Movable {
    //todo 接口 必须提供get，set方法 super使用父类的值
    override var maxSpeed: Int
        get() = super.maxSpeed
        set(value) {}

    override fun move(movable: Movable): String {
        return ""
    }
}

fun main() {

}