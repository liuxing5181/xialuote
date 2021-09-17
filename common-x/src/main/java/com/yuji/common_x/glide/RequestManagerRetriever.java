package com.yuji.common_x.glide;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;


/**
 * @author W4255
 * @data [ 2021/1/28 15:36]
 * @Description NA
 */
public class RequestManagerRetriever {
    public BitmapRequest get(Fragment fragment) {
        return new RequestManager(fragment).with();
    }

    public BitmapRequest get(FragmentActivity activity) {
        return new RequestManager(activity).with();
    }

    public BitmapRequest get(Context context) {
        return new RequestManager(context).with();
    }
}
