package com.yuji.xlt.kotlin

/**
 * 泛型类型约束 <BR>
 * //todo 如果要确保MagicBox里面只能装指定类型的物品，如Human类型，怎么办？
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
class MagicBox3<T : Human>(item: T) {
    var available = false
    var subject: T = item

    fun fetch(): T? {
        return subject.takeIf { available }
    }

}


fun main() {
    val box1: MagicBox3<Human> = MagicBox3(Boy("Jack", 20))
}
