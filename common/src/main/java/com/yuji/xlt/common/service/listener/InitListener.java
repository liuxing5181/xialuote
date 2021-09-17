package com.yuji.xlt.common.service.listener;

/**
 * NA<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
 */
public interface InitListener {
    /**
     * 注册组件
     *
     * @param register 组件注册器
     */
    void registerService(IServiceRegister register);
}
