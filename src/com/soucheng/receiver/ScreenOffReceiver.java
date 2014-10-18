package com.soucheng.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.soucheng.activity.ScreenLockActivity;
import com.soucheng.application.MainApplication;
import com.soucheng.service.ScreenLockService;

/**
 * 屏幕关闭事件接收器
 * Created by Riemannh on 2014/10/18.
 */
public class ScreenOffReceiver extends BroadcastReceiver {
    private ScreenLockService screenLockService = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        MainApplication application = (MainApplication) context.getApplicationContext();
        screenLockService = application.getScreenLockService();
        if (null == screenLockService) {
            Intent screenLockServiceIntent = new Intent(context, ScreenLockService.class);
            context.stopService(screenLockServiceIntent);
        }
        if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction()) && !application.isPhoneInUse()) {
            //显示自定义锁屏界面
            Intent lockIntent = new Intent(screenLockService, ScreenLockActivity.class);
            lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (null == application.getScreenLockActivity() || application.getScreenLockActivity().isFinishing()) {
                context.startActivity(lockIntent);
            }
        }
    }

    public ScreenLockService getScreenLockService() {
        return screenLockService;
    }

    public void setScreenLockService(ScreenLockService screenLockService) {
        this.screenLockService = screenLockService;
    }
}
