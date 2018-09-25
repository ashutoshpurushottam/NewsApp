package com.eigendaksh.newsapp.home.screens.topstories;

import android.annotation.SuppressLint;

import com.eigendaksh.newsapp.data.NewsRequester;
import com.eigendaksh.newsapp.di.ScreenScope;
import com.eigendaksh.newsapp.home.screens.BaseStoriesPresenter;
import com.eigendaksh.newsapp.home.screens.StoriesViewModel;

import javax.inject.Inject;

@ScreenScope
public class TopStoriesPresenter extends BaseStoriesPresenter {

    private final StoriesViewModel viewModel;
    private final NewsRequester newsRequester;

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
}
