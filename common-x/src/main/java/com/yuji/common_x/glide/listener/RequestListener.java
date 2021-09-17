package com.yuji.common_x.glide.listener;

import android.graphics.Bitmap;

/**
 * @author W4255
 * @version [V1.0.0.000, 2021/1/26 23:13]
 * @Description: NA
 */
public interface RequestListener {
    /**
     * success
     *
     * @param bitmap
     */
    void onSuccess(Bitmap bitmap);

    /**
     * fail
     *
     * @param object
     */
    void onFail(Object object);
}
