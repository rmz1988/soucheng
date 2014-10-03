package com.soucheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.soucheng.application.MainApplication;
import com.soucheng.db.DbAdapter;
import com.soucheng.vo.Config;
import com.soucheng.vo.User;

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

    private TextView adGoldView;
    private TextView lockGoldView;

    private int adGold;
    private int lockGold;

    private FrameLayout screen;
    private DbAdapter dbAdapter;
    private MainApplication application;

    private int deltaX;
    private FrameLayout.LayoutParams sourceLayoutParams;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.screen_lock);

        init();
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
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) stone.getLayoutParams();
                    deltaX = x - layoutParams.leftMargin;
                    stone.setVisibility(View.GONE);
                    stonePress.setVisibility(View.VISIBLE);
                    sourceLayoutParams = (FrameLayout.LayoutParams) stonePress.getLayoutParams();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) stonePress.getLayoutParams();
                layoutParams.leftMargin = x - deltaX;
                stonePress.setLayoutParams(layoutParams);

                if (isAdSelected(x, y)) {
                    adSelect.setVisibility(View.VISIBLE);
                    stonePress.setVisibility(View.GONE);

                } else if (isLockSelected(x, y)) {
                    lockSelect.setVisibility(View.VISIBLE);
                    stonePress.setVisibility(View.GONE);
                } else {
                    adSelect.setVisibility(View.GONE);
                    lockSelect.setVisibility(View.GONE);
                }

                break;
            case MotionEvent.ACTION_UP:
                if (isAdSelected(x, y)) {
                    addGold(adGold);
                    finish();
                } else if (isLockSelected(x, y)) {
                    addGold(lockGold);
                    finish();
                } else {
                    stonePress.setLayoutParams(sourceLayoutParams);
                    stonePress.setVisibility(View.GONE);
                    stone.setVisibility(View.VISIBLE);
                }
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
        return stoneX <= x && stoneX + stoneWidth >= x && stoneY <= y && stoneY + stoneHeight >= y;
    }

    private boolean isAdSelected(int x, int y) {
        int adX = (int) ad.getX();
        int adY = (int) ad.getY();
        int adWidth = stone.getWidth();
        int adHeight = stone.getHeight();
        return adX <= x && adX + adWidth >= x && adY <= y && adY + adHeight >= y;
    }

    private boolean isLockSelected(int x, int y) {
        int lockX = (int) lock.getX();
        int lockY = (int) lock.getY();
        int lockWidth = lock.getWidth();
        int lockHeight = lock.getHeight();
        return lockX <= x && lockX + lockWidth >= x && lockY <= y && lockY + lockHeight >= y;
    }

    private void init() {
        dbAdapter = new DbAdapter(this);
        application = (MainApplication) getApplication();
        stone = (ImageView) findViewById(R.id.stone);
        stonePress = (ImageView) findViewById(R.id.stonePress);
        lock = (ImageView) findViewById(R.id.lock);
        ad = (ImageView) findViewById(R.id.ad);
        lockSelect = (ImageView) findViewById(R.id.lockSelect);
        adSelect = (ImageView) findViewById(R.id.adSelect);

        adGoldView = (TextView) findViewById(R.id.adGoldView);
        lockGoldView = (TextView) findViewById(R.id.lockGoldView);

        Random r = new Random();
        lockGold = r.nextInt(10);
        adGold = 10 - lockGold;
        lockGoldView.setText(lockGold != 0 ? lockGold + "城币" : "");
        adGoldView.setText(adGold != 0 ? adGold + "城币" : "");

        screen = (FrameLayout) findViewById(R.id.screen);
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
}