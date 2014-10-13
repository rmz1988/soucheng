package com.soucheng.service;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.IBinder;
import com.soucheng.activity.LocationNotifyActivity;
import com.soucheng.activity.R;
import com.soucheng.activity.ScreenLockActivity;
import com.soucheng.application.MainApplication;

/**
 * @author lichen
 */
public class ScreenLockService extends Service {

	private MainApplication application;
	private BroadcastReceiver screenOnReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (Intent.ACTION_SCREEN_ON.equals(intent.getAction()) ||
					Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {
				//解锁屏幕
				application.disableKeyguard();
			}
		}
	};

	private BroadcastReceiver screenOffReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction()) && !application.isPhoneInUse()) {
				//显示自定义锁屏界面
				Intent lockIntent = new Intent(ScreenLockService.this, ScreenLockActivity.class);
				lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(lockIntent);
			}
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		application = (MainApplication) getApplication();

		IntentFilter screenOnIntentFilter = new IntentFilter();
		screenOnIntentFilter.addAction(Intent.ACTION_SCREEN_ON);
		screenOnIntentFilter.addAction(Intent.ACTION_USER_PRESENT);
		registerReceiver(screenOnReceiver, screenOnIntentFilter);
		IntentFilter screenOffIntentFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
		registerReceiver(screenOffReceiver, screenOffIntentFilter);
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
		unregisterReceiver(screenOnReceiver);
		unregisterReceiver(screenOffReceiver);
		stopForeground(true);
		super.onDestroy();
	}


}
