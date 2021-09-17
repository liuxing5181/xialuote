/*
 * Copyright (c) Wondertek Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.yuji.xlt.ability.utils;

/**
 * <类型转换工具类>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
 */
public class CastUtils {
    private static final String TAG = "CastUtils";

    private CastUtils() {
    }

    public static int castToInt(Object obj) {
        return castToInt(obj, 0);
    }

    public static int castToInt(Object obj, int defaultValue) {
        Integer ret = (Integer) cast(obj, Integer.class);
        return ret == null ? defaultValue : ret;
    }

    public static boolean castToBoolean(Object obj) {
        return castToBoolean(obj, false);
    }

    public static boolean castToBoolean(Object obj, boolean defaultValue) {
        Boolean ret = (Boolean) cast(obj, Boolean.class);
        return ret == null ? defaultValue : ret;
    }

    public static String castToString(Object obj) {
        return castToString(obj, "");
    }

    public static String castToString(Object obj, String defaultValue) {
        String ret = (String) cast(obj, String.class);
        return ret == null ? defaultValue : ret;
    }

    public static <T> T cast(Object obj, Class<T> clz) {
        if (clz == null) {
            Logger.i(TAG, "class is null");
            return null;
        } else {
            return clz.isInstance(obj) ? (T) obj : null;
        }
    }

    public static <T> T cast(Object obj, T defaultValue) {
        if (defaultValue == null) {
            Logger.i(TAG, "defaultValue is null.");
            return null;
        } else if (obj == null) {
            Logger.i(TAG, "object is null.");
            return null;
        } else {
            return defaultValue.getClass() == obj.getClass() ? (T) obj : defaultValue;
        }
    }
}
