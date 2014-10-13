package com.soucheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import com.soucheng.application.MainApplication;

/**
 * @author lichen
 */
public class LocationNotifyActivity extends Activity {

	private ImageView imageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
				WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		setContentView(R.layout.location_notify);

		imageView = (ImageView) findViewById(R.id.notifyView);
		imageView.setImageBitmap(((MainApplication) getApplication()).loadBitmap(R.drawable.huachuang,4));
		imageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public CharSequence onCreateDescription() {
		((MainApplication) getApplication()).recycleBitmap(imageView);
		return super.onCreateDescription();
	}
}