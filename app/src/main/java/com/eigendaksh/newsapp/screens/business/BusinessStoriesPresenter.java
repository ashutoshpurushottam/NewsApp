package com.eigendaksh.newsapp.screens.business;

import android.annotation.SuppressLint;

import com.eigendaksh.newsapp.data.NewsRequester;
import com.eigendaksh.newsapp.di.ScreenScope;
import com.eigendaksh.newsapp.model.others.Story;
import com.eigendaksh.newsapp.screens.StoriesAdapter;
import com.eigendaksh.newsapp.screens.StoriesViewModel;

import javax.inject.Inject;

@ScreenScope
public class BusinessStoriesPresenter implements StoriesAdapter.StoryClickedListener {

    private final StoriesViewModel viewModel;
    private final NewsRequester newsRequester;


    @Inject
    public BusinessStoriesPresenter(StoriesViewModel viewModel, NewsRequester newsRequester) {
        this.viewModel = viewModel;
        this.newsRequester = newsRequester;
        loadBusinessStories();
    }

    @SuppressLint("CheckResult")
    private void loadBusinessStories() {
        newsRequester.getBusinessStories()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.storyUpdated(), viewModel.onError());
    }

    @Override
    public void onStoryClicked(Story story) {

    }
}
