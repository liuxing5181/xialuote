package com.yuji.xlt.kotlin

/**
 * open <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
open class Hello17_1(var name: String) {
    fun description() = "Product:$name"
    open fun load() = "Nothing..."
}

class LuxuryProduct : Hello17_1("Luxury") {
    override fun load() = "LuxuryProduct loading"
}

data class Student(var name: String, var age: Int = 20) {

}

class PlayerScore(val name: String, val des: String) {
    operator fun component1() = name
    operator fun component2() = des
}

data class PlayerScore2(val name: String, val des: String) {
}

fun main() {
    //todo 继承 open关键字
    //todo 重写 override 关键字
    var product = LuxuryProduct()
    println(product.load())
    println(product.description())

    println(product is LuxuryProduct)
    //todo 7 copy函数
    val s = Student("jack")
    val copy = s.copy(name = "rose")
    println("copy s=$s")
    println("copy copy=$copy")

    //todo 8 解构函数 operator  数据类直接支持解构语法，内部帮忙实现了operator
    val (name: kotlin.String, des: String) = PlayerScore("a", "b")
    println("8 -- $name,$des")
    val (name1: kotlin.String, des1: String) = PlayerScore2("aaaa", "bbbb")
    println("8 -- $name1,$des1")

    //todo 11 代数类型
    println("11-" + Driver(LicenseStatus.LEAN).checkLicense())
}

enum class LicenseStatus {
    UNQ,
    LEAN,
    QUA
}

class Driver(var status: LicenseStatus) {
    //todo 11 代数类型
    fun checkLicense(): String {
        return when (status) {
            LicenseStatus.UNQ -> "1"
            LicenseStatus.LEAN -> "2"
            LicenseStatus.QUA -> "3"
        }
    }
}