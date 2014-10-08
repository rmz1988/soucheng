package com.soucheng.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author lichen
 */
public class BehindDetailActivity extends Activity {

    private WebView behindWebView;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.behind_detail);

        behindWebView = (WebView) findViewById(R.id.behindWebView);
        behindWebView.getSettings().setJavaScriptEnabled(true);
        behindWebView.getSettings().setSupportZoom(true);
        behindWebView.setInitialScale(90);
        behindWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressDialog = new ProgressDialog(BehindDetailActivity.this);
                progressDialog.setMessage(getResources().getString(R.string.loading));
                progressDialog.show();
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                progressDialog.cancel();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressDialog.cancel();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("url");
        behindWebView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}