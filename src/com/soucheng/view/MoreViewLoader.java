package com.soucheng.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.soucheng.activity.R;
import com.soucheng.activity.SetActivity;

/**
 * @author lichen
 */
public class MoreViewLoader extends ViewLoader {

	private Button myAccountBtn;
	private Button taskManageBtn;
	private Button setBtn;
	private Button suggestBtn;
	private Button inviteFriendBtn;
	private Button questionBtn;
	private Button upgradeBtn;
	private Button aboutBtn;

	public MoreViewLoader(Context context, View view) {
		super(context, view);
	}

	@Override
	public void load() {
		myAccountBtn = (Button) view.findViewById(R.id.myAccountBtn);
		taskManageBtn = (Button) view.findViewById(R.id.taskManageBtn);
		setBtn = (Button) view.findViewById(R.id.setBtn);
		suggestBtn = (Button) view.findViewById(R.id.suggestBtn);
		inviteFriendBtn = (Button) view.findViewById(R.id.inviteFriendBtn);
		questionBtn = (Button) view.findViewById(R.id.questionBtn);
		upgradeBtn = (Button) view.findViewById(R.id.upgradeBtn);
		aboutBtn = (Button) view.findViewById(R.id.aboutBtn);

		myAccountBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		taskManageBtn.setOnClickListener(new View.OnClickListener() {

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
		inviteFriendBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		questionBtn.setOnClickListener(new View.OnClickListener() {

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

			}
		});
	}
}
