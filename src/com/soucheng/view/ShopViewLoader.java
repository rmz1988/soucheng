package com.soucheng.view;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.soucheng.activity.R;
import com.soucheng.activity.adapter.ShopListAdapter;
import com.soucheng.vo.Goods;
import com.soucheng.widget.ImageTextButton;
import com.soucheng.widget.ImageTextButtonManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author lichen
 */
public class ShopViewLoader extends ViewLoader {

	private ImageTextButton shopBtn1;
	private ImageTextButton shopBtn2;
	private ImageTextButton shopBtn3;
	private ImageTextButton shopBtn4;
	private ImageTextButton shopBtn5;
	private ImageTextButton shopBtn6;

	private ListView goodsListView;
	private TextView emptyTipView;

	private ImageTextButtonManager manager;


	public ShopViewLoader(Context context, View view) {
		super(context, view);
	}

	@Override
	public void load() {
		initShopData();
		initButton();
	}

	private void initButton() {
		shopBtn1 = (ImageTextButton) view.findViewById(R.id.shopBtn1);
		shopBtn2 = (ImageTextButton) view.findViewById(R.id.shopBtn2);
		shopBtn3 = (ImageTextButton) view.findViewById(R.id.shopBtn3);
		shopBtn4 = (ImageTextButton) view.findViewById(R.id.shopBtn4);
		shopBtn5 = (ImageTextButton) view.findViewById(R.id.shopBtn5);
		shopBtn6 = (ImageTextButton) view.findViewById(R.id.shopBtn6);

		shopBtn1.setText(R.string.goods1);
		shopBtn2.setText(R.string.goods2);
		shopBtn3.setText(R.string.goods3);
		shopBtn4.setText(R.string.goods4);
		shopBtn5.setText(R.string.goods5);
		shopBtn6.setText(R.string.goods6);

		shopBtn1.setSelectedBackground(R.drawable.image_btn_normal);
		shopBtn1.setNormalBackground(R.drawable.image_btn_normal);
		shopBtn1.setSelectedColor(context.getResources().getColor(R.color.green));
		shopBtn1.setNormalColor(context.getResources().getColor(R.color.dark_gray));
		shopBtn1.setSelectedImageSource(R.drawable.logo_small);
		shopBtn1.setNormalImageSource(R.drawable.logo_small);
		shopBtn2.setSelectedBackground(R.drawable.image_btn_normal);
		shopBtn2.setNormalBackground(R.drawable.image_btn_normal);
		shopBtn2.setSelectedColor(context.getResources().getColor(R.color.green));
		shopBtn2.setNormalColor(context.getResources().getColor(R.color.dark_gray));
		shopBtn2.setSelectedImageSource(R.drawable.logo_small);
		shopBtn2.setNormalImageSource(R.drawable.logo_small);
		shopBtn3.setSelectedBackground(R.drawable.image_btn_normal);
		shopBtn3.setNormalBackground(R.drawable.image_btn_normal);
		shopBtn3.setSelectedColor(context.getResources().getColor(R.color.green));
		shopBtn3.setNormalColor(context.getResources().getColor(R.color.dark_gray));
		shopBtn3.setSelectedImageSource(R.drawable.logo_small);
		shopBtn3.setNormalImageSource(R.drawable.logo_small);
		shopBtn4.setSelectedBackground(R.drawable.image_btn_normal);
		shopBtn4.setNormalBackground(R.drawable.image_btn_normal);
		shopBtn4.setSelectedColor(context.getResources().getColor(R.color.green));
		shopBtn4.setNormalColor(context.getResources().getColor(R.color.dark_gray));
		shopBtn4.setSelectedImageSource(R.drawable.logo_small);
		shopBtn4.setNormalImageSource(R.drawable.logo_small);
		shopBtn5.setSelectedBackground(R.drawable.image_btn_normal);
		shopBtn5.setNormalBackground(R.drawable.image_btn_normal);
		shopBtn5.setSelectedColor(context.getResources().getColor(R.color.green));
		shopBtn5.setNormalColor(context.getResources().getColor(R.color.dark_gray));
		shopBtn5.setSelectedImageSource(R.drawable.logo_small);
		shopBtn5.setNormalImageSource(R.drawable.logo_small);
		shopBtn6.setSelectedBackground(R.drawable.image_btn_normal);
		shopBtn6.setNormalBackground(R.drawable.image_btn_normal);
		shopBtn6.setSelectedColor(context.getResources().getColor(R.color.green));
		shopBtn6.setNormalColor(context.getResources().getColor(R.color.dark_gray));
		shopBtn6.setSelectedImageSource(R.drawable.logo_small);
		shopBtn6.setNormalImageSource(R.drawable.logo_small);

		shopBtn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				manager.selected(R.id.shopBtn1);
				List<Goods> goodsList = loadGoods(1);
				if (goodsList.isEmpty()) {
					goodsListView.setVisibility(View.GONE);
					emptyTipView.setVisibility(View.VISIBLE);
				} else {
					goodsListView.setVisibility(View.VISIBLE);
					emptyTipView.setVisibility(View.GONE);
					goodsListView.setAdapter(new ShopListAdapter(context, goodsList, false));
				}
			}
		});
		shopBtn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				manager.selected(R.id.shopBtn2);
				List<Goods> goodsList = loadGoods(2);
				if (goodsList.isEmpty()) {
					goodsListView.setVisibility(View.GONE);
					emptyTipView.setVisibility(View.VISIBLE);
				} else {
					goodsListView.setVisibility(View.VISIBLE);
					emptyTipView.setVisibility(View.GONE);
					goodsListView.setAdapter(new ShopListAdapter(context, goodsList, false));
				}
			}
		});
		shopBtn3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				manager.selected(R.id.shopBtn3);
				List<Goods> goodsList = loadGoods(3);
				if (goodsList.isEmpty()) {
					goodsListView.setVisibility(View.GONE);
					emptyTipView.setVisibility(View.VISIBLE);
				} else {
					goodsListView.setVisibility(View.VISIBLE);
					emptyTipView.setVisibility(View.GONE);
					goodsListView.setAdapter(new ShopListAdapter(context, goodsList, false));
				}
			}
		});
		shopBtn4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				manager.selected(R.id.shopBtn4);
				List<Goods> goodsList = loadGoods(4);
				if (goodsList.isEmpty()) {
					goodsListView.setVisibility(View.GONE);
					emptyTipView.setVisibility(View.VISIBLE);
				} else {
					goodsListView.setVisibility(View.VISIBLE);
					emptyTipView.setVisibility(View.GONE);
					goodsListView.setAdapter(new ShopListAdapter(context, goodsList, false));
				}
			}
		});
		shopBtn5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				manager.selected(R.id.shopBtn5);
				List<Goods> goodsList = loadGoods(5);
				if (goodsList.isEmpty()) {
					goodsListView.setVisibility(View.GONE);
					emptyTipView.setVisibility(View.VISIBLE);
				} else {
					goodsListView.setVisibility(View.VISIBLE);
					emptyTipView.setVisibility(View.GONE);
					goodsListView.setAdapter(new ShopListAdapter(context, goodsList, false));
				}
			}
		});
		shopBtn6.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				manager.selected(R.id.shopBtn6);
				List<Goods> goodsList = loadGoods(6);
				if (goodsList.isEmpty()) {
					goodsListView.setVisibility(View.GONE);
					emptyTipView.setVisibility(View.VISIBLE);
				} else {
					goodsListView.setVisibility(View.VISIBLE);
					emptyTipView.setVisibility(View.GONE);
					goodsListView.setAdapter(new ShopListAdapter(context, goodsList, false));
				}
			}
		});

		manager = new ImageTextButtonManager();
		manager.addButton(shopBtn1);
		manager.addButton(shopBtn2);
		manager.addButton(shopBtn3);
		manager.addButton(shopBtn4);
		manager.addButton(shopBtn5);
		manager.addButton(shopBtn6);
		shopBtn1.performClick();
	}

	private void initShopData() {
		goodsListView = (ListView) view.findViewById(R.id.goodsListView);
		emptyTipView = (TextView) view.findViewById(R.id.emptyTip);
	}

	//加载商品数据
	private List<Goods> loadGoods(int goodsType) {
		List<Goods> goodsList = new ArrayList<>();
		Random r = new Random();
		int maxStockBalance = 500;
		int maxSellCount = 500;
		double maxPrice = 100d;

		Goods goods;
		int goodsCount = r.nextInt(10);
		for (int i = 0; i < goodsCount; i++) {
			goods = new Goods();
			goods.setId(i);
			goods.setName("商品" + goodsType + "-" + i);
			goods.setImageSource(R.drawable.logo);
			goods.setPrice(new DecimalFormat("0.00").format(Math.random() * maxPrice + 1));
			goods.setStockBalance(r.nextInt(maxStockBalance) + 1 + "");
			goods.setSellCount(r.nextInt(maxSellCount) + 1 + "");
			goods.setSellDate(new Date());

			goodsList.add(goods);
		}

		return goodsList;
	}
}
