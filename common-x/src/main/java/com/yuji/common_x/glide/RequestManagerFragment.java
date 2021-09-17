package com.yuji.common_x.glide;

import android.annotation.SuppressLint;

import androidx.fragment.app.Fragment;

import com.yuji.common_x.glide.listener.LifecycleListener;

/**
 * @author W4255
 * @data [ 2021/1/28 16:16]
 * @Description NA
 */
public class RequestManagerFragment extends Fragment {
    private LifecycleListener lifecycleListener;

    public RequestManagerFragment() {

    }

    @SuppressLint("ValidFragment")
    public RequestManagerFragment(LifecycleListener lifecycleListener) {
        this.lifecycleListener = lifecycleListener;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (lifecycleListener != null) {
            lifecycleListener.onStart();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (lifecycleListener != null) {
            lifecycleListener.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (lifecycleListener != null) {
            lifecycleListener.onDestroy();
        }
    }
}
