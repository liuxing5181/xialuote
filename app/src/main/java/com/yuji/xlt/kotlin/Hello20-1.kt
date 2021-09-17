package com.yuji.xlt.kotlin

class MagicBox6<T : Human> {
    inline fun <reified T> fandomOrBackup(backup: () -> T): T {
        val items = listOf(
            Boy("张三", 10),
            Man("李四", 30)
        )
        val random = items.shuffled().first()
        return random as? T ?: backup()
    }
}

fun main() {
    //指定类型boy，调用方法，如果随机产生的对象是boy则返回boy，否则返回传入的匿名函数
    var box1: MagicBox6<Boy> = MagicBox6()
    val subject = box1.fandomOrBackup {
        Man("王马斯", 99)
    }
    println(subject)
}

