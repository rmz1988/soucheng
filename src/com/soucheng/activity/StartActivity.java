package com.soucheng.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.soucheng.application.MainApplication;
import com.soucheng.db.DbAdapter;
import com.soucheng.file.FileUtils;
import com.soucheng.vo.Config;
import com.soucheng.vo.User;

/**
 * 系统启动界面闪屏
 *
 * @author lichen
 */
public class StartActivity extends Activity {

	private static final int MSG_NO_SD_CARD = 0;
	private static final int MSG_FIRST_LOGIN = 1;
	private static final int MSG_NOT_LOGIN = 2;
	private static final int MSG_HAS_LOGIN = 3;

	private DbAdapter dbAdapter;

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case MSG_NO_SD_CARD:
					AlertDialog.Builder builder = new AlertDialog.Builder(StartActivity.this);
					AlertDialog dialog = builder.setTitle(R.string.warn).setMessage(R.string.no_sd_card)
							.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									finish();
								}
							}).create();

					dialog.show();
					break;
				case MSG_FIRST_LOGIN:
				case MSG_NOT_LOGIN:
				case MSG_HAS_LOGIN:
					Intent intent = new Intent(StartActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
					break;
			}
		}
	};

	@Override

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.start);
		overridePendingTransition(R.anim.expand_in, 0);

		//初始化参数
		initParams();

		new Thread() {

			@Override
			public void run() {
				//判断是否有sd卡
				if (FileUtils.hasSdCard()) {
					//判断是否第一次打开应用
					dbAdapter.open();
					Config config = dbAdapter.getConfig();
					if (config.isFirstOpen()) {
						config.setFirstOpen(Config.NOT_FIRST_OPEN);
						dbAdapter.updateConfig(config);
						handler.sendEmptyMessageDelayed(MSG_FIRST_LOGIN, 2000L);
					} else if (config.hasUserLogin()) {//判断是否有用户登录
						User user = dbAdapter.getUser(config.getLoginUsername());
						((MainApplication) getApplication()).setLoginUser(user);
						handler.sendEmptyMessageDelayed(MSG_HAS_LOGIN, 2000L);
					} else {
						handler.sendEmptyMessageDelayed(MSG_NOT_LOGIN, 2000L);
					}

				} else {
					handler.sendEmptyMessageDelayed(MSG_NO_SD_CARD, 2000L);
				}

			}
		}.start();
	}

	private void initParams() {
		dbAdapter = new DbAdapter(this);
	}
}