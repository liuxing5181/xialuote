package com.yuji.xlt.main.ui.page

import android.os.Bundle
import com.yuji.xlt.ability.utils.Logger
import com.yuji.xlt.common.router.IntentConstant
import com.yuji.xlt.common.router.PageConstant
import com.yuji.xlt.main.R
import com.yuji.xlt.main.databinding.ActivityCurrencyBinding
import com.yuji.xlt.main.ui.page.fragment.EmptyFragment
import com.yuji.xlt.mvvm.BaseActivity
import com.yuji.xlt.mvvm.BaseBdFragment

/**
 * 通用的Activity<用来加载fragment二级页面><BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/18]
 * @since V1.0.0
 */
class CurrencyActivity : BaseActivity<ActivityCurrencyBinding>() {
    companion object {
        private const val TAG: String = "CurrencyActivity"
    }

    private lateinit var mBaseFragment: BaseBdFragment<*>
    private var pageType: String? = null
    private var userId: String? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_currency
    }

    override fun init(savedInstanceState: Bundle?) {
        initIntent()
        addFragment()
    }


    private fun initIntent() {
        val intent = intent
        val bundle = intent.extras
        bundle?.run {
            pageType = getString(IntentConstant.PAGE_TYPE)
            userId = getString(IntentConstant.USER_ID)
        }
        Logger.i(TAG, "pageType = $pageType,userid = $userId")

    }

    private fun addFragment() {
        mBaseFragment = createFragment()
        val data = Bundle()
        data.putString(IntentConstant.USER_ID, userId)
        mBaseFragment.arguments = data
        if (!mBaseFragment.isAdded) {
            supportFragmentManager.beginTransaction().replace(R.id.container, mBaseFragment)
                .commitNow()
        }
    }

    private fun createFragment(): BaseBdFragment<*> {
        //TODO 加载不同的fragment
        return when (pageType) {
            PageConstant.PAGE_MESSAGE_NOTICE -> {
                EmptyFragment.instance
            }
            else -> {
                EmptyFragment.instance
            }
        }
    }

}