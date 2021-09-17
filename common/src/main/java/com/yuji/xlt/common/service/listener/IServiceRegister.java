package com.yuji.xlt.common.service.listener;

import com.yuji.xlt.common.service.IService;

/**
 * 组件注册器<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
 */
public interface IServiceRegister {
    /**
     * 注册组件
     *
     * @param apiService 组件 对外暴露
     * @param clazzFull  组件类全量名
     */
    void registerComponent(Class<? extends IService> apiService, String clazzFull);

    /**
     * 注册组件
     *
     * @param apiService 组件api全量名
     * @param clazzFull  组件实现全量名
     */
    void registerComponent(String apiService, String clazzFull);
}
