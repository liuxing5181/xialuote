package com.yuji.xlt.common.router;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.yuji.xlt.ability.utils.ActivityController;
import com.yuji.xlt.ability.utils.Logger;
import com.yuji.xlt.ability.utils.ReflectionUtils;
import com.yuji.xlt.ability.utils.StringUtils;
import com.yuji.xlt.common.router.models.UriRequest;

/**
 * <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/9/1]
 * @since V1.0.0
 */
public class RouterUtil {
    private static final String TAG = "RouterUtil";

    /**
     * 启动目标界面
     *
     * @param context    上下文
     * @param uriRequest uriRequest
     */
    public static void startActivity(Context context, UriRequest uriRequest) {
        if (null == context || null == uriRequest) {
            Logger.e(TAG, "startActivity, context or uriRequest is null");
            return;
        }
        // 显式启动目标界面
        if (null != uriRequest.getTargetClass()) {
            Logger.i(TAG, "startActivity, start by targetCls");
            goActivity(context, uriRequest);
            return;
        }
        // 通过反射启动目标界面
        if (StringUtils.isNotEmpty(uriRequest.getTargetClassName())) {
            Logger.i(TAG, "startActivity, start by targetClsName");
            Class<?> targetCls = ReflectionUtils.getClass(uriRequest.getTargetClassName());
            if (targetCls == null) {
                Logger.e(TAG, "startActivity, targetCls is null.");
                return;
            }

            uriRequest.setTargetClass(targetCls);
            goActivity(context, uriRequest);
            return;
        }
    }

    /**
     * 跳转目标界面【显式启动】
     *
     * @param context    上下文
     * @param uriRequest uriRequest
     */
    private static void goActivity(Context context, UriRequest uriRequest) {
        Intent intent = new Intent(context, uriRequest.getTargetClass());
        if (null != uriRequest.getBundle()) {
            intent.putExtras(uriRequest.getBundle());
        }
        goActivityFromCurActivity(context, intent);
    }

    /**
     * 从当前activity跳转到目标activity
     *
     * @param context 上下文
     * @param intent  执行动作
     * @return 是否执行成功
     */
    private static boolean goActivityFromCurActivity(Context context, Intent intent) {
        Activity topActivity = ActivityController.getCurrentActivity();
        if (!(context instanceof Activity && null != topActivity)) {
            Logger.i(TAG, "goActivityFromCurActivity, use topActivity");
            return safeStartActivity(topActivity, intent);
        }

        Logger.i(TAG, "goActivityFromCurActivity use application");
        return safeStartActivity(context, intent);
    }

    private static boolean safeStartActivity(Context context, Intent intent) {
        if (null == context) {
            Log.w(TAG, "safeStartActivity by context, context can not be null!");
            return false;
        } else if (null == intent) {
            Log.w(TAG, "safeStartActivity by context, intent can not be null!");
            return false;
        } else {
            if (!(context instanceof Activity)) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }

            try {
                context.startActivity(intent);
                return true;
            } catch (ActivityNotFoundException var3) {
                Log.e(TAG, "safeStartActivity by context", var3);
                return false;
            } catch (SecurityException var4) {
                Log.e(TAG, "safeStartActivity by context", var4);
                return false;
            }
        }
    }
}
