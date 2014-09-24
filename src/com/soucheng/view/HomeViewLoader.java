package com.soucheng.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
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
	private ImageView selectedPot;

	private TextView inviteTipView;
	private TextView balanceView;
	private TextView incomeView;
	private Button inviteFriendBtn;

	public HomeViewLoader(Context context, View view) {
		super(context, view);
	}

	@Override
	public void load() {
		adViewPager = (ViewPager) view.findViewById(R.id.adPageView);
		selectedPot = (ImageView) view.findViewById(R.id.selectedPot);
		balanceView = (TextView) view.findViewById(R.id.balanceView);
		incomeView = (TextView) view.findViewById(R.id.incomeView);
		inviteFriendBtn = (Button) view.findViewById(R.id.inviteFriendBtn);

		inviteTipView = (TextView) view.findViewById(R.id.inviteTipView);
		String tip = inviteTipView.getText().toString();
		SpannableStringBuilder textBuilder = new SpannableStringBuilder(tip);
		textBuilder.setSpan(new ForegroundColorSpan(Color.rgb(39, 186, 42)), tip.length() - 2, tip.length(),
				Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		inviteTipView.setText(textBuilder);

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

			private int lastMarginLeft = -1;
			private int lastValue = -1;
			private float lastPercent = 0.0f;
			private boolean isLeft;
			private boolean isRight;
			private boolean pageChange;

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

				/*if (v != 0.0f) {
				    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) selectedPot.getLayoutParams();
					if (isLeft) {
						layoutParams.setMargins(layoutParams.leftMargin + (int) (38 * Math.abs(v - lastPercent)),
								layoutParams.topMargin,
								layoutParams.rightMargin, layoutParams.bottomMargin);
					} else if (isRight) {
						layoutParams.setMargins(layoutParams.leftMargin - (int) (38 * Math.abs(v - lastPercent)),
								layoutParams.topMargin,
								layoutParams.rightMargin, layoutParams.bottomMargin);
					}
					System.out.println(v);
					selectedPot.setLayoutParams(layoutParams);

					lastPercent = v;
				}*/
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
				/*FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) selectedPot.getLayoutParams();
				if (i == 1 && lastMarginLeft == -1) {
					lastMarginLeft = layoutParams.leftMargin;
				}

				if (i == 0) {
					if (pageChange) {
						if (isLeft) {
							layoutParams
									.setMargins(lastMarginLeft + 38, layoutParams.topMargin, layoutParams.rightMargin,
											layoutParams.bottomMargin);
						} else if (isRight) {
							layoutParams
									.setMargins(lastMarginLeft - 38, layoutParams.topMargin, layoutParams.rightMargin,
											layoutParams.bottomMargin);
						}
					} else {
						layoutParams.setMargins(lastMarginLeft, layoutParams.topMargin, layoutParams.rightMargin,
								layoutParams.bottomMargin);
					}
					selectedPot.setLayoutParams(layoutParams);

					pageChange = false;
					lastMarginLeft = -1;
					isLeft = isRight = false;
				}*/
			}
		});
	}
}
