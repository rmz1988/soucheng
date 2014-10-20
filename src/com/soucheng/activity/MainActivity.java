package com.soucheng.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import com.soucheng.activity.adapter.ViewPagerAdapter;
import com.soucheng.application.MainApplication;
import com.soucheng.service.LocationService;
import com.soucheng.service.ScreenLockService;
import com.soucheng.view.ViewLoader;
import com.soucheng.vo.MenuIcon;
import com.soucheng.widget.ButtonManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
public class MainActivity extends Activity {

    private static final int HOME_VIEW_INDEX = 0;
    private static final int REAL_ESTATE_VIEW_INDEX = 1;
    private static final int READ_VIEW_INDEX = 2;
    private static final int BEHIND_VIEW_INDEX = 3;
    private static final int MORE_VIEW_INDEX = 4;

    private Button homeBtn;
    private Button realEstateBtn;
    private Button readBtn;
    private Button behindBtn;
    private Button moreBtn;

    private ViewPager pageView;

    private ButtonManager btnManager;

    private ViewLoader homeViewLoader, realEstateViewLoader, readViewLoader, behindViewLoader, moreViewLoader;

    private boolean canExit;

    private MainApplication application;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        startUserService();
        //初始化导航按钮组件
        initButton();
        //初始化子页面
        initPageView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == OperCode.ACCOUNT_LOGOUT_CODE) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

            Intent locationServiceIntent = new Intent(this, LocationService.class);
            stopService(locationServiceIntent);
            Intent screenLockServiceIntent = new Intent(this, ScreenLockService.class);
            stopService(screenLockServiceIntent);

            finish();
        }
    }

    private void initButton() {
        homeBtn = (Button) findViewById(R.id.homeBtn);
        realEstateBtn = (Button) findViewById(R.id.realEstateBtn);
        readBtn = (Button) findViewById(R.id.readBtn);
        behindBtn = (Button) findViewById(R.id.behindBtn);
        moreBtn = (Button) findViewById(R.id.moreBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btnManager.selected(R.id.homeBtn);
                pageView.setCurrentItem(HOME_VIEW_INDEX);
            }
        });
        realEstateBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btnManager.selected(R.id.realEstateBtn);
                pageView.setCurrentItem(REAL_ESTATE_VIEW_INDEX);
            }
        });
        readBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btnManager.selected(R.id.readBtn);
                pageView.setCurrentItem(READ_VIEW_INDEX);
            }
        });
        behindBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btnManager.selected(R.id.behindBtn);
                pageView.setCurrentItem(BEHIND_VIEW_INDEX);
            }
        });
        moreBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btnManager.selected(R.id.moreBtn);
                pageView.setCurrentItem(MORE_VIEW_INDEX);
            }
        });

        btnManager = new ButtonManager(this);
        btnManager.addButton(homeBtn);
        btnManager.addButtonImg(homeBtn.getId(), new MenuIcon(getResources().getDrawable(R.drawable.home_normal),
                getResources().getDrawable(R.drawable.home_select)));
        btnManager.addButton(realEstateBtn);
        btnManager.addButtonImg(realEstateBtn.getId(), new MenuIcon(getResources().getDrawable(R.drawable.house_normal),
                getResources().getDrawable(R.drawable.house_select)));
        btnManager.addButton(readBtn);
        btnManager.addButtonImg(readBtn.getId(), new MenuIcon(getResources().getDrawable(R.drawable.read_normal),
                getResources().getDrawable(R.drawable.read_select)));
        btnManager.addButton(behindBtn);
        btnManager.addButtonImg(behindBtn.getId(), new MenuIcon(getResources().getDrawable(R.drawable.behind_normal),
                getResources().getDrawable(R.drawable.behind_select)));
        btnManager.addButton(moreBtn);
        btnManager.addButtonImg(moreBtn.getId(), new MenuIcon(getResources().getDrawable(R.drawable.more_normal),
                getResources().getDrawable(R.drawable.more_select)));
        btnManager.selected(R.id.homeBtn);
    }

    /**
     * 初始化子页面
     */
    private void initPageView() {
        pageView = (ViewPager) findViewById(R.id.pageView);
        /*LayoutInflater inflater = LayoutInflater.from(this);
        View homeView = inflater.inflate(R.layout.home, null);
		View realEstateView = inflater.inflate(R.layout.real_estate, null);
		View readView = inflater.inflate(R.layout.read, null);
		View behindView = inflater.inflate(R.layout.behind, null);
		View moreView = inflater.inflate(R.layout.more, null);*/

        btnManager.addViewButton(0, homeBtn);
        btnManager.addViewButton(1, realEstateBtn);
        btnManager.addViewButton(2, readBtn);
        btnManager.addViewButton(3, behindBtn);
        btnManager.addViewButton(4, moreBtn);
        final List<View> viewList = new ArrayList<>();
        viewList.add(null);
        viewList.add(null);
        viewList.add(null);
        viewList.add(null);
        viewList.add(null);
        List<Integer> layoutList = new ArrayList<>();
        layoutList.add(R.layout.home);
        layoutList.add(R.layout.real_estate);
        layoutList.add(R.layout.read);
        layoutList.add(R.layout.behind);
        layoutList.add(R.layout.more);

        pageView.setAdapter(new ViewPagerAdapter(this, viewList, layoutList));
        pageView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int position) {
                btnManager.viewSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
/*
        homeViewLoader = new HomeViewLoader(this, homeView);
		realEstateViewLoader = new RealEstateViewLoader(this, realEstateView);
		readViewLoader = new ReadViewLoader(this, readView);
		behindViewLoader = new BehindViewLoader(this, behindView);
		moreViewLoader = new MoreViewLoader(this, moreView);
		homeViewLoader.load();
		realEstateViewLoader.load();
		readViewLoader.load();
		behindViewLoader.load();
		moreViewLoader.load();*/
    }

    /**
     * 开启定位服务
     */
    private void startUserService() {
        Intent locationIntent = new Intent(this, LocationService.class);
        startService(locationIntent);
        Intent lockIntent = new Intent(this, ScreenLockService.class);
        startService(lockIntent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            pageView.removeAllViews();
            System.gc();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        pageView.removeAllViews();
        System.gc();
        super.onBackPressed();
        overridePendingTransition(0, R.anim.left_out);
        /*if (canExit) {
            finish();
        } else {
            canExit = true;
            Toast.makeText(this, R.string.exit_tip, Toast.LENGTH_SHORT).show();
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    canExit = false;
                }
            }.start();
        }*/
    }

    @Override
    public void onDestroy() {
        Intent intent = new Intent();
        intent.setAction("restart.screen.lock.service");
        sendBroadcast(intent);
        super.onDestroy();
//        intent = new Intent();
//        intent.setAction("restart.location.service");
//        sendBroadcast(intent);
    }
}
