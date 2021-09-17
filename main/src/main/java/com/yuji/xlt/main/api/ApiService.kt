package com.yuji.xlt.main.api

import com.yuji.xlt.main.demo.data.model.UserModel
import com.yuji.xlt.mvvm.http.ApiResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/30]
 * @since V1.0.0
 */
open interface ApiService {
    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(@Field("username") username: String,
                      @Field("password") password: String): ApiResponse<UserModel>
}