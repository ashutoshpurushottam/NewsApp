package com.eigendaksh.newsapp.article;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.base.BaseActivity;

import butterknife.BindView;

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.web_view) WebView webView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;


    @Override
    protected int layoutRes() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.nyt_article);
        initWebView();
        if(getIntent().hasExtra("article_url")) {
            String url = getIntent().getStringExtra("article_url");
            webView.loadUrl(url);
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if(progressBar != null && progressBar.getVisibility() == View.GONE) {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(progressBar != null && progressBar.getVisibility() == View.VISIBLE) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}
