package com.yuji.xlt.main.demo.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuji.xlt.main.demo.data.DemoRepository

import com.yuji.xlt.main.demo.data.model.UserModel
import com.yuji.xlt.mvvm.BaseViewModel

class DemoViewModel(private val demoRepository: DemoRepository) : BaseViewModel() {


    private val _loginResult = MutableLiveData<MutableList<UserModel>>()
    val loginResult: LiveData<MutableList<UserModel>> = _loginResult

    val loginLiveData = MutableLiveData<UserModel>()

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = demoRepository.getUserInfo()

        _loginResult.value = result

        launch {
            loginLiveData.value = demoRepository.login("", "")
        }
    }

}