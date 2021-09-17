package com.yuji.xlt.kotlin

/**
 * <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
const val NAME = "Jimmy's friend"
const val NAMES = "jack,jacky,jason"
fun main() {
    //Todo substring
    val index: Int = NAME.indexOf("\'")
    val str = NAME.substring(0, index)
    println("字符串截取 = $str")
    //Todo split
    val data: List<String> = NAMES.split(",")
    val (origin: String, dest: String, proxy: String) = NAMES.split(",")
    println("字符串分割：" + data[0] + ";$origin $dest $proxy")
    //todo replace
    val str1 = "The people's Republic of China."
    val str2 = str1.replace(Regex("[aeiou]")) {
        when (it.value) {
            "a" -> "8"
            "e" -> "6"
            "i" -> "9"
            "o" -> "1"
            "u" -> "3"
            else -> it.value
        }
    }
    println("字符串替换：str1 = $str1 ;str2 = $str2")
    //todo 字符串比较== ===
    val string1 = "Jason"
    val string2 = "Jason"
    val string3 = "jason".capitalize()
    println("字符串比较:$string1,$string2")
    println(string1 == string2) //true 值相同
    println(string1 === string2)//true 引用也相同
    // java和kotlin中字符串string是不可变的，str1和str2都是指向同一个内存地址。
    println("字符串比较: string=$string1,string3=$string3")
    println(string1 == string3) //true 值相同
    println(string1 === string3)//false string1的引用是Jason的 string3的引用是jason的

    //todo 字符串遍历
    "The people's Republic of China.".forEach { println("字符串遍历 : $it *") }

}


