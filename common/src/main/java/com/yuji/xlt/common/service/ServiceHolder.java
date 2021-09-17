package com.yuji.xlt.common.service;

import com.yuji.xlt.ability.utils.CastUtils;
import com.yuji.xlt.ability.utils.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Service存储器<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
 */
public class ServiceHolder {
    private Map<String, IService> mServiceMap = new HashMap<>();

    /**
     * 注册组件
     *
     * @param apiService
     * @param serviceImpl
     */
    public void registerService(Class<? extends IService> apiService, String serviceImpl) {
        if (apiService == null) {
            Logger.i("registerService failed, registerService is null");
            return;
        }
        if (serviceImpl == null) {
            Logger.i("registerService failed, serviceImpl is null");
            return;
        }
        Class clazz = getClass(serviceImpl);
        IService service = CastUtils.cast(newInstance(clazz), IService.class);
        if (service == null) {
            Logger.i("createServiceInstance failed, please check serviceImpl");
            return;
        }
        Logger.w("ServiceHolder", "registerService apiService = " + apiService + ";clazz = " + service);
        mServiceMap.put(getKey(apiService), service);
    }

    /**
     * 注册组件
     *
     * @param apiService
     * @param serviceImpl
     */
    public void registerService(String apiService, String serviceImpl) {
        if (apiService == null) {
            Logger.i("registerService failed, registerService is null");
            return;
        }
        if (serviceImpl == null) {
            Logger.i("registerService failed, serviceImpl is null");
            return;
        }
        Class clazz = getClass(serviceImpl);
        IService service = CastUtils.cast(newInstance(clazz), IService.class);
        if (service == null) {
            Logger.i("createServiceInstance failed, please check serviceImpl");
            return;
        }
        Logger.w("ServiceHolder", "registerService apiService = " + apiService + ";clazz = " + service);
        mServiceMap.put(apiService, service);
    }

    /**
     * 获得组件服务
     *
     * @param serviceClazzApi 服务类
     * @return 组件服务器
     */
    public IService getService(Class<? extends IService> serviceClazzApi) {
        if (serviceClazzApi == null) {
            Logger.i("getService failed, please check serviceClazzApi");
            return null;
        }
        IService service = mServiceMap.get(getKey(serviceClazzApi));
        if (service == null) {
            Logger.i("getService failed, service has not init");
        }
        return service;
    }

    /**
     * 清除map
     */
    public void clear() {
        if (mServiceMap != null) {
            mServiceMap.clear();
            mServiceMap = null;
        }
    }

    /**
     * 通过实例获取全量类名
     *
     * @param serviceClazzApi IService
     * @return com.wd.user.mediator.service.user.IService
     */
    private String getKey(Class<? extends IService> serviceClazzApi) {
        return serviceClazzApi.getName();
    }

    /**
     * 获得类实例
     *
     * @param clazz 类
     * @return 类实例
     */
    public Object newInstance(Class<?> clazz) {
        if (clazz != null) {
            try {
                return clazz.newInstance();
            } catch (InstantiationException e1) {
                Logger.e("newInstance InstantiationException " + e1);
            } catch (IllegalAccessException e2) {
                Logger.e("newInstance IllegalAccessException " + e2);
            }
        }
        return null;
    }

    /**
     * 获取类
     *
     * @param className 类名
     * @return 类
     */
    public static Class<?> getClass(String className) {
        return getClass(className, false);
    }

    private static Class<?> getClass(String className, boolean isSilent) {
        if (null == className) {
            return null;
        } else {
            Class clazz = null;
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e1) {
                if (!isSilent) {
                    Logger.e("ClassNotFoundException：" + e1);
                }
            } catch (LinkageError e2) {
                if (!isSilent) {
                    Logger.e("an error occurs during linkage");
                }
            }
            return clazz;
        }
    }
}
