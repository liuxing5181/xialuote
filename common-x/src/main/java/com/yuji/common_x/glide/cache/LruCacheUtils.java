package com.yuji.common_x.glide.cache;

import android.util.LruCache;

/**
 * @author W4255
 * @version [V1.0.0.000, 2021/1/28 0:33]
 * @Description: NA
 */
public class LruCacheUtils extends LruCache {
    private static LruCacheUtils instance;

    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public LruCacheUtils(int maxSize) {
        super(maxSize);
    }

    public static LruCacheUtils getInstance() {
        if (instance == null) {
            synchronized (LruCacheUtils.class) {
                if (instance == null) {
                    instance = new LruCacheUtils(1000);
                }
            }
        }
        return instance;
    }

    @Override
    protected int sizeOf(Object key, Object value) {
        return super.sizeOf(key, value);
    }
}
