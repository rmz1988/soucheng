package com.soucheng.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.soucheng.application.MainApplication;
import com.soucheng.db.DbAdapter;
import com.soucheng.vo.Config;
import com.soucheng.vo.User;

/**
 * @author lichen
 */
public class LoginActivity extends Activity {

    private EditText usernameEdit;
    private EditText passwordEdit;

    private Button registBtn;
    private Button loginBtn;
    private Button findPasswordBtn;

    private ImageButton qqLoginBtn;
    private ImageButton weixinLoginBtn;
    private ImageButton weiboLoginBtn;

    private DbAdapter dbAdapter;
    private MainApplication application;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        dbAdapter = new DbAdapter(this);
        application = (MainApplication) getApplication();
        initWidgets();
    }

    private void initWidgets() {
        usernameEdit = (EditText) findViewById(R.id.usernameEdit);
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);

        registBtn = (Button) findViewById(R.id.registBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        findPasswordBtn = (Button) findViewById(R.id.findPasswordBtn);
        registBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, OperCode.REGISTER_REQUEST_CODE);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if (username.length() == 0) {
                    Toast.makeText(LoginActivity.this, R.string.username_warn, Toast.LENGTH_SHORT).show();
                } else if (password.length() == 0) {
                    Toast.makeText(LoginActivity.this, R.string.password_warn, Toast.LENGTH_SHORT).show();
                } else {
                    dbAdapter.open();
                    User user = dbAdapter.getUser(username);
                    if (user == null || !password.equals(user.getPassword())) {
                        Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    application.setLoginUser(user);
                    Config config = dbAdapter.getConfig();
                    config.setLoginUsername(username);
                    dbAdapter.updateConfig(config);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        findPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        qqLoginBtn = (ImageButton) findViewById(R.id.qqLoginBtn);
        weixinLoginBtn = (ImageButton) findViewById(R.id.weixinLoginBtn);
        weiboLoginBtn = (ImageButton) findViewById(R.id.weiboLoginBtn);
        qqLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        weixinLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        weiboLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == OperCode.REGIST_SUCCESS_BACK_CODE) {
            usernameEdit.setText(data.getStringExtra("username"));
            passwordEdit.setText(data.getStringExtra("password"));
        }
    }
}