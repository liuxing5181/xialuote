package com.yuji.xlt.mvvm.http

import com.yuji.xlt.ability.AppContext
import com.yuji.xlt.ability.utils.Logger
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * des Retrofit工厂类
 * @author zs
 * @date 2020-05-09
 */
object RetrofitFactory {
    const val BASE_URL = "https://www.wanandroid.com"
    private const val DEFAULT_TIMEOUT = 10000
    //缓存100Mb
    private val okHttpClientBuilder: OkHttpClient.Builder
        get() {
            return OkHttpClient.Builder()
                .readTimeout(
                    DEFAULT_TIMEOUT.toLong(),
                    TimeUnit.MILLISECONDS
                )
                .connectTimeout(
                    DEFAULT_TIMEOUT.toLong(),
                    TimeUnit.MILLISECONDS
                )
                .addInterceptor(getLogInterceptor())
//                .cookieJar(getCookie())
                .cache(getCache())
        }

    fun factory(): Retrofit {
        val okHttpClient = okHttpClientBuilder.build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    /**
     * 获取日志拦截器
     */
    private fun getLogInterceptor(): Interceptor? {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Logger.d(
                message
            )
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

//    /**
//     * 获取cookie持久化
//     */
//    private fun getCookie(): ClearableCookieJar {
//        return PersistentCookieJar(
//            SetCookieCache(),
//            SharedPrefsCookiePersistor(getContext())
//        )
//    }

    /**
     * 获取缓存方式
     */
    private fun getCache(): Cache {
        //缓存100Mb
        return Cache(
            File(AppContext.getContext()?.cacheDir, "cache"), 1024 * 1024 * 100
        )
    }
}