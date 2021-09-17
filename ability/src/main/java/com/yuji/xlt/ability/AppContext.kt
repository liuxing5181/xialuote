package com.yuji.xlt.ability

import android.annotation.SuppressLint
import android.content.Context

/**
 * <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/24]
 * @since V1.0.0
 */
@SuppressLint("StaticFieldLeak")
object AppContext {
    private const val TAG = "AppContext"

    private var mContext: Context? = null

    fun init(context: Context?) {
        mContext = context
    }

    fun getContext(): Context? {
        return mContext
    }

    fun getFileDirPath(): String? {
        return if (mContext != null && mContext!!.filesDir != null) mContext!!.filesDir.path else ""
    }

}