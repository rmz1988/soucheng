package com.soucheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.soucheng.widget.ImageTextButton;
import com.soucheng.widget.ImageTextButtonManager;

/**
 * 首页
 */
public class HomeActivity extends Activity {

    private ImageTextButton homeBtn;
    private ImageTextButton shopBtn;
    private ImageTextButton hisBtn;
    private ImageTextButton moreBtn;

    private ImageTextButtonManager btnManager;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //初始化导航按钮组件
        initImageTextButton();
    }

    private void initImageTextButton() {
        homeBtn = (ImageTextButton) findViewById(R.id.homeBtn);
        shopBtn = (ImageTextButton) findViewById(R.id.shopBtn);
        hisBtn = (ImageTextButton) findViewById(R.id.hisBtn);
        moreBtn = (ImageTextButton) findViewById(R.id.moreBtn);

        homeBtn.setImageSrc(R.drawable.logo);
        shopBtn.setImageSrc(R.drawable.logo);
        hisBtn.setImageSrc(R.drawable.logo);
        moreBtn.setImageSrc(R.drawable.logo);

        homeBtn.setText(R.string.home_btn_text);
        shopBtn.setText(R.string.shop_btn_text);
        hisBtn.setText(R.string.his_btn_text);
        moreBtn.setText(R.string.more_btn_text);

        btnManager = new ImageTextButtonManager(this);
        btnManager.addButton(homeBtn);
        btnManager.addButton(shopBtn);
        btnManager.addButton(hisBtn);
        btnManager.addButton(moreBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnManager.selected(R.id.homeBtn);
            }
        });
        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnManager.selected(R.id.shopBtn);
            }
        });
        hisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnManager.selected(R.id.hisBtn);
            }
        });
        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnManager.selected(R.id.moreBtn);
            }
        });
    }

}
