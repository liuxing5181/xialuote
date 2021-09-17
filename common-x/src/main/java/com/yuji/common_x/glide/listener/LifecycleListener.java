package com.yuji.common_x.glide.listener;

/**
 * @author W4255
 * @version [V1.0.0.000, 2021/1/26 23:13]
 * @Description: NA
 */
public interface LifecycleListener {
    /**
     * Callback for when {@link androidx.fragment.app.Fragment#onStart()}} or {@link android.app.Activity##onStart()} is called.
     */
    void onStart();

    /**
     * Callback for when {@link androidx.fragment.app.Fragment#onStop()}} or {@link android.app.Activity##onStop()}} is called.
     */
    void onStop();

    /**
     * Callback for when {@link androidx.fragment.app.Fragment#onDestroy()}} or {@link android.app.Activity##onDestroy()} is
     * called.
     */
    void onDestroy();
}
