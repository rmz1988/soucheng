package com.soucheng.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.view.*;
import android.widget.TextView;
import com.soucheng.activity.R;

/**
 * 邀请朋友对话框
 *
 * @author lichen
 */
public class InviteFriendDialog extends Dialog {

    private TextView inviteQQ;
    private TextView inviteWeixin;
    private TextView inviteWeibo;

    public InviteFriendDialog(Context context) {
        this(context, 0);
    }

    public InviteFriendDialog(Context context, int theme) {
        this(context, true, null);
    }

    public InviteFriendDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.invite_friend);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.dialog_bg));

        inviteQQ = (TextView) findViewById(R.id.inviteQQ);
        inviteWeixin = (TextView) findViewById(R.id.inviteWeixin);
        inviteWeibo = (TextView) findViewById(R.id.inviteWeibo);

        inviteQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
        inviteWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
        inviteWeibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }
}
