package com.yuji.xlt.common.http

/**
 * 初始化Retrofit,维护多个ApiService单例对象<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/30]
 * @since V1.0.0
 */
open class RetrofitManager {
    companion object {
        /**
         * 用于存储ApiService
         */
        private val map = mutableMapOf<Class<*>, Any>()

        /**
         * 只初始化一次
         */
        private val retrofit = RetrofitFactory.factory()

        //动态指定域名
        fun <T : Any> getApiService(apiClass: Class<T>): T {
            return getService(apiClass)
        }

        //动态指定域名
        fun <T : Any> getResApiService(apiClass: Class<T>): T {
            return getService(apiClass)
        }

        /**
         * 获取ApiService单例对象
         */
        private fun <T : Any> getService(apiClass: Class<T>): T {
            //重入锁单例避免多线程安全问题
            return if (map[apiClass] == null) {
                synchronized(RetrofitManager::class.java) {
                    val t = retrofit.create(apiClass)
                    if (map[apiClass] == null) {
                        map[apiClass] = t
                    }
                    t
                }
            } else {
                map[apiClass] as T
            }
        }
    }
}