package com.soucheng.view;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.soucheng.activity.R;
import com.soucheng.activity.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页加载类
 *
 * @author lichen
 */
public class HomeViewLoader extends ViewLoader {

	private ViewPager adViewPager;
	private FrameLayout potFrame;
	private ImageView selectedPot;

	public HomeViewLoader(Context context, View view) {
		super(context, view);
	}

	@Override
	public void load() {
		adViewPager = (ViewPager) view.findViewById(R.id.adPageView);
		potFrame = (FrameLayout) view.findViewById(R.id.potFrame);
		selectedPot = (ImageView) view.findViewById(R.id.selectedPot);

		//加载广告图片
		loadAdImages();
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

			@Override
			public void onPageScrolled(int i, float v, int i2) {

			}

			@Override
			public void onPageSelected(int position) {

			}

			@Override
			public void onPageScrollStateChanged(int i) {

			}
		});
	}
}
