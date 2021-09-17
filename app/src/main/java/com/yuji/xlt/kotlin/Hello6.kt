package com.yuji.xlt.kotlin

/**
 * 函数。高阶<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/10]
 * @since V1.0.0
 */
fun main() {
    // todo 定义参数是函数的函数
    //获取促销文案
    val getDiscountWords = { goodsName: String, hour: Int ->
        val currentYear = 2027
        "${currentYear}年，双11 $goodsName 促销倒计时:$hour 小时}"
    }
    //展示
    showOnBoard("卫生纸", getDiscountWords)
    //todo 省略写法
    showOnBoard("洗手液") { goodsName: String, hour: Int ->
        val currentYear = 2027
        "${currentYear}年，双11 $goodsName 促销倒计时:$hour 小时}"
    }
    //todo 函数引用
    showOnBoard("牙膏", ::getDiscountWords)

    //Todo 函数类型作为返回类型
    val text: (String) -> String = showText()
    println(text("Jack"))

}

//具名参数
private fun showOnBoard(goodsName: String, showDiscount: (String, Int) -> String) {
    val hour: Int = (1..24).shuffled().last()
    println(showDiscount(goodsName, hour))
}

//具名函数 获取促销文案
private fun getDiscountWords(goodsName: String, hour: Int): String {
    val currentYear = 2027
    return "${currentYear}年，双11 $goodsName 促销倒计时:$hour 小时}"
}

//具名函数 返回类型是一个函数
private fun showText(): (String) -> String {
    return { name: String -> "hello $name" }
}