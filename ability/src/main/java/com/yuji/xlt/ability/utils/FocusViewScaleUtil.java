
package com.yuji.xlt.ability.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * View聚焦放大缩小util类<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
 */
public class FocusViewScaleUtil {

    public static final float ZOOM_SCALE_SMALL = 1.05f;

    public static final float ZOOM_SCALE = 1.10f;

    private static final float ORIGIN_SCALE = 1.0f;

    public static final float SCALE_1_2 = 1.2f;

    private static final long ANIM_DURATION = 300;

    public static void setViewAnimator(View v, boolean focus) {
        setViewAnimator(v, focus, ZOOM_SCALE);
    }

    public static void setViewAnimatorSmall(View v, boolean focus) {
        setViewAnimator(v, focus, ZOOM_SCALE_SMALL);
    }

    public static void setViewAnimator(View v, boolean focus, float... params) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX;
        ObjectAnimator scaleY;
        if (focus) {
            scaleX = ObjectAnimator.ofFloat(v, "scaleX", ORIGIN_SCALE, params[0]);
            scaleY = ObjectAnimator.ofFloat(v, "scaleY", ORIGIN_SCALE, params[0]);
        } else {
            scaleX = ObjectAnimator.ofFloat(v, "scaleX", params[0], ORIGIN_SCALE);
            scaleY = ObjectAnimator.ofFloat(v, "scaleY", params[0], ORIGIN_SCALE);
        }
        if (params.length > 1) {
            v.setPivotX(params[1]);
            v.setPivotY(params[2]);
        }
        animatorSet.setDuration(ANIM_DURATION);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.start();
    }
}
