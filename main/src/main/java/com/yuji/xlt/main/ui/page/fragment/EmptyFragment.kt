package com.yuji.xlt.main.ui.page.fragment

import android.os.Bundle
import com.yuji.xlt.ability.utils.Logger
import com.yuji.xlt.common.router.IntentConstant
import com.yuji.xlt.main.R
import com.yuji.xlt.main.databinding.FragmentEmptyBinding
import com.yuji.xlt.mvvm.BaseBdFragment

/**
 * 空页面<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/18]
 * @since V1.0.0
 */
class EmptyFragment : BaseBdFragment<FragmentEmptyBinding>() {
    private var userId: String? = null

    companion object {
        val instance: EmptyFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            EmptyFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_empty
    }

    override fun init(savedInstanceState: Bundle?) {
        initArguments()
    }

    private fun initArguments() {
        arguments?.let {
            userId = it.getString(IntentConstant.USER_ID)
        }
        Logger.i(tag, "userid = $userId")
    }
}