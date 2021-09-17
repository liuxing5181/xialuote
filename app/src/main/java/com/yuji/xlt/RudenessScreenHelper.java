
package com.yuji.xlt;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import com.yuji.xlt.ability.utils.CastUtils;
import com.yuji.xlt.ability.utils.Logger;
import com.yuji.xlt.ability.utils.ReflectionUtils;

import java.lang.reflect.Field;

import static android.content.Context.WINDOW_SERVICE;

/**
 * 文件描述
 *
 * @author dengshishuang
 * @version [V1.0.0.1, 2020/8/7]
 * @since V1.0.0.1
 */
public class RudenessScreenHelper {
    private static final String TAG = "RudenessScreenHelper";

    private Application.ActivityLifecycleCallbacks activityLifecycleCallbacks;

    private Application mApplication;

    private float designWidth = 1280;

    /**
     * 构造器.
     *
     * @param application 应用上下文
     * @param width       设计稿宽度
     */
    public RudenessScreenHelper(Application application, float width) {
        mApplication = application;
        designWidth = width;

        activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                // 通常情况下application与activity得到的resource虽然不是一个实例，但是displayMetrics是同一个实例，只需调用一次即可
                // 为了面对一些不可预计的情况以及向上兼容，分别调用一次较为保险
                resetDensity(mApplication, designWidth);
                resetDensity(activity, designWidth);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                resetDensity(mApplication, designWidth);
                resetDensity(activity, designWidth);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                resetDensity(mApplication, designWidth);
                resetDensity(activity, designWidth);
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        };
    }

    /**
     * 重新计算displayMetrics.xhdpi, 使单位pt重定义为设计稿的相对长度
     *
     * @param context     the context
     * @param designWidth 设计稿的宽度
     * @see #activate()
     */
    public static void resetDensity(Context context, float designWidth) {
        if (context == null) {
            return;
        }

        Point size = new Point();
        ((WindowManager) context.getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getSize(size);

        Resources resources = context.getResources();

        DisplayMetrics metrics = getMetricsOnMiui(context.getResources());
        if (metrics != null) {
            metrics.density = size.x / designWidth * 2;
        }

        resources.getDisplayMetrics().density = size.x / designWidth * 2;
    }

    /**
     * 恢复displayMetrics为系统原生状态，单位pt恢复为长度单位磅
     *
     * @param context 上下文对象
     * @see #inactivate()
     */
    public static void restoreDensity(Context context) {
        context.getResources().getDisplayMetrics().setToDefaults();

        DisplayMetrics metrics = getMetricsOnMiui(context.getResources());
        if (metrics != null) {
            metrics.setToDefaults();
        }
    }

    // 解决MIUI更改框架导致的MIUI7+Android5.1.1上出现的失效问题(以及极少数基于这部分miui去掉art然后置入xposed的手机)
    private static DisplayMetrics getMetricsOnMiui(Resources resources) {
        if ("MiuiResources".equals(resources.getClass().getSimpleName())
                || "XResources".equals(resources.getClass().getSimpleName())) {
            try {
                Field field = Resources.class.getDeclaredField("mTmpMetrics");
                ReflectionUtils.setAccessible(field, true);
                return CastUtils.cast(field.get(resources), DisplayMetrics.class);
            } catch (IllegalAccessException e) {
                Logger.e(TAG, "getMetricsOnMiui IllegalAccessException");
            } catch (NoSuchFieldException e) {
                Logger.e(TAG, "getMetricsOnMiui NoSuchFieldException");
            }
        }
        return null;
    }

    /**
     * 转换dp为px
     *
     * @param context 上下文对象
     * @param value   需要转换的dp值
     * @return px值 float
     */
    public static float dp2px(Context context, float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                context.getResources().getDisplayMetrics());
    }

    /**
     * 转换pt为px.
     *
     * @param context 上下文对象
     * @param value   需要转换的pt值
     * @return 像素值
     */
    public static float pt2px(Context context, float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, value, context.getResources().getDisplayMetrics());
    }

    /**
     * 激活本方案
     */
    public void activate() {
        resetDensity(mApplication, designWidth);
        mApplication.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    /**
     * 恢复系统原生方案
     */
    public void inactivate() {
        restoreDensity(mApplication);
        mApplication.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

}
