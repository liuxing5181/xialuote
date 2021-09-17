package com.yuji.xlt.ability.ext

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.StringRes
import com.yuji.xlt.ability.AppContext

/**
 * des Toast工具类
 */

fun Context.toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    if (TextUtils.isEmpty(content)) return
    Toast.makeText(this, content, duration).apply {
        show()
    }
}

fun Context.toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(id), duration)
}


fun toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    if (TextUtils.isEmpty(content)) return
    AppContext.getContext()?.toast(content, duration)
}

fun toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    AppContext.getContext()?.toast(id, duration)
}

fun longToast(content: String, duration: Int = Toast.LENGTH_LONG) {
    if (TextUtils.isEmpty(content)) return
    AppContext.getContext()?.toast(content, duration)
}

fun longToast(@StringRes id: Int, duration: Int = Toast.LENGTH_LONG) {
    AppContext.getContext()?.toast(id, duration)
}


