package com.eigendaksh.newsapp.data;

import com.eigendaksh.newsapp.apiResponses.PopularStoriesApiResponse;
import com.eigendaksh.newsapp.apiResponses.StoriesApiResponse;
import com.eigendaksh.newsapp.model.others.Story;
import com.eigendaksh.newsapp.model.popular.PopularStory;
import com.eigendaksh.newsapp.model.search.SearchDocument;

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

//    public Single<List<Story>> getTopStories() {
//        return service.getTopStories()
//                .map(StoriesApiResponse::stories)
//                .subscribeOn(Schedulers.io());
//    }
//
//    public Single<List<Story>> getBusinessStories() {
//        return service.getBusinessStories()
//                .map(StoriesApiResponse::stories)
//                .subscribeOn(Schedulers.io());
//    }
//
//    public Single<List<Story>> getSportsStories() {
//        return service.getSportsStories()
//                .map(StoriesApiResponse::stories)
//                .subscribeOn(Schedulers.io());
//    }
//
//
//
//    public Single<List<PopularStory>> getPopularStories() {
//        return service.getPopularStories()
//                .map(PopularStoriesApiResponse::popularStories)
//                .doOnError(throwable -> Timber.e(throwable.getLocalizedMessage()))
//                .subscribeOn(Schedulers.io());
//    }
//
//    public Single<List<SearchDocument>> getSearchStories(String query, String categories) {
//        return service.getSearchStories(query, categories)
//                .map(searchApiResponse -> searchApiResponse.searchResponseWrapper().searchDocumentList())
//                .doOnError(throwable -> Timber.e(throwable.getLocalizedMessage()))
//                .subscribeOn(Schedulers.io());
//    }


}
