package com.himeetu.ui.base;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;

import com.himeetu.R;
import com.himeetu.util.LogUtil;
import com.himeetu.view.ErrorView;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;


/**
 * Created by object1984 on 2015-05-16.
 */
public class WebActivity extends BaseActivity implements ErrorView.IErrorViewListener {
    private static final String TAG = "WebActivity";
    public WebView webView;
    private ErrorView errorView;
    public View loadingView;
    CookieManager cookieManager;
    public static String T;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

    }

    public void toPage(String url) {
        webView.loadUrl(url);
    }


    public void initWebView(int cacheModel) {
        webView = (WebView) this.findViewById(R.id.web_view);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(cacheModel);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);

        errorView = (ErrorView) this.findViewById(R.id.error_view);
        errorView.setListener(this);
        errorView.setVisibility(View.INVISIBLE);
        loadingView = this.findViewById(R.id.loading_view);
        loadingView.setVisibility(View.GONE);


        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollbarFadingEnabled(true);
        webView.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void appurl(String url) {
                Uri uri = Uri.parse(url);
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        }, "download");


        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return myShouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                LogUtil.d("WebActivity", "onPageFinished");
                loadingView.setVisibility(View.INVISIBLE);

                WebActivity.this.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                LogUtil.d("WebActivity", "onReceivedError");
               webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
                webView.setVisibility(View.INVISIBLE);
                loadingView.setVisibility(View.INVISIBLE);
                errorView.setVisibility(View.VISIBLE);
            }
        });

    }



    public void clearHistory() {
        webView.clearHistory();
        webView.clearCache(false);
    }

    public boolean myShouldOverrideUrlLoading(WebView view, String url) {
        LogUtil.d("url", url);
        return true;
    }

    public void onPageFinished(WebView view, String url) {
        LogUtil.d("onPageFinished url", url);
    }

    public String getLoadUrl() {
        return this.getIntent().getStringExtra("url");
    }


    @Override
    public void clickRefresh() {
        if (errorView != null) {
            errorView.setVisibility(View.INVISIBLE);
        }
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
        }

        if(webView != null){
            webView.setVisibility(View.VISIBLE);
            webView.loadUrl(this.getLoadUrl());
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
