package com.eigendaksh.newsapp.search.screens.searcharticles;

import android.annotation.SuppressLint;

import com.eigendaksh.newsapp.data.NewsRequester;
import com.eigendaksh.newsapp.di.ScreenScope;

import javax.inject.Inject;

@ScreenScope
public class SearchArticlesPresenter implements ArticlesAdapter.ArticleClickedListener {

    private final SearchArticlesViewModel viewModel;
    private final NewsRequester newsRequester;

    @Inject
    public SearchArticlesPresenter(SearchArticlesViewModel viewModel, NewsRequester newsRequester) {
        this.viewModel = viewModel;
        this.newsRequester = newsRequester;
    }

    @SuppressLint("CheckResult")
    protected void searchArticles(String query, String categories) {
        newsRequester.getSearchStories(query, categories)
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.storyUpdated(), viewModel.onError());
    }

    @Override
    public void onArticleClicked() {

    }
}
