package com.yuji.xlt.kotlin

/**
 * <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
fun main() {
    var number: Int? = null
    //Todo try 处理异常
    try {
        checkOperation(number)
        number!!.plus(1)
    } catch (e: Exception) {
        println(e)
    }
}

//检查异常
fun checkOperation(number: Int?) {
    //todo 自定义异常
//    number ?: throw UnskilledException()
    //todo 先决条件
    checkNotNull(number,{"something is not good."})
}

//自定义异常
class UnskilledException() : IllegalAccessError("操作不当")

