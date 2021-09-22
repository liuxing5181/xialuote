package com.yuji.xlt.mvvm.http

/**
 * 用来封装业务错误信息<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/30]
 * @since V1.0.0
 */
class ApiException(val errorMessage: String, val errorCode: Int) :
    Throwable()