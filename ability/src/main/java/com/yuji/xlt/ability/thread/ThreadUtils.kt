package com.yuji.xlt.ability.thread

import android.os.Handler
import android.os.Looper
import android.os.Message
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/24]
 * @since V1.0.0
 */
object ThreadUtils {
    /**
     * CPU 个数
     */
    private val CPU_COUNT = Runtime.getRuntime().availableProcessors()

    /**
     * 主线程 Handler
     */
    private val MAIN_HANDLER = Handler(Looper.getMainLooper())

    /**
     * 线程池大小
     */
    private const val POOL_SIZE = 2

    /**
     * 普通的线程组池大小：1. （CPU个数 -1） 和 4 取最小值得到值 A 2. 值 A 和 2 取最大值，保证不低于两组
     */
    private val NORMAL_GROUP_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4))

    /**
     * 创建线程池。线程池的"最大池大小"和"核心池大小"都为1(THREADS_SIZE)，"线程池"的阻塞队列容量为1(CAPACITY)。
     */
    private val POLL: ThreadPoolExecutor? = ThreadPoolExecutor(
        POOL_SIZE, POOL_SIZE, 20L,
        TimeUnit.SECONDS, ArrayBlockingQueue(1)
    )

    private fun ThreadUtils() {}

    /**
     * 提交任务执行
     */
    fun execute(runnable: Runnable?) {
        POLL!!.execute(runnable)
    }

    /**
     * 关闭线程池
     */
    fun unInit(poolName: String?) {
        if (POLL == null || POLL.isShutdown) {
            return
        }
        POLL.shutdownNow()
    }

    /**
     * 主线程执行
     *
     * @param runnable 执行接口
     * @return 取消接口
     */
    fun postMain(runnable: Runnable?) {
        MAIN_HANDLER.post(runnable)
        return PostCancelable.from(MAIN_HANDLER, runnable)
    }

    /**
     * 主线程延时执行
     *
     * @param runnable    执行接口
     * @param delayMillis 延时时间：毫秒
     * @return 取消接口
     */
    fun postMainDelay(runnable: Runnable?, delayMillis: Long) {
        MAIN_HANDLER.postDelayed(runnable, delayMillis)
        return PostCancelable.from(MAIN_HANDLER, runnable)
    }


    fun sendEmptyMessage(what: Int) {
        MAIN_HANDLER.sendEmptyMessage(what)
    }

    fun sendEmptyMessageDelay(what: Int, delay: Long) {
        MAIN_HANDLER.sendEmptyMessageDelayed(what, delay)
    }

    fun sendMessage(message: Message?) {
        MAIN_HANDLER.sendMessage(message)
    }

    fun sendMessageDelay(message: Message?, delay: Long) {
        MAIN_HANDLER.sendMessageDelayed(message, delay)
    }

    fun removeMessages(message: Int) {
        MAIN_HANDLER.removeMessages(message)
    }

    fun removeCallbacks(callback: Runnable?) {
        MAIN_HANDLER.removeCallbacks(callback)
    }
}