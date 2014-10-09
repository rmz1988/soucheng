package com.soucheng.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.soucheng.activity.AccountActivity;
import com.soucheng.activity.OperCode;
import com.soucheng.activity.R;
import com.soucheng.activity.SetActivity;
import com.soucheng.application.MainApplication;
import com.soucheng.dialog.AboutDialog;

/**
 * @author lichen
 */
public class MoreViewLoader extends ViewLoader {

	private LinearLayout myAccountBtn;
	private ImageView userPhoto;
	private TextView accountView;

	private Button myChengmiBtn;
	private Button taskBtn;
	private Button suggestBtn;
	private Button setBtn;
	private Button upgradeBtn;
	private Button aboutBtn;

	private MainApplication application;

	public MoreViewLoader(Context context, View view) {
		super(context, view);
	}

	@Override
	public void load() {
		application = (MainApplication) context.getApplicationContext();
		myAccountBtn = (LinearLayout) view.findViewById(R.id.myAccountBtn);
		myAccountBtn.setFocusable(true);
		myAccountBtn.setClickable(true);
		userPhoto = (ImageView) view.findViewById(R.id.userPhoto);
		accountView = (TextView) view.findViewById(R.id.accountView);
		accountView.setText(application.getLoginUser().getUsername());

		myChengmiBtn = (Button) view.findViewById(R.id.myChengmiBtn);
		taskBtn = (Button) view.findViewById(R.id.taskBtn);
		setBtn = (Button) view.findViewById(R.id.setBtn);
		suggestBtn = (Button) view.findViewById(R.id.suggestBtn);
		upgradeBtn = (Button) view.findViewById(R.id.upgradeBtn);
		aboutBtn = (Button) view.findViewById(R.id.aboutBtn);

		myAccountBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, AccountActivity.class);
				((Activity) context).startActivityForResult(intent, OperCode.ACCOUNT_REQUEST_CODE);
			}
		});

		myChengmiBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		taskBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		setBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, SetActivity.class);
				context.startActivity(intent);
			}
		});

		suggestBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		upgradeBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		aboutBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AboutDialog dialog = new AboutDialog(context);
				dialog.show();
			}
		});
	}
}
