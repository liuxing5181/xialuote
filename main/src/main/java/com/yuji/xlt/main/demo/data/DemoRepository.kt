package com.yuji.xlt.main.demo.data

import com.yuji.xlt.ability.utils.Logger
import com.yuji.xlt.mvvm.http.RetrofitManager
import com.yuji.xlt.main.api.ApiService
import com.yuji.xlt.main.demo.data.model.UserModel
import com.yuji.xlt.mvvm.BaseRepository

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class DemoRepository() : BaseRepository() {

    fun getUserInfo(): MutableList<UserModel> {
        val mutableList: MutableList<UserModel> = ArrayList<UserModel>()
        mutableList.apply {
            add(UserModel("01", "Jack"))
            add(UserModel("02", "Json"))
        }
        return mutableList
    }

    suspend fun login(username: String, password: String) = withIO {
        RetrofitManager.getApiService(ApiService::class.java)
            .login(username, password)
            .data()
            .apply {
                Logger.i("TAG","1111111111111")
            }
    }
}