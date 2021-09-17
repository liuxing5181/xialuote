package com.yuji.common_x.glide;

import android.content.Context;
import android.widget.ImageView;


import com.yuji.common_x.glide.listener.RequestListener;
import com.yuji.common_x.glide.sqlite.DatabaseManager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * @author W4255
 * @version [V1.0.0.000, 2021/1/26 23:10]
 * @Description: NA
 */
public class BitmapRequest {
    private Context context;
    /**
     * 图片URL
     */
    private String url;
    private WeakReference<ImageView> weakReference;
    /**
     * 占位图
     */
    private int loadingResId;
    private RequestListener requestListener;
    /**
     * 请求访问标志
     */
    private String urlMd5;
    public static ArrayList<String> urlLst = new ArrayList<>();

    public BitmapRequest(Context context) {
        this.context = context;
        //创建缓存图片的数据库
        DatabaseManager.getInstance(context);
    }

    public BitmapRequest loading(int resId) {
        this.loadingResId = resId;
        return this;
    }

    public BitmapRequest load(String url) {
        this.url = url;
        this.urlMd5 = Md5Util.stringMd5(url);
        urlLst.add(urlMd5);
        return this;
    }

    public BitmapRequest listener(RequestListener listener) {
        this.requestListener = listener;
        return this;
    }

    public void into(ImageView imageView) {
        imageView.setTag(this.urlMd5);
        this.weakReference = new WeakReference<>(imageView);
        RequestManager.getInstance().addBitmapRequest(this);
    }

    public void removeToSqlCache() {
        DatabaseManager.getInstance(context).remove();
    }


    public String getUrl() {
        return url;
    }

    public ImageView getImageView() {
        return weakReference.get();
    }

    public int getLoadingResId() {
        return loadingResId;
    }

    public Context getContext() {
        return context;
    }

    public RequestListener getRequestListener() {
        return requestListener;
    }

    public String getUrlMd5() {
        return urlMd5;
    }
}
