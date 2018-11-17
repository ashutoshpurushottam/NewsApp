package com.eigendaksh.newsapp.data;

import javax.inject.Inject;

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
