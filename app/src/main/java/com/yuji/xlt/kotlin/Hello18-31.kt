package com.yuji.xlt.kotlin

/**
 * 泛型函数 <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
class MagicBox<T>(item: T) {
    var available = false
    private var subject: T = item

    //todo 泛型函数
    fun fetch(): T? {
        return subject.takeIf { available }
    }
}

//人
open class Human(val age: Int)

//男孩
class Boy(val name: String, age: Int) : Human(age) {
    override fun toString(): String {
        return "Boy(name = '$name',age = '$age')"
    }
}

//男人
class Man(val name: String, age: Int) : Human(age) {
    override fun toString(): String {
        return "Man(name = '$name',age = '$age')"
    }
}

//狗
class Dog(val weight: Int)

fun main() {
    val box1: MagicBox<Boy> = MagicBox(Boy("jack", 20))
    val box2: MagicBox<Dog> = MagicBox(Dog(18))
    box1.available = true
    box1.fetch()?.run {
        println("you find $name;$age")
    }

}
