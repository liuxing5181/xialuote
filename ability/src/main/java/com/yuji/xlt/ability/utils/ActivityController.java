/*
 * Copyright (c) Wondertek Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.yuji.xlt.ability.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import com.yuji.xlt.ability.AppContext;

import java.util.ArrayList;
import java.util.List;

/**
 * [Activity界面管理工具类]<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
 */
public class ActivityController {
    private static final String TAG = "ActivityController";

    /**
     * 主界面的类名[全路径]
     */
    private static final String MAIN_ACTIVITY = "";

    private static List<FragmentActivity> sActivities = new ArrayList<>();

    private static long sTotalMemory = 0L;

    /**
     * 进制单位B,KB,M,G
     */
    private static int MEM_UNIT = 1024;

    /**
     * 2G换算为M
     */
    private static int MEM_2G_PRE_M = 2 * MEM_UNIT;

    private static int MEM_MB = MEM_UNIT * MEM_UNIT;

    /**
     * 添加activity
     *
     * @param fragmentActivity fragmentActivity
     */
    public static void addActivity(FragmentActivity fragmentActivity) {
        Logger.i(TAG, "addActivity activity: " + fragmentActivity);
        sActivities.remove(fragmentActivity);
        sActivities.add(fragmentActivity);
    }

    /**
     * 获取当前activity
     *
     * @return fragmentActivity
     */
    public static FragmentActivity getCurrentActivity() {
        if (ArrayUtils.isNotEmpty(sActivities)) {
            return ArrayUtils.getListElement(sActivities, sActivities.size() - 1);
        }

        return null;
    }

    /**
     * 销毁单个activity
     *
     * @param fragmentActivity fragmentActivity
     * @param isFinished       是否finished
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void removeActivity(FragmentActivity fragmentActivity, boolean isFinished) {
       Logger.i(TAG, "removeActivity activity: " + fragmentActivity);
        if (sActivities.contains(fragmentActivity)) {
            sActivities.remove(fragmentActivity);
            if (isFinished) {
                if (fragmentActivity.isDestroyed() || fragmentActivity.isFinishing()) {
                    return;
                }

                fragmentActivity.finish();
            }
        }
    }

    /**
     * 获取所有的activity
     *
     * @return activities
     */
    public static List<FragmentActivity> getsActivities() {
        return sActivities;
    }

    /**
     * 销毁所有activity除了主界面
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void removeAllActivitiesExceptMain() {
        Logger.i(TAG, "removeAllActivities");
        if (isFewTotalMemory()) {
            for (FragmentActivity fragmentActivity : sActivities) {
                Logger.i(TAG, "removeAllActivities activity: " + fragmentActivity);
                if (fragmentActivity.isDestroyed() || fragmentActivity.isFinishing()) {
                    return;
                }

                if (!isMainActivity(fragmentActivity)) {
                    fragmentActivity.finish();
                }
            }
            Logger.i(TAG, "removeAllActivities end");
        }
    }

    /**
     * 销毁所有activity
     */
    @SuppressLint("NewApi")
    public static void removeAllActivities() {
        Logger.i(TAG, "removeAllActivities");
        if (isFewTotalMemory()) {
            for (FragmentActivity fragmentActivity : sActivities) {
                Logger.i(TAG, "removeAllActivities activity: " + fragmentActivity);
                if (fragmentActivity.isDestroyed() || fragmentActivity.isFinishing()) {
                    return;
                }

                fragmentActivity.finish();
            }
            sActivities.clear();
            Logger.i(TAG, "removeAllActivities end");
        }
    }

    /**
     * 销毁除自身外的所有activity
     *
     * @param activity 当前activity
     */
    public static void removeAllActivitiesExceptSelf(FragmentActivity activity) {
        Logger.i(TAG, "removeAllActivitiesExceptSelf ");
        for (FragmentActivity fragmentActivity : sActivities) {
            if (fragmentActivity != activity) {
                fragmentActivity.finish();
            }
        }
        Logger.i(TAG, "removeAllActivitiesExceptSelf end");
    }

    /**
     * 是否处于低内存状态[设备内存小于2G]
     *
     * @return true or false
     */
    private static boolean isFewTotalMemory() {
        return getTotalMemory() < MEM_2G_PRE_M;
    }

    /**
     * 获取设备内存大小
     *
     * @return 设备内存大小 单位M
     */
    private static long getTotalMemory() {
        if (MathUtils.isEqual(sTotalMemory, 0L) && null != AppContext.INSTANCE.getContext()) {
            ActivityManager activityManager = CastUtils
                    .cast(AppContext.INSTANCE.getContext().getSystemService(Context.ACTIVITY_SERVICE), ActivityManager.class);
            if (null == activityManager) {
                return sTotalMemory;
            }

            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            Logger.i(TAG, "getTotalMemory memInfo.totalMem: " + memoryInfo.totalMem);
            sTotalMemory = memoryInfo.totalMem / MEM_MB;
        }
        Logger.i(TAG, "getTotalMemory: " + sTotalMemory);
        return sTotalMemory;
    }

    /**
     * 判断是否是主界面
     *
     * @param fragmentActivity fragmentActivity
     * @return true or false
     */
    private static boolean isMainActivity(FragmentActivity fragmentActivity) {
        Class<?> componentClass = ReflectionUtils.getClass(MAIN_ACTIVITY);
        if (null == componentClass) {
            Logger.e(TAG, "isMainActivity componentClass is null");
            return false;
        }

        Logger.i(TAG, "isMainActivity activity.getClass(): " + fragmentActivity.getClass().getName());
        Logger.i(TAG, "isMainActivity componentClass: " + componentClass.getName());
        return StringUtils.isEqualIgnoreCase(fragmentActivity.getClass().getName(), componentClass.getName());
    }

    /**
     * app是否退出
     *
     * @return true 退出 false 未退出
     */
    public static boolean isAppExit() {
        return getsActivities().isEmpty();
    }

    /**
     * 获取当前应用包名
     *
     * @param context 上下文
     * @return 包名
     */
    public static String getPackageName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.packageName;
        } catch (Exception e) {
            Logger.e(TAG, "getPackageName error", e);
        }
        return "";
    }
}
