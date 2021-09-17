package com.yuji.xlt.main.aidl

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import com.yuji.xlt.ability.utils.Logger
import com.yuji.xlt.main.R
import com.yuji.xlt.main.databinding.ActivityTestAidlBinding
import com.yuji.xlt.mvvm.BaseActivity

/**
 * 测试调用aidl接口<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/9/15]
 * @since V1.0.0
 */
class TestAidlActivity : BaseActivity<ActivityTestAidlBinding>() {
    companion object {
        const val TAG: String = "TestAidlActivity";
    }

    override fun getLayoutId(): Int? {
        return R.layout.activity_test_aidl
    }

    override fun init(savedInstanceState: Bundle?) {
        initListener()

    }

    private fun initListener() {
        mBinding.button.setOnClickListener {
            // 参数为服务端的Service的action的name参数的值
            val intent = Intent("com.yuji.libai.aidl.ILbAidlInterface")
            // 参数为服务端的包名
            intent.setPackage("com.yuji.libai");
            // 绑定Service
            bindService(intent, MyConnection(), BIND_AUTO_CREATE);
        }
    }

    class MyConnection : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//             获得服务端的RemoteInterface的对象。
            val aidlService = com.yuji.libai.aidl.ILbAidlInterface.Stub.asInterface(service)
            // 调用服务端的方法
            try {
                val str = aidlService?.appName
                Logger.i(TAG, "onServiceConnected ,str = $str")
            } catch (e: RemoteException) {
                e.printStackTrace();
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Logger.i(TAG, "onServiceDisconnected ,name = $name")
        }

    }
}


