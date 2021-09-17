package com.yuji.xlt.main.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/9/11]
 * @since V1.0.0
 */
public class UsbBroadcastReceiver extends BroadcastReceiver {
    public final static String ACTION = "android.hardware.usb.action.USB_STATE";

    //todo manifest注册如下
    //<receiver
    //            android:name=".receiver.UsbBroadcastReceiver"
    //            android:enabled="true">
    //            <intent-filter>
    //                <action android:name="android.hardware.usb.action.USB_STATE" />
    //            </intent-filter>
    //        </receiver>
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ACTION.equals(intent.getAction())) {
            boolean connected = intent.getExtras().getBoolean("connected");
            if (connected) {
                //TODO 接入USB接口
                Toast.makeText(context, "插入usb" + action, Toast.LENGTH_LONG).show();
            } else {
                //TODO 拔出USB接口
                Toast.makeText(context, "拔出usb" + action, Toast.LENGTH_LONG).show();
            }
        }
    }
}
