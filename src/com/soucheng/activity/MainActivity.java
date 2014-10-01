package com.soucheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import com.soucheng.activity.adapter.ViewPagerAdapter;
import com.soucheng.view.*;
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

	private ViewLoader homeViewLoader, realEstateViewLoader, readViewLoader, behindViewLoader,moreViewLoader;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//初始化导航按钮组件
		initButton();
		//初始化子页面
		initPageView();
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
		btnManager.addButton(realEstateBtn);
		btnManager.addButton(readBtn);
		btnManager.addButton(behindBtn);
		btnManager.addButton(moreBtn);
		btnManager.selected(R.id.homeBtn);
	}

	/**
	 * 初始化子页面
	 */
	private void initPageView() {
		pageView = (ViewPager) findViewById(R.id.pageView);
		LayoutInflater inflater = LayoutInflater.from(this);
		View homeView = inflater.inflate(R.layout.home, null);
		View realEstateView = inflater.inflate(R.layout.real_estate, null);
		View readView = inflater.inflate(R.layout.read, null);
		View behindView = inflater.inflate(R.layout.beind, null);
		View moreView = inflater.inflate(R.layout.more, null);
		btnManager.addViewButton(homeView.getId(), homeBtn);
		btnManager.addViewButton(realEstateView.getId(), realEstateBtn);
		btnManager.addViewButton(readView.getId(), readBtn);
		btnManager.addViewButton(behindView.getId(), behindBtn);
		btnManager.addViewButton(moreView.getId(), moreBtn);
		final List<View> viewList = new ArrayList<>();
		viewList.add(homeView);
		viewList.add(realEstateView);
		viewList.add(readView);
		viewList.add(behindView);
		viewList.add(moreView);
		pageView.setAdapter(new ViewPagerAdapter(viewList));
		pageView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageScrolled(int i, float v, int i2) {
			}

			@Override
			public void onPageSelected(int position) {
				btnManager.viewSelected(viewList.get(position).getId());
			}

			@Override
			public void onPageScrollStateChanged(int i) {

			}
		});

		homeViewLoader = new HomeViewLoader(this, homeView);
		realEstateViewLoader = new RealEstateViewLoader(this, realEstateView);
		readViewLoader = new ReadViewLoader(this, readView);
		behindViewLoader = new BehindViewLoader(this, behindView);
		moreViewLoader = new MoreViewLoader(this, moreView);
		homeViewLoader.load();
        realEstateViewLoader.load();
        readViewLoader.load();
        behindViewLoader.load();
		moreViewLoader.load();
	}

}
