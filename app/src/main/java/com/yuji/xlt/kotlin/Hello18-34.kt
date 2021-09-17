package com.yuji.xlt.kotlin

/**
 * vararg关键字和get函数 <BR>
 * //todo MagicBox能存放任何类型的Human类型，但一次只能放一次，如果需要放入多个实例捏
 * //todo varary == java ...
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
class MagicBox4<T : Human>(vararg item: T) {
    var available = false
    var subject: Array<out T> = item

    fun fetch(index: Int): T? {
        return subject[index].takeIf { available }
    }

    //todo 多泛型参数
    //业务 把元素进行修改，盒子里面放的时男孩Boy，取出来，把他变成男人Man2。
    fun <R> fetch(index: Int, subjectModFunction: (T) -> R): R? {
        return subjectModFunction(subject[index]).takeIf { available }
    }
}



fun main() {
    val box1: MagicBox4<Boy> = MagicBox4(
        Boy("Jack", 15),
        Boy("Jacky", 16),
        Boy("John", 17)
    )
    box1.available = true
    box1.fetch(1)?.run {
        println("you find $name,$age")
    }
    //
    val man = box1.fetch(2) {
        Man(it.name, it.age.plus(20))
    }
    man?.let {
        println("男孩长大了 - ${it.name},${it.age}")
    }
}
