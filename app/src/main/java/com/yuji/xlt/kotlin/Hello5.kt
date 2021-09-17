package com.yuji.xlt.kotlin

/**
 * 函数-匿名函数<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/9]
 * @since V1.0.0
 */
fun main() {
//    val total: Int = "blessingFunction".count()
//    val totals: Int = "blessingFunction".count { letter -> letter == 's' }
//    val blessingFunction: () -> String = {
//        val name = "Jack"
//        "hello $name"
//    }
//    val blessingFunction2: (String) -> String = { name ->
//        "hello $name"
//    }
//    val blessingFunction3: (String) -> String = {
//        "hello $it"
//    }
//    val blessingFunction4: (String, Int) -> String = { name, age ->
//        "hello $name $age"
//    }
//    val blessingFunction5 = { name: String, age: Int ->
//        "hello $name , $age"
//    }

    // todo 1函数头
    println("函数" + doSomeThing(3, false))
    //todo 2 int = 2 默认值 3 如果使用命名值参，不用管值参顺序,多参数时避免传参失误。
    fix(age = 4, name = "java")

    /**
     * todo 4 匿名函数
     */
    val total: Int = "mississippi".count()
    println(total)
    val totals: Int = "mississippi".count { letter -> letter == 's' }
    println(totals)

    //返回类型：blessingFunction变量的类型是一个匿名函数() -> String
    val blessingFunction: () -> String = {
        val holiday = "new Year."
        "Happy $holiday"
    }
    println("blessingFunction = " + blessingFunction())

    //函数参数
    // blessingFunction2: (String) 对应 name
    // "$name Happy $holiday2" 对应 -> String =
    val blessingFunction2: (String) -> String = { name ->
        val holiday2 = "new year."
        "$name Happy $holiday2"
    }
    println(blessingFunction2("Jack"))

    //[IT]
    val blessingFunction3: (String) -> String = {
        val a = "new day."
        "$it happy $a"
    }
    println(blessingFunction3("jack"))
    //【类型推断】
    val blessingFunction4 = { name: String, age: Int ->
        "$name happy $age"
    }
    println(blessingFunction4("jack", 1))
}

private fun doSomeThing(age: Int, flag: Boolean): String {
    return "result"
}

fun fix(name: String, age: Int = 2) {
    println("$name ; $age")
}
