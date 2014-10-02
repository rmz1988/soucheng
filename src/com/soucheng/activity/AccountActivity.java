package com.soucheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.soucheng.application.MainApplication;
import com.soucheng.db.DbAdapter;
import com.soucheng.vo.Config;

/**
 * @author lichen
 */
public class AccountActivity extends Activity {

    private TextView currentUser;
    private Button backBtn;
    private Button logoutBtn;

    private MainApplication application;
    private DbAdapter dbAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

        dbAdapter = new DbAdapter(this);
        application = (MainApplication) getApplication();
        currentUser = (TextView) findViewById(R.id.currentUser);
        currentUser.setText(application.getLoginUser().getUsername());

        backBtn = (Button) findViewById(R.id.backBtn);
        logoutBtn = (Button) findViewById(R.id.logoutBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(OperCode.ACCOUNT_NORMAL_BACK_CODE);
                finish();
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAdapter.open();
                Config config = dbAdapter.getConfig();
                config.setLoginUsername(null);
                dbAdapter.updateConfig(config);
                application.setLoginUser(null);

                setResult(OperCode.ACCOUNT_LOGOUT_CODE);
                finish();
            }
        });
    }
}