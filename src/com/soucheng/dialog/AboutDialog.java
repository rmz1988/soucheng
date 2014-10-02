package com.soucheng.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.view.*;
import android.widget.ImageView;
import com.soucheng.activity.R;

/**
 * 关于dialog
 *
 * @author lichen
 */
public class AboutDialog extends Dialog {

    private ImageView aboutView;

    public AboutDialog(Context context) {
        this(context, 0);
    }

    public AboutDialog(Context context, int theme) {
        this(context, true, null);
    }

    protected AboutDialog(Context context, final boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.about_dialog);

        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.dialog_bg));

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        Point point = new Point();
        Display screen = ((Activity) context).getWindowManager().getDefaultDisplay();
        screen.getSize(point);

        layoutParams.width = point.x * 8 / 10;
        layoutParams.height = point.y / 2;
        window.setAttributes(layoutParams);

        aboutView = (ImageView) findViewById(R.id.aboutView);
        aboutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }
}
