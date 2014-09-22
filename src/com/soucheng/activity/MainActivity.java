package com.soucheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;
import com.soucheng.activity.adapter.ViewPagerAdapter;
import com.soucheng.view.HisViewLoader;
import com.soucheng.view.HomeViewLoader;
import com.soucheng.view.MoreViewLoader;
import com.soucheng.view.ShopViewLoader;
import com.soucheng.view.ViewLoader;
import com.soucheng.widget.ImageTextButton;
import com.soucheng.widget.ImageTextButtonManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
public class MainActivity extends Activity {

	private static final int HOME_VIEW_INDEX = 0;
	private static final int SHOP_VIEW_INDEX = 1;
	private static final int HIS_VIEW_INDEX = 2;
	private static final int MORE_VIEW_INDEX = 3;

	private ImageTextButton homeBtn;
	private ImageTextButton shopBtn;
	private ImageTextButton hisBtn;
	private ImageTextButton moreBtn;

	private ViewPager pageView;

	private ImageTextButtonManager btnManager;

	private ViewLoader homeViewLoader, shopViewLoader, hisViewLoader, moreViewLoader;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//初始化导航按钮组件
		initImageTextButton();
		//初始化子页面
		initPageView();
	}

	private void initImageTextButton() {
		homeBtn = (ImageTextButton) findViewById(R.id.homeBtn);
		shopBtn = (ImageTextButton) findViewById(R.id.shopBtn);
		hisBtn = (ImageTextButton) findViewById(R.id.hisBtn);
		moreBtn = (ImageTextButton) findViewById(R.id.moreBtn);

		homeBtn.setNormalBackground(R.drawable.image_btn_normal);
		shopBtn.setNormalBackground(R.drawable.image_btn_normal);
		hisBtn.setNormalBackground(R.drawable.image_btn_normal);
		moreBtn.setNormalBackground(R.drawable.image_btn_normal);
		homeBtn.setNormalColor(getResources().getColor(R.color.light_gray));
		shopBtn.setNormalColor(getResources().getColor(R.color.light_gray));
		hisBtn.setNormalColor(getResources().getColor(R.color.light_gray));
		moreBtn.setNormalColor(getResources().getColor(R.color.light_gray));
		homeBtn.setNormalImageSource(R.drawable.home_normal);
		shopBtn.setNormalImageSource(R.drawable.shop_normal);
		hisBtn.setNormalImageSource(R.drawable.his_normal);
		moreBtn.setNormalImageSource(R.drawable.more_normal);
		homeBtn.setSelectedBackground(R.drawable.image_btn_selected);
		shopBtn.setSelectedBackground(R.drawable.image_btn_selected);
		hisBtn.setSelectedBackground(R.drawable.image_btn_selected);
		moreBtn.setSelectedBackground(R.drawable.image_btn_selected);
		homeBtn.setSelectedColor(getResources().getColor(R.color.green));
		shopBtn.setSelectedColor(getResources().getColor(R.color.green));
		hisBtn.setSelectedColor(getResources().getColor(R.color.green));
		moreBtn.setSelectedColor(getResources().getColor(R.color.green));
		homeBtn.setSelectedImageSource(R.drawable.home_selected);
		shopBtn.setSelectedImageSource(R.drawable.shop_selected);
		hisBtn.setSelectedImageSource(R.drawable.his_selected);
		moreBtn.setSelectedImageSource(R.drawable.more_selected);

		homeBtn.setText(R.string.home_btn_text);
		shopBtn.setText(R.string.shop_btn_text);
		hisBtn.setText(R.string.his_btn_text);
		moreBtn.setText(R.string.more_btn_text);

		homeBtn.setSort(1);
		shopBtn.setSort(2);
		hisBtn.setSort(3);
		moreBtn.setSort(4);

		homeBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//setFlipperAnimation(R.id.homeBtn);
				btnManager.selected(R.id.homeBtn);
				pageView.setCurrentItem(HOME_VIEW_INDEX);
			}
		});
		shopBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//setFlipperAnimation(R.id.shopBtn);
				btnManager.selected(R.id.shopBtn);
				pageView.setCurrentItem(SHOP_VIEW_INDEX);
			}
		});
		hisBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//setFlipperAnimation(R.id.hisBtn);
				btnManager.selected(R.id.hisBtn);
				pageView.setCurrentItem(HIS_VIEW_INDEX);
			}
		});
		moreBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//setFlipperAnimation(R.id.moreBtn);
				btnManager.selected(R.id.moreBtn);
				pageView.setCurrentItem(MORE_VIEW_INDEX);
			}
		});

		btnManager = new ImageTextButtonManager();
		btnManager.addButton(homeBtn);
		btnManager.addButton(shopBtn);
		btnManager.addButton(hisBtn);
		btnManager.addButton(moreBtn);
		btnManager.selected(R.id.homeBtn);
	}

	/**
	 * 设置ViewFlipper的动画
	 *
	 * @param btnId 按钮id
	 */
	/*private void setFlipperAnimation(int btnId) {
		if (btnManager.isShowFrontPage(btnId)) {
			pageView.setInAnimation(this, R.anim.left_in);
			pageView.setOutAnimation(this, R.anim.right_out);
		} else {
			pageView.setInAnimation(this, R.anim.right_in);
			pageView.setOutAnimation(this, R.anim.left_out);
		}
	}*/

	/**
	 * 初始化子页面
	 */
	private void initPageView() {
		pageView = (ViewPager) findViewById(R.id.pageView);
		LayoutInflater inflater = LayoutInflater.from(this);
		View homeView = inflater.inflate(R.layout.home, null);
		View shopView = inflater.inflate(R.layout.shop, null);
		View hisView = inflater.inflate(R.layout.his, null);
		View moreView = inflater.inflate(R.layout.more, null);
		btnManager.addViewButton(homeView.getId(), homeBtn);
		btnManager.addViewButton(shopView.getId(), shopBtn);
		btnManager.addViewButton(hisView.getId(), hisBtn);
		btnManager.addViewButton(moreView.getId(), moreBtn);
		final List<View> viewList = new ArrayList<>();
		viewList.add(homeView);
		viewList.add(shopView);
		viewList.add(hisView);
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
		shopViewLoader = new ShopViewLoader(this, shopView);
		hisViewLoader = new HisViewLoader(this, hisView);
		moreViewLoader = new MoreViewLoader(this, moreView);
		homeViewLoader.load();
		shopViewLoader.load();
		hisViewLoader.load();
		moreViewLoader.load();
	}

}
