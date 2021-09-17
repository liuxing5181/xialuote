package com.yuji.xlt.ability.utils;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.yuji.xlt.ability.AppContext;


/**
 * NA<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
 */
public class ResUtils {
    private static final String TAG = "ResUtils";
    private static final float ROUNDING = 0.5F;

    public static int getColor(int resId) {
        try {
            return AppContext.INSTANCE.getContext().getResources().getColor(resId);
        } catch (Resources.NotFoundException var2) {
            Logger.e(TAG, var2);
            return 0;
        }
    }

    private ResUtils() {
    }

    public static int getInt(int resId) {
        try {
            return AppContext.INSTANCE.getContext().getResources().getInteger(resId);
        } catch (Resources.NotFoundException var2) {
            Logger.e(TAG, var2);
            return 0;
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public static Drawable getDrawable(int resId) {
        try {
            return AppContext.INSTANCE.getContext().getResources().getDrawable(resId);
        } catch (Resources.NotFoundException var2) {
            Logger.e(TAG, var2);
            return null;
        }
    }

    public static int getDimensionPixelSize(int resId) {
        try {
            return AppContext.INSTANCE.getContext().getResources().getDimensionPixelSize(resId);
        } catch (Resources.NotFoundException var2) {
            Logger.e(TAG, var2);
            return 0;
        }
    }

    public static int dp2Px(int dp) {
        float scale = AppContext.INSTANCE.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + ROUNDING);
    }

    public static int dp2Px(float dp) {
        float scale = AppContext.INSTANCE.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + ROUNDING);
    }

}
