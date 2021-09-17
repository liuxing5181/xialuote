package com.yuji.xlt.common.router.models;

import android.os.Bundle;

/**
 * 页面跳转数据封装<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/28]
 * @since V1.0.0
 */
public class UriRequest {
    /**
     * 数据
     */
    private Bundle bundle;
    /**
     * 目标界面
     */
    private Class<?> targetClass;
    /**
     * 目标界面的名称
     */
    private String targetClassName;

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public String getTargetClassName() {
        return targetClassName;
    }

    public void setTargetClassName(String targetClassName) {
        this.targetClassName = targetClassName;
    }
}
