package com.yuji.xlt.mvvm

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/30]
 * @since V1.0.0
 */
abstract class BaseBdFragment<BD : ViewDataBinding> : Fragment() {

    /**
     * 开放给外部使用
     */
    lateinit var mContext: Context
    private lateinit var binding: BD
    private var mBinding: ViewDataBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //由于同一个fragment对象可能被activity attach多次(比如viewPager+PagerStateAdapter中)
        //所以fragmentViewModel不能放在onCreateView初始化，否则会产生多个fragmentViewModel
        initFragmentViewModel()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        // 必须要在Activity与Fragment绑定后，因为如果Fragment可能获取的是Activity中ViewModel
        // 必须在onCreateView之前初始化viewModel，因为onCreateView中需要通过ViewModel与DataBinding绑定
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getLayoutId()?.let {
            //获取ViewDataBinding
            binding = DataBindingUtil.inflate(inflater, it, container, false)
            //将ViewDataBinding生命周期与Fragment绑定
            binding.lifecycleOwner = viewLifecycleOwner
            return binding.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)
        //observe一定要在初始化最后，因为observe会收到黏性事件，随后对ui做处理
        observe()
        onClick()
    }

    /**
     * 初始化viewModel
     * 之所以没有设计为抽象，是因为部分简单activity可能不需要viewModel
     * observe同理
     */
    open fun initViewModel() {

    }

    open fun initFragmentViewModel() {

    }

    /**
     * 注册观察者
     */
    open fun observe() {

    }

    /**
     * 获取指定类型的VM实例 activity/fragment获取vm实例则跟随其生命周期
     *
     * @param modelClass VM的类实例
     * @param <T>            VM类型
     * @return VM对象
     * */
    protected fun <T : ViewModel?> getViewModel(modelClass: Class<T>): T {
        return if (null != activity) {
            ViewModelProvider(requireActivity()).get(modelClass)
        } else {
            ViewModelProvider(this).get(modelClass)
        }
    }

    protected fun <T : ViewModel> getViewModel(
        factory: ViewModelProvider.Factory,
        modelClass: Class<T>
    ): T {
        return if (null != activity) {
            ViewModelProvider(requireActivity(), factory).get(modelClass)
        } else {
            ViewModelProvider(this, factory).get(modelClass)
        }
    }

    /**
     * 点击事件
     */
    open fun onClick() {

    }

    /**
     * 初始化View以及事件
     */
    open fun initView() {

    }

    /**
     * 加载数据
     */
    open fun loadData() {

    }


    /**
     * 初始化入口
     */
    abstract fun init(savedInstanceState: Bundle?)

    /**
     * 获取layout布局
     */
    abstract fun getLayoutId(): Int?
}