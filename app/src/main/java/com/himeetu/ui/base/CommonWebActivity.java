package com.himeetu.ui.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;


import com.himeetu.R;
import com.himeetu.util.LogUtil;

/**
 * Created by object1984 on 15/9/11.
 */
public class CommonWebActivity extends WebActivity  {
    private String url;
    private String shareUrl = "";
    private boolean inited = false;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.activity_web);
        initWebView(WebSettings.LOAD_NO_CACHE);
        url = getIntent().getStringExtra("url");

        clickRefresh();

        webView.loadUrl(this.getLoadUrl());
    }

    @Override
    public String getLoadUrl() {
        return url;
    }


    @Override
    public boolean myShouldOverrideUrlLoading(WebView view, String url) {
       LogUtil.d("CommonWebActivity", "myShouldOverrideUrlLoading");
        loadingView.setVisibility(View.VISIBLE);

        return true;
    }



}
