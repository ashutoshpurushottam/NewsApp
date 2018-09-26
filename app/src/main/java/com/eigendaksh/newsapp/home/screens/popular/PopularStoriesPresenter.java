package com.eigendaksh.newsapp.home.screens.popular;

import android.annotation.SuppressLint;

import com.eigendaksh.newsapp.data.NewsRepository;
import com.eigendaksh.newsapp.di.ScreenScope;
import com.eigendaksh.newsapp.home.screens.BaseStoriesPresenter;
import com.eigendaksh.newsapp.home.screens.StoriesViewModel;

import javax.inject.Inject;

@ScreenScope
public class PopularStoriesPresenter extends BaseStoriesPresenter {

    private final StoriesViewModel viewModel;
    private final NewsRepository newsRepository;

    @Inject
    public PopularStoriesPresenter(StoriesViewModel viewModel, NewsRepository newsRepository) {
        this.viewModel = viewModel;
        this.newsRepository = newsRepository;
        loadPopularStories();
    }

    @SuppressLint("CheckResult")
    private void loadPopularStories() {
        newsRepository.getPopularStories()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.popularStoriesUpdated(), viewModel.onError());
    }


}
