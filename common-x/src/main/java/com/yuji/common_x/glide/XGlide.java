package com.yuji.common_x.glide;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * @author W4255
 * @version [V1.0.0.000, 2021/1/27 0:27]
 * @Description: NA
 */
public class XGlide {
    private static volatile XGlide instance;
    private RequestManagerRetriever retriever;

    private XGlide(RequestManagerRetriever retriever) {
        this.retriever = retriever;
    }

    private static XGlide getInstance() {
        if (instance == null) {
            synchronized (XGlide.class) {
                if (instance == null) {
                    RequestManagerRetriever retriever = new RequestManagerRetriever();
                    instance = new XGlide(retriever);
                }
            }
        }
        return instance;
    }

    public static RequestManagerRetriever getRetriever() {
        return getInstance().retriever;
    }

    public static BitmapRequest with(Fragment fragment) {
        return getRetriever().get(fragment);
    }

    public static BitmapRequest with(FragmentActivity activity) {
        return getRetriever().get(activity);
    }

    public static BitmapRequest with(Context context) {
        return getRetriever().get(context);
    }
}
