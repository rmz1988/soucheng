package com.soucheng.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.soucheng.activity.R;
import com.soucheng.vo.Address;

import java.util.List;

/**
 * 地址列表适配器
 *
 * @author lichen
 */
public class AddressListAdapter extends BaseAdapter {

	private List<Address> addressList;
	private Context context;

	public AddressListAdapter(Context context, List<Address> addressList) {
		this.addressList = addressList;
		this.context = context;
	}


	@Override
	public int getCount() {
		return addressList.size();
	}

	@Override
	public Object getItem(int position) {
		return addressList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, null);
		TextView nameView = (TextView) convertView.findViewById(android.R.id.text1);
		TextView detailView = (TextView) convertView.findViewById(android.R.id.text2);
		Address address = addressList.get(position);

		nameView.setText(address.getName());
		detailView.setText(address.getProvince() + "," + address.getCity() + "," + address.getDetail());

		return convertView;
	}
}
