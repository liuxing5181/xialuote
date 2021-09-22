package com.yuji.xlt;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.yuji.xlt.ability.AppContext;
import com.yuji.xlt.ability.utils.Logger;
import com.yuji.xlt.ability.utils.ProcessUtils;

/**
 * <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/25]
 * @since V1.0.0
 */
public class MyApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        if (isMainProcess()) {
            init();
        }
    }
    /**
     * 初始化context
     */
    private void init() {
        AppContext.INSTANCE.init(getApplicationContext());
        Logger.initLogTag("<XLT>");
    }
    /**
     * 判断当前的进程是否是主进程
     *
     * @return true表示主进程
     */
    private boolean isMainProcess() {
        return ProcessUtils.isMainProcess(getApplicationContext(), getPackageName());
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
