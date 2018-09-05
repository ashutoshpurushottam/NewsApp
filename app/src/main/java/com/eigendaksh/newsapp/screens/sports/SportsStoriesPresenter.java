package com.eigendaksh.newsapp.screens.sports;

import android.annotation.SuppressLint;

import com.eigendaksh.newsapp.data.NewsRequester;
import com.eigendaksh.newsapp.di.ScreenScope;
import com.eigendaksh.newsapp.model.others.Story;
import com.eigendaksh.newsapp.screens.StoriesAdapter;
import com.eigendaksh.newsapp.screens.StoriesViewModel;

import javax.inject.Inject;

@ScreenScope
public class SportsStoriesPresenter implements StoriesAdapter.StoryClickedListener {

    private final StoriesViewModel viewModel;
    private final NewsRequester newsRequester;

    @Inject
    public SportsStoriesPresenter(StoriesViewModel viewModel, NewsRequester newsRequester) {
        this.viewModel = viewModel;
        this.newsRequester = newsRequester;
        loadSportsStories();
    }

    @SuppressLint("CheckResult")
    private void loadSportsStories() {
        newsRequester.getSportsStories()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.storyUpdated(), viewModel.onError());
    }


    @Override
    public void onStoryClicked(Story story) {

    }

}
