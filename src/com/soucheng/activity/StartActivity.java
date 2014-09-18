package com.soucheng.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

/**
 * 系统启动界面闪屏
 *
 * @author lichen
 */
public class StartActivity extends Activity {

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			Intent intent = new Intent(StartActivity.this, HomeActivity.class);
			startActivity(intent);
			finish();
		}
	};

	@Override

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.start);
		overridePendingTransition(R.anim.expand_in, 0);

		new Thread() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000L);
					handler.sendEmptyMessage(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}