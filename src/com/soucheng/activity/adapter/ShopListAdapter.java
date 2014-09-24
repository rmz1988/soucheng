package com.soucheng.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.soucheng.activity.R;
import com.soucheng.vo.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表适配器
 *
 * @author lichen
 */
public class ShopListAdapter extends BaseAdapter {

	private Context context;
	private List<Goods> goodsList = new ArrayList<>();
	private boolean isSellHis;

	public ShopListAdapter(Context context, List<Goods> goodsList, boolean isSellHis) {
		this.context = context;
		this.goodsList = goodsList;
		this.isSellHis = isSellHis;
	}

	@Override
	public int getCount() {
		return goodsList.size();
	}

	@Override
	public Object getItem(int position) {
		return goodsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(context).inflate(R.layout.good_item, null);
		ImageView iconView = (ImageView) convertView.findViewById(R.id.iconView);
		TextView nameView = (TextView) convertView.findViewById(R.id.nameView);
		TextView detailView = (TextView) convertView.findViewById(R.id.detailView);
		TextView priceView = (TextView) convertView.findViewById(R.id.priceView);

		Goods goods = goodsList.get(position);
		iconView.setImageResource(R.drawable.logo);
		nameView.setText(goods.getName());
		if (isSellHis) {
			detailView.setText("于" + goods.getSellDateString() + "\n购买" + goods.getSellCount() + "件");
		} else {
			detailView.setText("库存：" + goods.getStockBalance() + "件，已销售：" + goods.getSellCount() + "件");
		}
		priceView.setText(goods.getPrice() + "元");

		return convertView;
	}
}
