package com.yuji.xlt.kotlin

/**
 * reified关键字 <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
class MagicBox5<T : Human>() {
    //todo inline fun<reified> 随机产生一个对象，如果不是指定类型对象，通过backup函数生成一个指定类型对象。
    inline fun <reified T> fandomOrBackup(backup: () -> T): T {
        val items: List<Human> = listOf(
            Boy("jack", 20),
            Man("john", 35)
        )
        val random: Human = items.shuffled().first()
        return if (random is T) {
            random
        } else {
            backup()
        }
    }
}

fun main() {
    //指定类型man 是则返回该类型john的对象 如果不是则返回jimmy这个对象。
    val box1: MagicBox5<Man> = MagicBox5()
    var subject = box1.fandomOrBackup {
        Man("jimmy", 29)
    }
    println(subject)
}

