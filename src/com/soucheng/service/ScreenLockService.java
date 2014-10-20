package com.soucheng.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.IBinder;
import com.soucheng.activity.R;
import com.soucheng.application.MainApplication;
import com.soucheng.receiver.ScreenOffReceiver;
import com.soucheng.receiver.ScreenOnReceiver;

/**
 * @author lichen
 */
public class ScreenLockService extends Service {

    private MainApplication application;
    private boolean isScreenOnReceiverRegister = false;
    private boolean isScreenOffReceiverRegister = false;
    private ScreenOnReceiver screenOnReceiver = new ScreenOnReceiver();
    private ScreenOffReceiver screenOffReceiver = new ScreenOffReceiver();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = (MainApplication) getApplication();
        application.setScreenLockService(this);
        registerReceiver();
    }

    public void registerReceiver() {
        IntentFilter screenOnIntentFilter = new IntentFilter();
        screenOnIntentFilter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(screenOnReceiver, screenOnIntentFilter);
        isScreenOnReceiverRegister = true;
        IntentFilter screenOffIntentFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        screenOnIntentFilter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(screenOffReceiver, screenOffIntentFilter);
        isScreenOffReceiverRegister = true;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setLargeIcon(((BitmapDrawable) (getResources().getDrawable(R.drawable.logo))).getBitmap());
        builder.setSmallIcon(R.drawable.logo);
        builder.setContentInfo("ScreenLockService is running...");
        Notification notification = builder.build();
        startForeground(0, notification);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (isScreenOnReceiverRegister) {
            unregisterReceiver(screenOnReceiver);
            isScreenOnReceiverRegister = false;
        }
        if (isScreenOffReceiverRegister) {
            unregisterReceiver(screenOffReceiver);
            isScreenOffReceiverRegister = false;
        }
        Intent intent = new Intent();
        intent.setAction("restart.screen.lock.service");
        sendBroadcast(intent);
        stopForeground(true);
        application.setLocationService(null);
        super.onDestroy();
    }


}
