package com.yuji.xlt.kotlin

/**
 * 定义类 <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
class Hello16 {
    var name: String = ""
        get() = field.capitalize()
        set(value) {
            field = value.trim()
        }
}

//todo 主构造函数,_name临时变量. var age 变量
class Hello16_1(_name: String, var age: Int) {
    //todo 次构造函数
    constructor(name: String) : this(name, age = 0)

    val config by lazy { loadConfig() }
    fun loadConfig(): String {
        println("loading...")
        return "xxx"
    }
}

//todo 陷阱1
class Hello16_2() {
    /**
     * blood 要声明在init之前才能使用
     */
    val blood = 100

    init {
        val bloodBonus: Int = blood.times(4)
    }
}

//todo 陷阱2
class Hello16_3() {
    val name: String

    init {
        name = "jack"
        println(name)
    }
}

//todo 陷阱3
class Hello16_4(_name: String) {
    val name: String = _name
    val playerName: String = initPlayerName()
    private fun initPlayerName() = name
}


fun main() {
    var p = Hello16()
    p.name = "AAA"
    println(p.name)
    p.name = "  BBB "
    println(p.name)
    var p2 = Hello16_1("java", 1)
    Thread.sleep(3000)
    println(p2.config)

    println(Hello16_4("jack").playerName)
}

