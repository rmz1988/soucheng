package com.soucheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * @author lichen
 */
public class AddressShowActivity extends Activity {

	private TextView backBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.address_show);

		backBtn = (TextView) findViewById(R.id.backView);
		backBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}