package com.eigendaksh.newsapp.screens;

import android.content.Context;
import android.content.Intent;

import com.eigendaksh.newsapp.article.WebViewActivity;

public abstract class BaseStoriesPresenter implements StoryClickedListener {

    @Override
    public void onStoryClicked(Context context, String articleUrl) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", articleUrl);
        context.startActivity(intent);
    }
}
