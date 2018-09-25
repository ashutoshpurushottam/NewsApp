package com.eigendaksh.newsapp.article;


import com.bluelinelabs.conductor.Controller;
import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.base.BaseActivity;
import com.eigendaksh.newsapp.home.screens.article.ArticleController;

public class WebViewActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_web_view;
    }

    @Override
    protected Controller initialScreen() {
        return new ArticleController();
    }
}
