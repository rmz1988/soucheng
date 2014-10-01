package com.soucheng.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.soucheng.activity.R;
import com.soucheng.activity.adapter.ViewPagerAdapter;
import com.soucheng.db.DbAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页加载类
 *
 * @author lichen
 */
public class HomeViewLoader extends ViewLoader {

    private ViewPager adViewPager;
    private ImageView selectedPot;

    private TextView broadcastView;
    private TextView goldView;
    private TextView moneyView;
    private Button exchangeBtn;
    private Button donateBtn;
    private Button inviteFriendBtn;

    private int[] broadcastTexts;

    private DbAdapter dbAdapter;

    private Handler handler = new Handler() {

        private int index = 0;

        @Override
        public void handleMessage(Message msg) {
            if (index == 0) {
                index = 1;
            } else {
                index = 0;
            }
            broadcastView.setText(broadcastTexts[index]);
        }
    };

    public HomeViewLoader(Context context, View view) {
        super(context, view);
        dbAdapter = new DbAdapter(context);
    }

    @Override
    public void load() {
        adViewPager = (ViewPager) view.findViewById(R.id.adPageView);
        selectedPot = (ImageView) view.findViewById(R.id.selectedPot);

        exchangeBtn = (Button) view.findViewById(R.id.exchangeBtn);
        donateBtn = (Button) view.findViewById(R.id.donateBtn);
        inviteFriendBtn = (Button) view.findViewById(R.id.inviteFriendBtn);

        loadMoneyInfo();

        loadBroadcast();

        //加载广告图片
        loadAdImages();
    }

    private void loadMoneyInfo() {
        goldView = (TextView) view.findViewById(R.id.goldView);
        moneyView = (TextView) view.findViewById(R.id.moneyView);

    }

    private void loadBroadcast() {
        broadcastView = (TextView) view.findViewById(R.id.broadcastView);
        broadcastTexts = new int[]{R.string.broadcast1, R.string.broadcast2};
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(0);
                }
            }
        }.start();
    }

    private void loadAdImages() {
        final List<View> viewList = new ArrayList<>();

        ImageView adView1 = new ImageView(context);
        adView1.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT));
        adView1.setAdjustViewBounds(true);
        adView1.setScaleType(ImageView.ScaleType.FIT_XY);
        adView1.setImageResource(R.drawable.ad1);
        viewList.add(adView1);

        ImageView adView2 = new ImageView(context);
        adView2.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT));
        adView2.setAdjustViewBounds(true);
        adView2.setScaleType(ImageView.ScaleType.FIT_XY);
        adView2.setImageResource(R.drawable.ad2);
        viewList.add(adView2);

        ImageView adView3 = new ImageView(context);
        adView3.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT));
        adView3.setAdjustViewBounds(true);
        adView3.setScaleType(ImageView.ScaleType.FIT_XY);
        adView3.setImageResource(R.drawable.ad3);
        viewList.add(adView3);

        ImageView adView4 = new ImageView(context);
        adView4.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT));
        adView4.setAdjustViewBounds(true);
        adView4.setScaleType(ImageView.ScaleType.FIT_XY);
        adView4.setImageResource(R.drawable.ad4);
        viewList.add(adView4);

        adViewPager.setAdapter(new ViewPagerAdapter(viewList));
        adViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int lastValue = -1;
            private boolean isLeft;
            private boolean isRight;

            @Override
            public void onPageScrolled(int i, float v, int i2) {
                if (i2 != 0) {
                    if (i2 > lastValue) {//左滑
                        isLeft = true;
                        isRight = false;
                    } else if (i2 < lastValue) {//右滑
                        isLeft = false;
                        isRight = true;
                    }

                    lastValue = i2;
                }

            }

            @Override
            public void onPageSelected(int position) {
                //pageChange = true;
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) selectedPot.getLayoutParams();
                if (isLeft) {
                    layoutParams
                            .setMargins(layoutParams.leftMargin + 38, layoutParams.topMargin, layoutParams.rightMargin,
                                    layoutParams.bottomMargin);
                } else if (isRight) {
                    layoutParams
                            .setMargins(layoutParams.leftMargin - 38, layoutParams.topMargin, layoutParams.rightMargin,
                                    layoutParams.bottomMargin);
                }
                selectedPot.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
