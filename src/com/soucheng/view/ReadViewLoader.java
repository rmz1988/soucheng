package com.soucheng.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import com.soucheng.activity.R;

/**
 * @author lichen
 */
public class ReadViewLoader extends ViewLoader {

    private WebView newsView;
    private Button backBtn;

    private ProgressDialog progressDialog;

    public ReadViewLoader(Context context, View view) {
        super(context, view);
    }

    @Override
    public void load() {
        newsView = (WebView) view.findViewById(R.id.newsView);
        newsView.getSettings().setJavaScriptEnabled(true);
        newsView.getSettings().setSupportZoom(true);
        newsView.setInitialScale(90);
        newsView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage(context.getResources().getString(R.string.loading));
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
        newsView.loadUrl("http://soucity.sinaapp.com/");

        backBtn = (Button) view.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsView.loadUrl("http://soucity.sinaapp.com/");
            }
        });
    }

}
