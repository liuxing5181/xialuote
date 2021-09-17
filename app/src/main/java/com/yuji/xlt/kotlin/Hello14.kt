package com.yuji.xlt.kotlin

import android.os.Build

/**
 * map <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
fun main() {
    //todo 1创建
    val map: Map<String, Int> = mapOf("jack" to 10, "jason" to 20)
    println(map)
    mapOf(Pair("AAa", 10), Pair("BB", 20))

    //todo 2取值
    println(map["jack"])
    println(map.getValue("jack"))
    println(map.getOrElse("jason") { "unknown" })
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        println(map.getOrDefault("jack", 0))
    }

    //todo 3遍历Map
    map.forEach {
        println(" $it.key , $it.values")
    }
    map.forEach { (key: String, value: Int) ->
        println("遍历2 =" + "${key},${value}")
    }
    //todo 4 mutableMap
    val mutableMap = mutableMapOf("aaa" to 1, "bbb" to 2)
    mutableMap.put("ccc", 3)
    println("mutableMap = $mutableMap")
}

