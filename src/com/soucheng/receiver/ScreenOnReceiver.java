package com.soucheng.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.soucheng.application.MainApplication;

/**
 * 屏幕点亮事件接收器
 * Created by Riemannh on 2014/10/18.
 */
public class ScreenOnReceiver extends BroadcastReceiver {

    private MainApplication application = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        application = (MainApplication) context.getApplicationContext();
        if (Intent.ACTION_SCREEN_ON.equals(intent.getAction()) ||
                Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {
            //解锁屏幕
            application.disableKeyguard();
        }
    }
}
