package com.soucheng.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.soucheng.service.LocationService;
import com.soucheng.service.ScreenLockService;

/**
 * 开机启动服务
 *
 * @author lichen
 */
public class BootCompletedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Intent locationServiceIntent = new Intent(context, LocationService.class);
            context.startService(locationServiceIntent);

            Intent screenLockServiceIntent = new Intent(context, ScreenLockService.class);
            context.startService(screenLockServiceIntent);
//
//            Intent TimeTickServiceIntent = new Intent(context, TimeTickService.class);
//            context.startService(TimeTickServiceIntent);
        }
    }
}
