package com.yuji.common_x.glide;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;


import com.yuji.common_x.glide.http.ImageHttpUtil;
import com.yuji.xlt.ability.utils.Logger;

import java.util.concurrent.BlockingQueue;

/**
 * @author W4255
 * @version [V1.0.0.000, 2021/1/26 23:19]
 * @Description: NA
 */
public class BitmapDispatcher extends Thread {
    private BlockingQueue<BitmapRequest> bitmapRequests;
    private Handler handler = new Handler(Looper.getMainLooper());

    public BitmapDispatcher(BlockingQueue<BitmapRequest> bitmapRequests) {
        this.bitmapRequests = bitmapRequests;
    }

    @Override
    public void run() {
        super.run();
        if (bitmapRequests == null) {
            Logger.w("run bitmapRequests == null ");
            return;
        }
        while (!isInterrupted()) {
            try {
                //1 从队列中拿到请求Request
                BitmapRequest request = bitmapRequests.take();
                //2 加载占位
                showDefaultPic(request);
                //3 下载图片
                Bitmap bitmap = ImageHttpUtil.loadBitmap(request);
                //4 显示在view
                showImage(request, bitmap);
            } catch (Exception e) {
                Logger.w("run e = " + e.toString());
                e.printStackTrace();
            }
        }
    }


    private void showImage(BitmapRequest request, Bitmap bitmap) {
        if (request.getImageView() != null && bitmap != null) {
            handler.post(new ShowImageViewRun(request, bitmap));
        }
    }

    public static class ShowImageViewRun implements Runnable {
        private BitmapRequest request;
        private Bitmap bitmap;

        public ShowImageViewRun(BitmapRequest request, Bitmap bitmap) {
            this.request = request;
            this.bitmap = bitmap;
        }

        @Override
        public void run() {
            if (bitmap != null && request.getImageView() != null
                    && request.getImageView().getTag().equals(request.getUrlMd5())) {
                ImageView imageView = request.getImageView();
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    private void showDefaultPic(BitmapRequest request) {
        handler.post(new LoadingImageRun(request));
    }

    public static class LoadingImageRun implements Runnable {
        private BitmapRequest request;

        public LoadingImageRun(BitmapRequest request) {
            this.request = request;
        }

        @Override
        public void run() {
            if (request.getLoadingResId() > 0 && request.getImageView() != null) {
                int resId = request.getLoadingResId();
                if (resId > 0) {
                    ImageView imageView = request.getImageView();
                    imageView.setImageResource(resId);
                }
            }
        }
    }

}
