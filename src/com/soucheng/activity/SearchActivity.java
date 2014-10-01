package com.soucheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * @author lichen
 */
public class SearchActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        Toast.makeText(this, getIntent().getStringExtra("search"), Toast.LENGTH_SHORT).show();
    }
}