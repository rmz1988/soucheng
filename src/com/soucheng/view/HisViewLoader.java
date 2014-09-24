package com.soucheng.view;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.soucheng.activity.R;
import com.soucheng.activity.adapter.ShopListAdapter;
import com.soucheng.vo.Goods;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author lichen
 */
public class HisViewLoader extends ViewLoader {

	private ListView sellHisView;
	private TextView emptyTip;

	public HisViewLoader(Context context, View view) {
		super(context, view);
	}

	@Override
	public void load() {
		sellHisView = (ListView) view.findViewById(R.id.sellHisView);
		emptyTip = (TextView) view.findViewById(R.id.emptyTip);

		List<Goods> goodsList = loadBuyGoods();
		if (goodsList.isEmpty()) {
			sellHisView.setVisibility(View.GONE);
			emptyTip.setVisibility(View.VISIBLE);
		} else {
			sellHisView.setVisibility(View.VISIBLE);
			emptyTip.setVisibility(View.GONE);
			sellHisView.setAdapter(new ShopListAdapter(context, goodsList, true));
		}
	}

	//加载购买记录
	private List<Goods> loadBuyGoods() {
		Random r = new Random();
		int count = r.nextInt(10);
		Goods goods;
		List<Goods> goodsList = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			goods = new Goods();
			goods.setId(i);
			goods.setName("商品" + ((int) (Math.random() * 6) + 1) + "-" + r.nextInt(10));
			goods.setImageSource(R.drawable.logo);
			goods.setPrice(new DecimalFormat("0.00").format(Math.random() * 100 + 1));
			goods.setStockBalance(r.nextInt(500) + 1 + "");
			goods.setSellCount(r.nextInt(20) + 1 + "");
			goods.setSellDate(new Date());

			goodsList.add(goods);
		}

		return goodsList;
	}
}
