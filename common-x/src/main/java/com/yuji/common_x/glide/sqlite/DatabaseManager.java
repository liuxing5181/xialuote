package com.yuji.common_x.glide.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.yuji.xlt.ability.utils.Logger;


/**
 * @author W4255
 * @version [V1.0.0.000, 2021/1/28 0:18]
 * @Description: NA
 */
public class DatabaseManager {
    private static SQLiteDatabase db;
    private static String DatabaseName = "cache.db";
    private static DatabaseManager instance;

    /**
     * getWritableDatabase()：它以读写的方式去打开数据库，当数据库的磁盘空间满了时，就只能读不能写。
     * getReadableDatabase()：它内部也调用了getWritableDatabase(),以读写的方式打开，如果数据库磁盘空间满了，则打开失败。
     * 打开失败后，如果继续尝试对数据库的读，则会读取数据，不能写
     *
     * @param context
     */
    private DatabaseManager(Context context) {
        DatabaseSqLite sqlIte = new DatabaseSqLite(context, DatabaseName, null, 1);
        db = sqlIte.getWritableDatabase();
    }

    public static DatabaseManager getInstance(Context context) {
        if (instance == null) {
            synchronized (DatabaseManager.class) {
                if (instance == null) {
                    instance = new DatabaseManager(context);
                }
            }
        }
        return instance;
    }

    /**
     * 插入数据进数据库
     *
     * @param md5Key 图片的key md5
     * @param value  图片value 图片资源
     */
    public static void put(String md5Key, byte[] value) {
        ContentValues values = new ContentValues();
        values.put("ke", md5Key);
        values.put("content", value);
        try {
            db.insertOrThrow("cache_table", null, values);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    /**
     * 删除数据库缓存
     */
    public void remove() {
        try {
            db.delete("cache_table", null, null);
            Logger.i("delete", "删除成功");
        } catch (Exception e) {
            Logger.i("delete", "数据库暂无数据");
        }
    }

    /**
     * 读取数据库数据
     *
     * @param key 图片key
     * @return 返回Bitmap
     */
    public static Bitmap get(String key) {
        //存放图片key
        String md5Key;
        //存放图片二进制
        byte[] picContent = null;
        //返回图片
        Bitmap resultPic = null;
        Cursor query = db.query(true, "cache_table", null,
                null, null, null, null, null, null);
        if (query.moveToFirst()) {
            do {
                //查询到当前图片的key时
                md5Key = query.getString(query.getColumnIndex("ke"));
                //一个key，对应一个value，当查询到了就break退出
                if (key.equals(md5Key)) {
                    //拿到图片的value
                    picContent = query.getBlob(query.getColumnIndex("content"));
                    break;
                }
            } while (query.moveToNext());
        }
        //如果数据为null时则不进行转换
        if (picContent != null) {
            resultPic = BitmapFactory.decodeByteArray(picContent, 0, picContent.length);
        }
        query.close();
        return resultPic;
    }
}
