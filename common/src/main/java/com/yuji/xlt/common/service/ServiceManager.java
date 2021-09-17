package com.yuji.xlt.common.service;

import com.yuji.xlt.common.service.listener.IServiceRegister;
import com.yuji.xlt.common.service.listener.InitListener;

/**
 * NA<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
 */
public class ServiceManager {
    private ServiceHolder mServiceHolder = new ServiceHolder();

    public void init(InitListener listener) {
        listener.registerService(new IServiceRegister() {
            @Override
            public void registerComponent(Class<? extends IService> apiService, String clazzFull) {
                mServiceHolder.registerService(apiService, clazzFull);
            }

            @Override
            public void registerComponent(String apiService, String clazzFull) {
                mServiceHolder.registerService(apiService, clazzFull);
            }
        });
    }

    /**
     * 获得组件服务
     *
     * @param serviceClazz 服务类
     * @param <S>          服务类型
     * @return 组件服务器
     */
    public <S extends IService> S getService(Class<S> serviceClazz) {
        return (S) mServiceHolder.getService(serviceClazz);
    }

    /**
     * clear
     */
    public void clear() {
        mServiceHolder.clear();
    }
}
