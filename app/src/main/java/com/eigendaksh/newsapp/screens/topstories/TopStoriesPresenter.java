package com.eigendaksh.newsapp.screens.topstories;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import com.eigendaksh.newsapp.article.WebViewActivity;
import com.eigendaksh.newsapp.data.NewsRequester;
import com.eigendaksh.newsapp.di.ScreenScope;
import com.eigendaksh.newsapp.model.others.Story;
import com.eigendaksh.newsapp.screens.StoriesAdapter;
import com.eigendaksh.newsapp.screens.StoriesViewModel;

import javax.inject.Inject;

@ScreenScope
public class TopStoriesPresenter implements StoriesAdapter.StoryClickedListener {

    private final StoriesViewModel viewModel;
    private final NewsRequester newsRequester;

    @Inject
    Context appContext;

    @Inject
    public TopStoriesPresenter(StoriesViewModel viewModel, NewsRequester newsRequester) {
        this.viewModel = viewModel;
        this.newsRequester = newsRequester;
        loadTopStories();
    }

    @SuppressLint("CheckResult")
    private void loadTopStories() {
        newsRequester.getTopStories()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.storyUpdated(), viewModel.onError());
    }


    @Override
    public void onStoryClicked(Story story) {
        Intent intent = new Intent(appContext, WebViewActivity.class);
        intent.putExtra("url", story.articleUrl());
        appContext.startActivity(intent);
    }
}
