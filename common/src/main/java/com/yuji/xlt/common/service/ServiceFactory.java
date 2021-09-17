package com.yuji.xlt.common.service;

import com.yuji.xlt.common.service.listener.InitListener;

/**
 * 管理service工厂类-反射<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
 */
public class ServiceFactory {
    private static ServiceManager manager = new ServiceManager();

    /**
     * 初始化接口：同步
     *
     * @param listener 初始化监听器
     */
    public static void init(InitListener listener) {
        manager.init(listener);
    }

    /**
     * 获得组件服务
     *
     * @param serviceClazz 服务类
     * @param <S>          服务类型
     * @return 组件服务器
     */
    public static <S extends IService> S getService(Class<S> serviceClazz) {
        return manager.getService(serviceClazz);
    }

}
