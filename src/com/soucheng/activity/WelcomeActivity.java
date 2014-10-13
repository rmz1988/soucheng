package com.soucheng.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.soucheng.activity.adapter.ViewPagerAdapter;
import com.soucheng.application.MainApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 欢迎页面
 * Created by lichen on 2014/10/1.
 */
public class WelcomeActivity extends Activity {

    private ViewPager viewPager;
    private ImageView[] dotViews;
    private Button enterBtn;
    private MainApplication application;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        application = (MainApplication) getApplication();

        viewPager = (ViewPager) findViewById(R.id.welcomeView);
        LayoutInflater inflater = LayoutInflater.from(this);
        View welcomeView1 = inflater.inflate(R.layout.welcome1, null);
        View welcomeView2 = inflater.inflate(R.layout.welcome2, null);
        View welcomeView3 = inflater.inflate(R.layout.welcome3, null);
        View welcomeView4 = inflater.inflate(R.layout.welcome4, null);

        List<View> viewList = new ArrayList<>();
        viewList.add(welcomeView1);
        viewList.add(welcomeView2);
        viewList.add(welcomeView3);
        viewList.add(welcomeView4);

        enterBtn = (Button) welcomeView4.findViewById(R.id.centerBtn);
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, application.getLoginUser() != null ? MainActivity.class : LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                finish();
            }
        });

        dotViews = new ImageView[]{(ImageView) findViewById(R.id.dot1), (ImageView) findViewById(R.id.dot2), (ImageView) findViewById(R.id.dot3), (ImageView) findViewById(R.id.dot4)};
        dotViews[0].setImageDrawable(getResources().getDrawable(R.drawable.dark_gray_dot));

        viewPager.setAdapter(new ViewPagerAdapter(this,viewList,null));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                for (int j = 0; j < dotViews.length; j++) {
                    if (i == j) {
                        dotViews[j].setImageDrawable(getResources().getDrawable(R.drawable.dark_gray_dot));
                    } else {
                        dotViews[j].setImageDrawable(getResources().getDrawable(R.drawable.light_gray_dot));
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}