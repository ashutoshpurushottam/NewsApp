package com.eigendaksh.newsapp.home.screens.topstories;

import android.annotation.SuppressLint;

import com.eigendaksh.newsapp.data.NewsRepository;
import com.eigendaksh.newsapp.di.ScreenScope;
import com.eigendaksh.newsapp.home.screens.BaseStoriesPresenter;
import com.eigendaksh.newsapp.home.screens.StoriesViewModel;

import javax.inject.Inject;

@ScreenScope
public class TopStoriesPresenter extends BaseStoriesPresenter {

    private final StoriesViewModel viewModel;
    private final NewsRepository newsRepository;

    @Inject
    public TopStoriesPresenter(StoriesViewModel viewModel, NewsRepository newsRepository) {
        this.viewModel = viewModel;
        this.newsRepository = newsRepository;
        loadTopStories();
    }

    @SuppressLint("CheckResult")
    private void loadTopStories() {
        newsRepository.getTopStories()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.storyUpdated(), viewModel.onError());
    }
}
