package com.soucheng.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.soucheng.activity.ScreenLockActivity;
import com.soucheng.application.MainApplication;

/**
 * 屏幕关闭事件接收器
 * Created by Riemannh on 2014/10/18.
 */
public class ScreenOffReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        MainApplication application = (MainApplication) context.getApplicationContext();
        if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction()) && !application.isPhoneInUse()) {
            //显示自定义锁屏界面
            Intent lockIntent = new Intent(context, ScreenLockActivity.class);
            lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (null == application.getScreenLockActivity() || application.getScreenLockActivity().isFinishing()) {
                context.startActivity(lockIntent);
            }
        }
    }
}
