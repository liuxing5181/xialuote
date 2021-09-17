package com.yuji.xlt.mvvm

import android.os.Bundle
import androidx.databinding.ViewDataBinding

/**
 * 基于androidx 实现懒加载<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/30]
 * @since V1.0.0
 */
abstract class BaseLazyFragment<BD : ViewDataBinding> : BaseBdFragment<BD>() {

    private var isLoaded = false
    override fun onResume() {
        super.onResume()
        //增加了Fragment是否可见的判断
        if (!isLoaded && !isHidden) {
            lazyInit()
            isLoaded = true
        }
    }

    override fun init(savedInstanceState: Bundle?) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    abstract fun lazyInit()

}