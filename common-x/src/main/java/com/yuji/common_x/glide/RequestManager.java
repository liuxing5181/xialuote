package com.yuji.common_x.glide;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.yuji.common_x.glide.listener.LifecycleListener;
import com.yuji.xlt.ability.utils.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author W4255
 * @version [V1.0.0.000, 2021/1/27 0:13]
 * @Description: NA
 */
public class RequestManager {
    private static final String FRAGMENT_ACTIVITY_NAME = "Fragment_Activity_Name";
    private static volatile RequestManager instance;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 管理着请求队列
     */
    private BlockingQueue<BitmapRequest> requestQueue = new LinkedBlockingDeque<>();
    /**
     * 每个请求对应的线程
     */
    private BitmapDispatcher[] bitmapDispatchers;
    private static boolean isCreateFragment = false;

    private RequestManager() {
        Logger.i("RequestManager() -->");
        stopThread();
        createBitmapDispatcher();
    }

    /**
     * 代表无法管理生命周期
     *
     * @param context
     */
    public RequestManager(Context context) {
        this.mContext = context;
    }

    public RequestManager(FragmentActivity activity) {
        this.mContext = activity;
        Logger.i("RequestManager , start  isCreateFragment = " + isCreateFragment);
        if (isCreateFragment) {
            return;
        }
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(FRAGMENT_ACTIVITY_NAME);
        if (fragment == null) {
            isCreateFragment = true;
            Logger.i("RequestManager , fragment++ isCreateFragment = " + isCreateFragment);
            fragment = new RequestManagerFragment(lifecycleListener);
            //添加到 FragmentManager 中
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(fragment, FRAGMENT_ACTIVITY_NAME);
            transaction.commitAllowingStateLoss();
        }
    }

    public RequestManager(Fragment fragment) {
        this.mContext = fragment.getActivity();
    }

    public static RequestManager getInstance() {
        if (instance == null) {
            synchronized (RequestManager.class) {
                if (instance == null) {
                    instance = new RequestManager();
                }
            }
        }
        return instance;
    }

    public BitmapRequest with() {
        return new BitmapRequest(mContext);
    }

    /**
     * 加入队列
     *
     * @param request
     */
    public void addBitmapRequest(BitmapRequest request) {
        if (!requestQueue.contains(request)) {
            requestQueue.add(request);
        }
    }

    /**
     * threadCount = Runtime.getRuntime().availableProcessors();
     */
    private void createBitmapDispatcher() {
        Logger.i("createBitmapDispatcher() -->");
        int threadCount = 10;
        bitmapDispatchers = new BitmapDispatcher[threadCount];
        for (int i = 0; i < threadCount; i++) {
            BitmapDispatcher bitmapDispatcher = new BitmapDispatcher(requestQueue);
            bitmapDispatcher.start();
            bitmapDispatchers[i] = bitmapDispatcher;
        }
    }

    /**
     * 关闭线程
     */
    public void stopThread() {
        Logger.i("stopThread() -->");
        if (bitmapDispatchers == null || bitmapDispatchers.length <= 0) {
            return;
        }
        for (int i = 0; i < bitmapDispatchers.length; i++) {
            BitmapDispatcher bitmapDispatcher = bitmapDispatchers[i];
            if (!bitmapDispatcher.isInterrupted()) {
                bitmapDispatcher.interrupt();
            }
        }

    }

    private LifecycleListener lifecycleListener = new LifecycleListener() {
        @Override
        public void onStart() {
            Logger.d("onStart() -->");
        }

        @Override
        public void onStop() {
            Logger.d("onStop() -->");
        }

        @Override
        public void onDestroy() {
            Logger.d("onDestroy() -->");
            stopThread();
            isCreateFragment = false;
        }
    };

}
