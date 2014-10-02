package com.soucheng.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.soucheng.application.MainApplication;
import com.soucheng.db.DbAdapter;
import com.soucheng.vo.User;

/**
 * @author lichen
 */
public class RegisterActivity extends Activity {

    private EditText usernameEdit;
    private EditText smsEdit;
    private EditText passwordEdit;
    private EditText confirmPasswordEdit;

    private Button sendSmsBtn;
    private Button registBtn;
    private Button backBtn;

    DbAdapter dbAdapter;
    MainApplication application;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        dbAdapter = new DbAdapter(this);
        application = (MainApplication) getApplication();

        usernameEdit = (EditText) findViewById(R.id.usernameEdit);
        smsEdit = (EditText) findViewById(R.id.smsEdit);
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);
        confirmPasswordEdit = (EditText) findViewById(R.id.confirmPasswordEdit);

        sendSmsBtn = (Button) findViewById(R.id.sendSmsBtn);
        registBtn = (Button) findViewById(R.id.registBtn);
        backBtn = (Button) findViewById(R.id.backBtn);
        sendSmsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, R.string.send_sms_success, Toast.LENGTH_SHORT).show();
            }
        });
        registBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEdit.getText().toString().trim();
                String password = passwordEdit.getText().toString();
                String confirmPassword = confirmPasswordEdit.getText().toString();
                String smsCode = smsEdit.getText().toString().trim();
                if (username.trim().length() == 0) {
                    Toast.makeText(RegisterActivity.this, R.string.username_warn, Toast.LENGTH_SHORT).show();
                } else if (smsCode.length() == 0) {
                    Toast.makeText(RegisterActivity.this, R.string.sms_warn, Toast.LENGTH_SHORT).show();
                } else if (password.length() == 0) {
                    Toast.makeText(RegisterActivity.this, R.string.password_warn, Toast.LENGTH_SHORT).show();
                } else if (confirmPassword.length() == 0) {
                    Toast.makeText(RegisterActivity.this, R.string.confirm_pwd_warn, Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, R.string.pwd_diff_warn, Toast.LENGTH_SHORT).show();
                } else {
                    dbAdapter.open();
                    User user = dbAdapter.getUser(username);
                    if (user != null) {
                        Toast.makeText(RegisterActivity.this, R.string.username_repeat, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setGold(0);
                    dbAdapter.saveUser(user);

                    Toast.makeText(RegisterActivity.this, R.string.regist_success, Toast.LENGTH_SHORT).show();
                    //返回登录页面
                    Intent intent = new Intent();
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    setResult(OperCode.REGIST_SUCCESS_BACK_CODE, intent);
                    finish();
                }

            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(OperCode.REGIST_NORMAL_BACK_CODE);
                finish();
            }
        });
    }
}