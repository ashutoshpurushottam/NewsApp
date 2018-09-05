package com.eigendaksh.newsapp.screens.popular;

import android.annotation.SuppressLint;

import com.eigendaksh.newsapp.data.NewsRequester;
import com.eigendaksh.newsapp.di.ScreenScope;
import com.eigendaksh.newsapp.model.others.Story;
import com.eigendaksh.newsapp.screens.StoriesViewModel;

import javax.inject.Inject;

@ScreenScope
public class PopularStoriesPresenter implements PopularStoriesAdapter.StoryClickedListener {

    private final StoriesViewModel viewModel;
    private final NewsRequester newsRequester;

    @Inject
    public PopularStoriesPresenter(StoriesViewModel viewModel, NewsRequester newsRequester) {
        this.viewModel = viewModel;
        this.newsRequester = newsRequester;
        loadPopularStories();
    }

    @SuppressLint("CheckResult")
    private void loadPopularStories() {
        newsRequester.getPopularStories()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.popularStoriesUpdated(), viewModel.onError());
    }



    @Override
    public void onStoryClicked(Story story) {

    }
}
