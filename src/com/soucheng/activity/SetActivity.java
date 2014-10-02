package com.soucheng.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 系统设置
 *
 * @author lichen
 */
public class SetActivity extends Activity {

    private Button backBtn;
    private Button addressShowBtn;
    private Button addressAddBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set);

        backBtn = (Button) findViewById(R.id.backBtn);
        addressShowBtn = (Button) findViewById(R.id.addressShowBtn);
        addressAddBtn = (Button) findViewById(R.id.addressAddBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addressShowBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetActivity.this, AddressShowActivity.class);
                startActivity(intent);
            }
        });
        addressAddBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetActivity.this, AddressAddActivity.class);
                startActivity(intent);
            }
        });
    }
}