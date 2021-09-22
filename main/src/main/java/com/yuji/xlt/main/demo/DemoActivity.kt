package com.yuji.xlt.main.demo

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.yuji.xlt.ability.ext.toast
import com.yuji.xlt.ability.utils.Logger
import com.yuji.xlt.main.R
import com.yuji.xlt.main.databinding.ActivityLoginBinding
import com.yuji.xlt.main.demo.vm.DemoViewModel
import com.yuji.xlt.main.demo.vm.DemoViewModelFactory
import com.yuji.xlt.mvvm.BaseActivity

class DemoActivity : BaseActivity<ActivityLoginBinding>() {

    companion object {
        private const val TAG: String = "DemoActivity"
    }

    private lateinit var demoViewModel: DemoViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun init(savedInstanceState: Bundle?) {
        Logger.d(TAG, "init -->")
        mBinding.login.setOnClickListener {
            Logger.d(TAG, "init -->")
            demoViewModel.login("", "")
        }
    }

    override fun initViewModel() {
        Logger.d(TAG, "initViewModel -->")
        demoViewModel = getViewModel(DemoViewModelFactory(), DemoViewModel::class.java)

        demoViewModel.loginResult.observe(this@DemoActivity, Observer {
            val loginResult = it ?: return@Observer
            Logger.e(TAG, "result = $loginResult")
            toast("$loginResult",Toast.LENGTH_LONG)
        })

        demoViewModel.loginLiveData.observe(this, Observer {
            Logger.w(TAG, "result ${it.displayName}")
        })

        demoViewModel.errorLiveData.observe(this, Observer {
            Logger.w(TAG, "异常:${it.errorMessage}")
        })
    }

    override fun loadData() {
        Logger.d(TAG, "loadData -->")
    }

}

