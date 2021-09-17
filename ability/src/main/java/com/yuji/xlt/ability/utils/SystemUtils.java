/*
 * Copyright (c) Wondertek Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.yuji.xlt.ability.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import androidx.annotation.RequiresApi;

import com.yuji.xlt.ability.AppContext;

/**
 * 文件描述
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
 */
public class SystemUtils {
    private static final String TAG = "SystemUtils";

    private static final int INVALID_FLAGS = -1;

    private static final int TOP_RUNNING_TASK = 0;

    private static final int TASKS_MAX_NUM = 1;

    private SystemUtils() {
    }

    public static <T> T getSysService(String name, Class<T> clazz) {
        return getSysService(AppContext.INSTANCE.getContext(), name, clazz);
    }

    public static <T> T getSysService(Context context, String name, Class<T> clazz) {
        if (!TextUtils.isEmpty(name) && clazz != null && context != null) {
            Object obj = context.getSystemService(name);
            return clazz.isInstance(obj) ? (T) obj : null;
        } else {
            return null;
        }
    }

    public static void openWifiOrDataSettings(Context context) {
        openWifiOrDataSettings(context, -1);
    }

    public static void openWifiOrDataSettings(Context context, int flags) {

    }

    public static boolean isEnableAccelerometerRotation() {
        return Settings.System.getInt(AppContext.INSTANCE.getContext().getContentResolver(), "accelerometer_rotation", 0) == 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static int getDisplayWidth(Activity activity) {
        int width = 0;
        DisplayMetrics metric = new DisplayMetrics();
        if (metric != null) {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(metric);
            width = metric.widthPixels; // 宽度（PX）
        }
        return width;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static int getDisplayHeight(Activity activity) {
        int height = 0;
        DisplayMetrics metric = new DisplayMetrics();
        if (metric != null) {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(metric);
            height = metric.heightPixels; // 高度（PX）
        }
        return height;
    }
}
