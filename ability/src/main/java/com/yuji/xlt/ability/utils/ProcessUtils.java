package com.yuji.xlt.ability.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

/**
 * 线程判断<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
 */
public class ProcessUtils {

    /**
     * 判断当前的进程是否是主进程
     *
     * @return true表示主进程
     */
    public static boolean isMainProcess(Context mContext, String pageName) {
        return isSpecialPrivateProcess(mContext, pageName, null);
    }

    /**
     * 判断当前的进程是否是特定私有进程
     *
     * @return true表示是特定私有进程
     */
    private static boolean isSpecialPrivateProcess(Context mContext, String pageName, String processSuffix) {
        int pid = android.os.Process.myPid();
        String processName = "";
        if (mContext != null) {
            ActivityManager manager =
                    SystemUtils.getSysService(mContext, Context.ACTIVITY_SERVICE, ActivityManager.class);
            if (manager != null && ArrayUtils.isNotEmpty(manager.getRunningAppProcesses())) {
                for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
                    if (process != null && process.pid == pid) {
                        processName = process.processName;
                        break;
                    }
                }
            }
        }
        String specialProcessName = pageName + ((processSuffix == null) ? "" : processSuffix);
        return TextUtils.equals(specialProcessName, processName);
    }
}
