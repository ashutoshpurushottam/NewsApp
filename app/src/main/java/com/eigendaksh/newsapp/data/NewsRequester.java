package com.eigendaksh.newsapp.data;

import com.eigendaksh.newsapp.apiResponses.TopStoriesApiResponse;
import com.eigendaksh.newsapp.model.TopStory;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class NewsRequester {

    private final NewsService service;

    @Inject
    NewsRequester(NewsService service) {
        this.service = service;
    }

    public Single<List<TopStory>> getTopStories() {
        return service.getTopStories()
                .map(new Function<TopStoriesApiResponse, List<TopStory>>() {
                    @Override
                    public List<TopStory> apply(TopStoriesApiResponse topStoriesApiResponse) {
                        return topStoriesApiResponse.topStories();
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
