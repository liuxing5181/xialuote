package com.yuji.common_x.text

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.yuji.common_x.glide.BitmapDispatcher
import com.yuji.common_x.glide.BitmapRequest
import com.yuji.common_x.glide.RequestManagerFragment
import com.yuji.common_x.glide.listener.LifecycleListener
import com.yuji.xlt.ability.utils.Logger
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingDeque

/**
 * @author W4255
 * @version [V1.0.0.000, 2021/1/27 0:13]
 * @Description: NA
 */
class RequestManager1 {
    /**
     * 上下文
     */
    private var mContext: Context? = null

    /**
     * 管理着请求队列
     */
    private val requestQueue: BlockingQueue<BitmapRequest> = LinkedBlockingDeque()

    /**
     * 每个请求对应的线程
     */
    private var bitmapDispatchers: Array<BitmapDispatcher?>? = null

    private constructor() {
        Logger.i("RequestManager() -->")
        stopThread()
        createBitmapDispatcher()
    }

    /**
     * 代表无法管理生命周期
     *
     * @param context
     */
    constructor(context: Context?) {
        mContext = context
    }

    constructor(activity: FragmentActivity) {
        mContext = activity
        Logger.i("RequestManager , start  isCreateFragment = " + isCreateFragment)
        if (isCreateFragment) {
            return
        }
        val fragmentManager = activity.supportFragmentManager
        var fragment = fragmentManager.findFragmentByTag(FRAGMENT_ACTIVITY_NAME)
        if (fragment == null) {
            isCreateFragment = true
            Logger.i("RequestManager , fragment++ isCreateFragment = " + isCreateFragment)
            fragment = RequestManagerFragment(lifecycleListener)
            //添加到 FragmentManager 中
            val transaction = fragmentManager.beginTransaction()
            transaction.add(fragment, FRAGMENT_ACTIVITY_NAME)
            transaction.commitAllowingStateLoss()
        }
    }

    constructor(fragment: Fragment) {
        mContext = fragment.activity
    }

    fun with(): BitmapRequest {
        return BitmapRequest(mContext)
    }

    /**
     * 加入队列
     *
     * @param request
     */
    fun addBitmapRequest(request: BitmapRequest) {
        if (!requestQueue.contains(request)) {
            requestQueue.add(request)
        }
    }

    /**
     * threadCount = Runtime.getRuntime().availableProcessors();
     */
    private fun createBitmapDispatcher() {
        Logger.i("createBitmapDispatcher() -->")
        val threadCount = 10
        bitmapDispatchers = arrayOfNulls(threadCount)
        for (i in 0 until threadCount) {
            val bitmapDispatcher = BitmapDispatcher(requestQueue)
            bitmapDispatcher.start()
            bitmapDispatchers!![i] = bitmapDispatcher
        }
    }

    /**
     * 关闭线程
     */
    fun stopThread() {
        Logger.i("stopThread() -->")
        if (bitmapDispatchers == null || bitmapDispatchers!!.size <= 0) {
            return
        }
        for (i in bitmapDispatchers!!.indices) {
            val bitmapDispatcher = bitmapDispatchers!![i]
            if (!bitmapDispatcher!!.isInterrupted) {
                bitmapDispatcher.interrupt()
            }
        }
    }

    private val lifecycleListener: LifecycleListener = object : LifecycleListener {
        override fun onStart() {
            Logger.d("onStart() -->")
        }

        override fun onStop() {
            Logger.d("onStop() -->")
        }

        override fun onDestroy() {
            Logger.d("onDestroy() -->")
            stopThread()
            isCreateFragment = false
        }
    }

    companion object {
        private const val FRAGMENT_ACTIVITY_NAME = "Fragment_Activity_Name"

        @Volatile
        var instance: RequestManager1? = null
            get() {
                if (field == null) {
                    synchronized(RequestManager1::class.java) {
                        if (field == null) {
                            field = RequestManager1()
                        }
                    }
                }
                return field
            }
            private set
        private var isCreateFragment = false
    }
}