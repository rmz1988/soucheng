package com.soucheng.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.soucheng.activity.R;
import com.soucheng.activity.adapter.ViewPagerAdapter;
import com.soucheng.application.MainApplication;
import com.soucheng.db.DbAdapter;
import com.soucheng.dialog.InviteFriendDialog;
import com.soucheng.vo.User;

import java.text.DecimalFormat;
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
	private TextView broadcastView;
	private TextView goldView;
	private TextView moneyView;
	private ImageButton exchangeBtn;
	private ImageButton donateBtn;
	private ImageButton buyBtn;
	private ImageButton inviteFriendBtn;

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

	private MainApplication application;

	public HomeViewLoader(Context context, View view) {
		super(context, view);
		dbAdapter = new DbAdapter(context);
		application = (MainApplication) context.getApplicationContext();
	}

	@Override
	public void load() {
		adViewPager = (ViewPager) view.findViewById(R.id.adPageView);
		selectedPot = (ImageView) view.findViewById(R.id.selectedPot);

		exchangeBtn = (ImageButton) view.findViewById(R.id.exchangeBtn);
		donateBtn = (ImageButton) view.findViewById(R.id.donateBtn);
		buyBtn = (ImageButton) view.findViewById(R.id.buyBtn);
		inviteFriendBtn = (ImageButton) view.findViewById(R.id.inviteFriendBtn);
		inviteTipView = (TextView) view.findViewById(R.id.inviteTipView);
		String inviteTip = inviteTipView.getText().toString();
		SpannableStringBuilder builder = new SpannableStringBuilder(inviteTip);
		ForegroundColorSpan span = new ForegroundColorSpan(Color.rgb(0xFE, 0x59, 0x00));
		builder.setSpan(span, inviteTip.length() - 2, inviteTip.length(),
				Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
		inviteTipView.setText(builder);

		exchangeBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		buyBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		donateBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		inviteFriendBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				InviteFriendDialog dialog = new InviteFriendDialog(context);
				dialog.show();
			}
		});

		loadMoneyInfo();

		loadBroadcast();

		//加载广告图片
		loadAdImages();
	}

	private void loadMoneyInfo() {
		goldView = (TextView) view.findViewById(R.id.goldView);
		moneyView = (TextView) view.findViewById(R.id.moneyView);

		User user = application.getLoginUser();
		if (user != null) {
			goldView.setText(String.valueOf(user.getGold()) + "枚");
			moneyView.setText(
					new DecimalFormat("0.00").format(Double.parseDouble(String.valueOf(user.getGold())) / 100) + "元");
		}
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

		adViewPager.setAdapter(new ViewPagerAdapter(context, viewList, null));
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
