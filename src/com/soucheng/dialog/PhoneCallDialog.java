package com.soucheng.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import com.soucheng.activity.R;
import com.soucheng.application.MainApplication;

/**
 * @author lichen
 */
public class PhoneCallDialog extends Dialog {

	private ImageView phoneAdView;

	public PhoneCallDialog(Context context) {
		this(context, 0);
	}

	public PhoneCallDialog(Context context, int theme) {
		this(context, true, null);
	}

	protected PhoneCallDialog(Context context, final boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.phone_call);


		Window window = getWindow();
		window.setGravity(Gravity.LEFT | Gravity.TOP);
		window.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.dialog_bg));

		WindowManager.LayoutParams layoutParams = window.getAttributes();
		DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;

		layoutParams.width = screenWidth;
		layoutParams.height = screenHeight;
		window.setAttributes(layoutParams);

		phoneAdView = (ImageView) findViewById(R.id.phoneAdView);
		phoneAdView
				.setImageBitmap(((MainApplication) context.getApplicationContext()).loadBitmap(R.drawable.huachuang,4));
		phoneAdView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				cancel();
			}
		});
	}

	@Override
	protected void onStop() {
		super.onStop();
	}
}
