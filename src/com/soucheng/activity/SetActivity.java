package com.soucheng.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

/**
 * 系统设置
 *
 * @author lichen
 */
public class SetActivity extends Activity {

	private TextView backBtn;
	private Switch locationNotifySwitch;
	private LinearLayout addBtnArea;

	private Button queryAddrBtn;
	private Button addAddrBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set);

		backBtn = (TextView) findViewById(R.id.backView);
		locationNotifySwitch = (Switch) findViewById(R.id.locationOpenBtn);
		addBtnArea = (LinearLayout) findViewById(R.id.addrBtnArea);

		queryAddrBtn = (Button) findViewById(R.id.queryAddrBtn);
		addAddrBtn = (Button) findViewById(R.id.addAddrBtn);

		backBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		locationNotifySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					addBtnArea.setVisibility(View.VISIBLE);
				} else {
					addBtnArea.setVisibility(View.GONE);
				}
			}
		});

		queryAddrBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SetActivity.this, AddressShowActivity.class);
				startActivity(intent);
			}
		});

		addAddrBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SetActivity.this, AddressAddActivity.class);
				startActivity(intent);
			}
		});
	}
}