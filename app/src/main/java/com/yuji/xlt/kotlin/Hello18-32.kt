package com.yuji.xlt.kotlin

/**
 * 多泛型参数 <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
class MagicBox2<T>(item: T) {
    var available = false
    private var subject: T = item

    //todo 泛型函数
    fun fetch(): T? {
        return subject.takeIf { available }
    }

    //todo 多泛型参数
    //业务 把元素进行修改，盒子里面放的时男孩Boy2，取出来，把他变成男人Man2。
    fun <R> fetch(subjectModFunction: (T) -> R): R? {
        return subjectModFunction(subject).takeIf { available }
    }
}

fun main() {
    val box1: MagicBox2<Boy> = MagicBox2(Boy("jack", 20))
    box1.available = true
    //男孩长大了
    val man = box1.fetch() {
        Man(it.name, it.age.plus(10))
    }
    man?.let { println("${it.name},${it.age}") }

}
