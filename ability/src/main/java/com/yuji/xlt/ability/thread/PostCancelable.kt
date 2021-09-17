package com.yuji.xlt.ability.thread

import android.os.Handler
import com.yuji.xlt.ability.utils.Logger

/**
 * <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/24]
 * @since V1.0.0
 */
object PostCancelable {
    private const val TAG = "PostCancelable"

    private var runnable: Runnable? = null

    private var handler: Handler? = null

    @Volatile
    private var canceled = false

    private fun PostCancelable(h: Handler?, r: Runnable?) {
        handler = h
        runnable = r
    }

    fun from(h: Handler?, r: Runnable?) {
        return PostCancelable(h, r)
    }

    fun cancel() {
        if (runnable != null && handler != null) {
            handler?.removeCallbacks(runnable)
            canceled = true
        } else {
            Logger.e(TAG, "runnable or handler not set, cannot cancel")
        }
    }

    fun isCanceled(): Boolean {
        return canceled
    }
}