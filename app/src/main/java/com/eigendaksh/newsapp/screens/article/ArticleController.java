package com.eigendaksh.newsapp.screens.article;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.base.BaseController;

import butterknife.BindView;
import timber.log.Timber;

public class ArticleController extends BaseController {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.web_view)
    WebView webView;


    @Override
    protected int layoutRes() {
        return R.layout.article_screen;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onViewBound(View view) {
        super.onViewBound(view);
        if(getActivity() != null) {
            String url = getActivity().getIntent().getStringExtra("url");
            Timber.i("Url: %s", url);
            webView.setWebViewClient(new ArticleClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(url);
        }
    }

    public class ArticleClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            // progressBar.setVisibility(View.INVISIBLE);
        }

    }



}
