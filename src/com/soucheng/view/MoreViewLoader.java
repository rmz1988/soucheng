package com.soucheng.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.soucheng.activity.AccountActivity;
import com.soucheng.activity.OperCode;
import com.soucheng.activity.R;
import com.soucheng.activity.SetActivity;
import com.soucheng.dialog.AboutDialog;
import com.soucheng.dialog.InviteFriendDialog;

/**
 * @author lichen
 */
public class MoreViewLoader extends ViewLoader {

    private Button myAccountBtn;
    private Button setBtn;
    private Button inviteFriendBtn;
    private Button upgradeBtn;
    private Button aboutBtn;

    public MoreViewLoader(Context context, View view) {
        super(context, view);
    }

    @Override
    public void load() {
        myAccountBtn = (Button) view.findViewById(R.id.myAccountBtn);
        setBtn = (Button) view.findViewById(R.id.setBtn);
        inviteFriendBtn = (Button) view.findViewById(R.id.inviteFriendBtn);
        upgradeBtn = (Button) view.findViewById(R.id.upgradeBtn);
        aboutBtn = (Button) view.findViewById(R.id.aboutBtn);

        myAccountBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AccountActivity.class);
                ((Activity) context).startActivityForResult(intent, OperCode.ACCOUNT_REQUEST_CODE);
            }
        });

        setBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SetActivity.class);
                context.startActivity(intent);
            }
        });

        inviteFriendBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                InviteFriendDialog dialog = new InviteFriendDialog(context);
                dialog.show();
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
