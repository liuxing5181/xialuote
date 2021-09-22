package com.yuji.xlt.main.ui

import android.Manifest
import android.content.Intent
import android.os.Bundle
import com.yuji.xlt.ability.utils.Logger
import com.yuji.xlt.main.R
import com.yuji.xlt.main.databinding.ActivitySplashBinding
import com.yuji.xlt.main.demo.DemoActivity
import com.yuji.xlt.mvvm.BaseActivity
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.util.concurrent.TimeUnit

/**
 * <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/30]
 * @since V1.0.0
 */
class SplashActivity : BaseActivity<ActivitySplashBinding>(), EasyPermissions.PermissionCallbacks {
    private var disposable: Disposable? = null

    companion object {
        private const val WRITE_EXTERNAL_STORAGE = 100
    }

    override fun getLayoutId(): Int? {
        return R.layout.activity_splash
    }

    override fun init(savedInstanceState: Bundle?) {
        Logger.d("SplashActivity", "init -->")
        requestPermission()
    }

    @AfterPermissionGranted(WRITE_EXTERNAL_STORAGE)
    private fun LocationAndCallPermission() {
        val perms = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        //数组中权限都已申请
        if (EasyPermissions.hasPermissions(this, *perms)) {
            startIntent()
        } else {
            EasyPermissions.requestPermissions(this, "请求写入权限", WRITE_EXTERNAL_STORAGE, *perms)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    /**
     * 权限申请成功
     */
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Logger.i("SplashActivity", "onPermissionsGranted requestCode = $requestCode")
        startIntent()
    }

    /**
     * 权限申请失败
     */
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Logger.i("SplashActivity", "onPermissionsDenied requestCode = $requestCode")
    }

    /**
     * 申请权限
     */
    private fun requestPermission() {
        //已申请
        val perms = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (EasyPermissions.hasPermissions(this, *perms)) {
            startIntent()
        } else {
            LocationAndCallPermission()
        }
    }

    /**
     * 开始倒计时跳转
     */
    private fun startIntent() {
        disposable = Observable.timer(2000, TimeUnit.MILLISECONDS)
            .subscribe {
                startActivity(Intent(this, DemoActivity::class.java))
                finish()
            }
    }
}