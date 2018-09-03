package com.eigendaksh.newsapp.data;

import com.eigendaksh.newsapp.apiResponses.PopularStoriesApiResponse;
import com.eigendaksh.newsapp.apiResponses.TopStoriesApiResponse;
import com.eigendaksh.newsapp.model.PopularStory;
import com.eigendaksh.newsapp.model.TopStory;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class NewsRequester {

    private final NewsService service;

    @Inject
    NewsRequester(NewsService service) {
        this.service = service;
    }

    public Single<List<TopStory>> getTopStories() {
        return service.getTopStories()
                .map(TopStoriesApiResponse::topStories)
                .subscribeOn(Schedulers.io());
    }

    public Single<List<PopularStory>> getPopularStories() {
        return service.getPopularStories()
                .map(PopularStoriesApiResponse::popularStories)
                .doOnError(throwable -> Timber.e(throwable.getLocalizedMessage()))
                .subscribeOn(Schedulers.io());
    }
}
