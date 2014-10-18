package com.soucheng.activity;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.soucheng.application.MainApplication;
import com.soucheng.db.DbAdapter;
import com.soucheng.view.LockLayer;
import com.soucheng.vo.Config;
import com.soucheng.vo.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author lichen
 */
public class ScreenLockActivity extends Activity implements View.OnTouchListener {

	public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;

	private ImageView stone;
	private ImageView stonePress;
	private ImageView lock;
	private ImageView ad;
	private ImageView lockSelect;
	private ImageView adSelect;
	private ImageView lockPress;
	private ImageView adPress;

	private TextView adGoldView;
	private TextView lockGoldView;

	private TextView dateView;
	private TextView timeView;

	private int adGold;
	private int lockGold;

	private FrameLayout screen;
	private DbAdapter dbAdapter;
	private MainApplication application;

	private int deltaX;
	private FrameLayout.LayoutParams sourceLayoutParams;
	private LockLayer lockLayer;

	private boolean stoneSelected;

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			dateView.setText(getCurrentDate());
			timeView.setText(getCurrentTime());
		}
	};

	private BroadcastReceiver dateTimeReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (Intent.ACTION_TIME_TICK.equals(intent.getAction())) {
				handler.sendEmptyMessage(0);
			}
		}
	};

	@Override
	protected void onResume() {
		super.onResume();
		getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
						WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);
		getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
						WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

		View view = LayoutInflater.from(this).inflate(R.layout.screen_lock, null);

		lockLayer = LockLayer.getInstance(this);
		lockLayer.setLockView(view);
		lockLayer.lock();

		init(view);
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Intent.ACTION_TIME_TICK);
		registerReceiver(dateTimeReceiver, intentFilter);
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(dateTimeReceiver);

		application.recycleBitmap(stone);
		application.recycleBitmap(stonePress);
		application.recycleBitmap(lock);
		application.recycleBitmap(ad);
		application.recycleBitmap(lockSelect);
		application.recycleBitmap(adSelect);
		application.recycleBitmap(lockPress);
		application.recycleBitmap(adPress);
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return keyCode == KeyEvent.KEYCODE_HOME || keyCode == KeyEvent.KEYCODE_BACK || super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		final int x = (int) event.getRawX();
		final int y = (int) event.getRawY();
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if (isStoneSelected(x, y)) {
					stoneSelected = true;
					FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) stone.getLayoutParams();
					deltaX = x - layoutParams.leftMargin;
					stone.setVisibility(View.GONE);
					stonePress.setVisibility(View.VISIBLE);
					lockSelect.setVisibility(View.VISIBLE);
					adSelect.setVisibility(View.VISIBLE);
				}
				sourceLayoutParams = (FrameLayout.LayoutParams) stonePress.getLayoutParams();
				break;
			case MotionEvent.ACTION_MOVE:
				if (stoneSelected && (x >= ad.getX() || x <= lock.getX())) {
					FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) stonePress.getLayoutParams();
					layoutParams.leftMargin = x - deltaX;
					stonePress.setLayoutParams(layoutParams);

					if (isAdSelected(x, y)) {
						ad.setVisibility(View.GONE);
						adSelect.setVisibility(View.GONE);
						adPress.setVisibility(View.VISIBLE);
						stonePress.setVisibility(View.GONE);

					} else if (isLockSelected(x, y)) {
						lock.setVisibility(View.GONE);
						lockSelect.setVisibility(View.GONE);
						lockPress.setVisibility(View.VISIBLE);
						stonePress.setVisibility(View.GONE);
					} else {
						ad.setVisibility(View.VISIBLE);
						lock.setVisibility(View.VISIBLE);
						adSelect.setVisibility(View.VISIBLE);
						lockSelect.setVisibility(View.VISIBLE);
						adPress.setVisibility(View.GONE);
						lockPress.setVisibility(View.GONE);
					}
				}
				break;
			case MotionEvent.ACTION_UP:
				if (isAdSelected(x, y) && stoneSelected) {
					addGold(adGold);
					lockLayer.unlock();
					finish();
				} else if (isLockSelected(x, y) && stoneSelected) {
					addGold(lockGold);
					lockLayer.unlock();
					finish();
				} else {
					stonePress.setLayoutParams(sourceLayoutParams);
					stonePress.setVisibility(View.GONE);
					stone.setVisibility(View.VISIBLE);
					adSelect.setVisibility(View.GONE);
					lockSelect.setVisibility(View.GONE);
				}
				stoneSelected = false;
				break;
		}

		screen.invalidate();
		return true;
	}

	private boolean isStoneSelected(int x, int y) {
		int stoneX = (int) stone.getX();
		int stoneY = (int) stone.getY();
		int stoneWidth = stone.getWidth();
		int stoneHeight = stone.getHeight();
		return stoneX - 100 <= x && stoneX + stoneWidth + 100 >= x && stoneY - 100 <= y &&
				stoneY + stoneHeight + 100 >= y;
	}

	private boolean isAdSelected(int x, int y) {
		int adX = (int) ad.getX();
		int adY = (int) ad.getY();
		int adWidth = stone.getWidth();
		int adHeight = stone.getHeight();
		return adX - 100 <= x && adX + adWidth + 100 >= x && adY - 100 <= y && adY + adHeight + 100 >= y;
	}

	private boolean isLockSelected(int x, int y) {
		int lockX = (int) lock.getX();
		int lockY = (int) lock.getY();
		int lockWidth = lock.getWidth();
		int lockHeight = lock.getHeight();
		return lockX - 100 <= x && lockX + lockWidth + 100 >= x && lockY - 100 <= y && lockY + lockHeight + 100 >= y;
	}

	private void init(View view) {
		dbAdapter = new DbAdapter(this);
		application = (MainApplication) getApplication();
        application.setScreenLockActivity(this);
		stone = (ImageView) view.findViewById(R.id.stone);
		stone.setImageBitmap(application.loadBitmap(R.drawable.stone_n, 0));
		stonePress = (ImageView) view.findViewById(R.id.stonePress);
		stonePress.setImageBitmap(application.loadBitmap(R.drawable.stone_s, 0));
		lock = (ImageView) view.findViewById(R.id.lock);
		lock.setImageBitmap(application.loadBitmap(R.drawable.lock_n, 0));
		ad = (ImageView) view.findViewById(R.id.ad);
		ad.setImageBitmap(application.loadBitmap(R.drawable.web_n, 0));
		lockSelect = (ImageView) view.findViewById(R.id.lockSelect);
		lockSelect.setImageBitmap(application.loadBitmap(R.drawable.stone_s, 0));
		adSelect = (ImageView) view.findViewById(R.id.adSelect);
		adSelect.setImageBitmap(application.loadBitmap(R.drawable.stone_s, 0));
		lockPress = (ImageView) view.findViewById(R.id.lockPress);
		lockPress.setImageBitmap(application.loadBitmap(R.drawable.lock_p, 0));
		adPress = (ImageView) view.findViewById(R.id.adPress);
		adPress.setImageBitmap(application.loadBitmap(R.drawable.web_p, 0));

		dateView = (TextView) view.findViewById(R.id.dateView);
		timeView = (TextView) view.findViewById(R.id.timeView);
		dateView.setText(getCurrentDate());
		timeView.setText(getCurrentTime());

		adGoldView = (TextView) view.findViewById(R.id.adGoldView);
		lockGoldView = (TextView) view.findViewById(R.id.lockGoldView);

		Random r = new Random();
		lockGold = r.nextInt(10);
		adGold = 10 - lockGold;
		lockGoldView.setText(lockGold != 0 ? lockGold + "城币" : "");
		adGoldView.setText(adGold != 0 ? adGold + "城币" : "");

		screen = (FrameLayout) view.findViewById(R.id.screen);
		screen.setBackground(new BitmapDrawable(getResources(), application.loadBitmap(R.drawable.screen_lock, 4)));
		screen.setOnTouchListener(this);
	}

	private void addGold(int gold) {
		User user = application.getLoginUser();
		dbAdapter.open();
		if (user == null) {
			Config config = dbAdapter.getConfig();
			user = dbAdapter.getUser(config.getLoginUsername());
		}

		user.setGold(user.getGold() + gold);
		dbAdapter.updateUser(user);
	}

	private String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 EEEE");
		return sdf.format(new Date());
	}

	private String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(new Date());
	}


}