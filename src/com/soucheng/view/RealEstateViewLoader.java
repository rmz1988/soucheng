package com.soucheng.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import com.soucheng.activity.R;
import com.soucheng.activity.RealEstateDetailActivity;
import com.soucheng.activity.SearchActivity;
import com.soucheng.activity.adapter.ViewPagerAdapter;
import com.soucheng.application.MainApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lichen
 */
public class RealEstateViewLoader extends ViewLoader {

	//    private Spinner locationSpinner;
	//    private SearchView searchView;

	private EditText searchEdit;
	private ImageButton locationBtn;

	private ImageButton houseBtn;
	private ImageButton apartmentBtn;
	private ImageButton commercialBtn;
	private ImageButton workBtn;
	private ImageButton rentBtn;

	private ViewPager buildingViewPager;
	private ImageView buildingBroadcast;

	private ImageView[] dotViews;

	public RealEstateViewLoader(Context context, View view) {
		super(context, view);
	}

	@Override
	public void load() {
	   /* locationSpinner = (Spinner) view.findViewById(R.id.locationSpinner);
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.locations, R.layout.location_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);*/

       /* searchView = (SearchView) view.findViewById(R.id.searchView);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(context, SearchActivity.class);
                intent.putExtra("search", query);
                context.startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });*/

		searchEdit = (EditText) view.findViewById(R.id.searchEdit);
		locationBtn = (ImageButton) view.findViewById(R.id.locationBtn);

		initImageBtn();
		initBuildingView();
		initBuildingBroadcast();

	}

	private void initImageBtn() {
		houseBtn = (ImageButton) view.findViewById(R.id.houseBtn);
		apartmentBtn = (ImageButton) view.findViewById(R.id.apartmentBtn);
		commercialBtn = (ImageButton) view.findViewById(R.id.commercialBtn);
		workBtn = (ImageButton) view.findViewById(R.id.workBtn);
		rentBtn = (ImageButton) view.findViewById(R.id.rentBtn);

		houseBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, RealEstateDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("url", "http://m.fang.com/xf/jn/pu0/");
				intent.putExtras(bundle);
				context.startActivity(intent);
			}
		});
		apartmentBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, RealEstateDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("url",
						"http://m.fang.com/search.d?m=search&type=0&keyword=%B9%AB%D4%A2&city=jn&r=0.7301192109007388");
				intent.putExtras(bundle);
				context.startActivity(intent);
			}
		});
		commercialBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, RealEstateDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("url", "http://m.fang.com/xf/jn/pu2/");
				intent.putExtras(bundle);
				context.startActivity(intent);
			}
		});
		workBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, RealEstateDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("url", "http://m.fang.com/xf/jn/pu1/");
				intent.putExtras(bundle);
				context.startActivity(intent);
			}
		});
		rentBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, RealEstateDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("url", "http://m.fang.com/zf/jn/");
				intent.putExtras(bundle);
				context.startActivity(intent);
			}
		});
	}

	private void initBuildingView() {
		buildingViewPager = (ViewPager) view.findViewById(R.id.buildingViewPager);
		List<View> viewList = new ArrayList<>();
		ImageView adView1 = new ImageView(context);
		adView1.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.MATCH_PARENT));
		adView1.setAdjustViewBounds(true);
		adView1.setScaleType(ImageView.ScaleType.FIT_XY);
		adView1.setImageBitmap(((MainApplication) context.getApplicationContext()).loadBitmap(R.drawable.behind_ad,2));
		viewList.add(adView1);

		ImageView adView2 = new ImageView(context);
		adView2.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.MATCH_PARENT));
		adView2.setAdjustViewBounds(true);
		adView2.setScaleType(ImageView.ScaleType.FIT_XY);
		adView2.setImageResource(R.drawable.ad2);
		viewList.add(adView2);

		dotViews = new ImageView[]{(ImageView) view.findViewById(R.id.dot1), (ImageView) view.findViewById(R.id.dot2)};
		dotViews[0].setImageDrawable(context.getResources().getDrawable(R.drawable.dark_gray_dot));

		buildingViewPager.setAdapter(new ViewPagerAdapter(context,viewList,null));
		buildingViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageScrolled(int i, float v, int i2) {

			}

			@Override
			public void onPageSelected(int i) {
				for (int j = 0; j < dotViews.length; j++) {
					if (i == j) {
						dotViews[j].setImageDrawable(context.getResources().getDrawable(R.drawable.dark_gray_dot));
					} else {
						dotViews[j].setImageDrawable(context.getResources().getDrawable(R.drawable.light_gray_dot));
					}
				}
			}

			@Override
			public void onPageScrollStateChanged(int i) {

			}
		});
	}

	private void initBuildingBroadcast() {
		buildingBroadcast = (ImageView) view.findViewById(R.id.buildingBroadcast);
		buildingBroadcast.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, RealEstateDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("url", "http://m.fang.com");
				intent.putExtras(bundle);
				context.startActivity(intent);
			}
		});
	}

}
