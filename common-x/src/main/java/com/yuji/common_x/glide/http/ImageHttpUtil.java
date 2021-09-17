package com.yuji.common_x.glide.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.yuji.common_x.glide.BitmapRequest;
import com.yuji.common_x.glide.cache.LruCacheUtils;
import com.yuji.common_x.glide.sqlite.DatabaseManager;
import com.yuji.common_x.glide.util.CompressPic;
import com.yuji.xlt.ability.utils.Logger;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author W4255
 * @version [V1.0.0.000, 2021/1/26 23:04]
 * @Description: NA
 */
public class ImageHttpUtil {
    /**
     * UrlConnection 请求url返回Bitmap
     *
     * @param url
     * @return Bitmap
     */
    public static Bitmap downLoadImage(String url) {
        Bitmap bitmap = null;
        URLConnection conn;
        InputStream is;
        try {
            conn = new URL(url).openConnection();
            is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bitmap = BitmapFactory.decodeStream(bis);
            is.close();
            bis.close();
        } catch (IOException e) {
            Logger.w("ImageHttpUtil downLoadImage e = " + e.toString());
        }
        return bitmap;
    }

    /**
     * 请求图片，使用三方框架
     * OKhttp
     *
     * @param request
     * @return
     */
    public static Bitmap loadBitmap(BitmapRequest request) {
        Bitmap bitmap = null;
        LruCacheUtils lruCacheUtils = LruCacheUtils.getInstance();
        //从缓存拿
        bitmap = (Bitmap) lruCacheUtils.get(request.getUrlMd5());
        if (bitmap != null) {
            Logger.i("ImageHttpUtil", "从缓存中拿到");
            return bitmap;
        }
        //从数据库拿
        bitmap = DatabaseManager.get(request.getUrlMd5());
        if (bitmap != null) {
            Logger.i("ImageHttpUtil", "从数据库中拿到");
            lruCacheUtils.put(request.getUrlMd5(), bitmap);
            return bitmap;
        }
        //下载图片
        bitmap = downLoadUrlPic(request.getUrl(), request);
        if (bitmap != null) {
            //加入缓存中
            cachePut(request, bitmap);
        }
        return bitmap;
    }

    private static void cachePut(BitmapRequest request, Bitmap bitmap) {
        LruCacheUtils lruCacheUtils = LruCacheUtils.getInstance();
        //内存缓存
        lruCacheUtils.put(request.getUrlMd5(), bitmap);
        //数据库缓存-将bitmap转换为byte
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        byte[] bit = outputStream.toByteArray();
        DatabaseManager.put(request.getUrlMd5(), bit);
    }

    private static Bitmap downLoadUrlPic(String url, BitmapRequest requestBitmap) {
        Bitmap bitmap = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            Logger.e("downLoadUrlPic, " + requestBitmap.getUrl() + ",ok");
            bitmap = CompressPic.decodeBitmap(response.body().bytes(), 800, 800);
        } catch (Exception e) {
            Logger.e("downLoadUrlPic, " + requestBitmap.getUrl() + ",error  = " + e.toString());
            e.printStackTrace();
        }
        return bitmap;
    }
}
