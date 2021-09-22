package com.yuji.xlt.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yuji.xlt.ability.utils.color.ColorUtils
import com.yuji.xlt.ability.utils.color.StatusUtils

/**
 * <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/30]
 * @since V1.0.0
 */
abstract class BaseActivity<BD : ViewDataBinding> : AppCompatActivity() {
    private var mActivityProvider: ViewModelProvider? = null
    lateinit var mBinding: BD
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLayoutId()?.let { mBinding = DataBindingUtil.setContentView(this, it) }
        initViewModel()
        init(savedInstanceState)
        loadData()
    }

    /**
     * 获取指定类型的VM实例，跟随activity生命周期
     * @param modelClass VM的类实例
     * @return VM对象
     */
    protected fun <T : ViewModel?> getViewModel(modelClass: Class<T>): T? {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(this)
        }
        return mActivityProvider?.get(modelClass)
    }

    protected fun <T : ViewModel> getViewModel(
        factory: ViewModelProvider.Factory,
        modelClass: Class<T>,
    ): T {
        return ViewModelProvider(this, factory)
            .get(modelClass)
    }

    /**
     * 获取layout布局
     */
    abstract fun getLayoutId(): Int?

    /**
     * activity入口
     */
    abstract fun init(savedInstanceState: Bundle?)

    /**
     * 初始化viewModel
     * 之所以没有设计为抽象，是因为部分简单activity可能不需要viewModel
     */
    open fun initViewModel() {
    }

    open fun loadData() {}
}
