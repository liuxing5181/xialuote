package com.yuji.xlt.ability.callback

/**
 * <取消接口>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
</取消接口> */
interface Cancelable {
    /**
     * 取消
     */
    fun cancel()

    /**
     * 是否已经取消
     *
     * @return true: 取消，false：未取消
     */
    val isCanceled: Boolean
}