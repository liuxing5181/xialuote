package com.yuji.common_x.glide.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.yuji.xlt.ability.utils.Logger;


/**
 * @author W4255
 * @version [V1.0.0.000, 2021/1/28 0:43]
 * @Description: NA
 */
public class DatabaseSqLite extends SQLiteOpenHelper {
    public static final String CREATE_TABLE = "create table cache_table (" +
            "id integer primary key autoincrement," +
            "ke text unique," +
            "content blob" +
            ")";
    private Context context;
    public DatabaseSqLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        Logger.d("表创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}