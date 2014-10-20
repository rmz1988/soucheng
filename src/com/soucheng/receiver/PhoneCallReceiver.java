package com.soucheng.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.soucheng.application.MainApplication;
import com.soucheng.listener.SouchengPhoneStateListener;
import com.soucheng.service.LocationService;
import com.soucheng.service.ScreenLockService;

/**
 * @author lichen
 */
public class PhoneCallReceiver extends BroadcastReceiver {
    private static boolean hasListen;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, ScreenLockService.class);
        context.startService(serviceIntent);

        Intent locationIntent = new Intent(context, LocationService.class);
        context.startService(locationIntent);
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        //设置一个监听器
        if (!hasListen) {
            manager.listen(new SouchengPhoneStateListener(context), PhoneStateListener.LISTEN_CALL_STATE);
            hasListen = true;
        }

    }
}
