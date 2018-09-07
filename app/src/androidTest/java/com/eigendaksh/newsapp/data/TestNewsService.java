package com.eigendaksh.newsapp.data;

import com.eigendaksh.newsapp.apiResponses.PopularStoriesApiResponse;
import com.eigendaksh.newsapp.apiResponses.StoriesApiResponse;
import com.eigendaksh.newsapp.test.TestUtils;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

import static org.junit.Assert.*;


@Singleton
public class TestNewsService implements NewsService {

    private boolean sendError;
    private TestUtils testUtils;

    @Inject
    public TestNewsService(TestUtils testUtils) {
        this.testUtils = testUtils;
    }

    public void setSendError(boolean sendError) {
        this.sendError = sendError;
    }

    @Override
    public Single<StoriesApiResponse> getTopStories() {
        if (!sendError) {
            StoriesApiResponse response = testUtils.loadJson("mock/get_top_stories.json",
                    StoriesApiResponse.class);
            return Single.just(response);
        }
        return Single.error(new IOException());
    }

    @Override
    public Single<PopularStoriesApiResponse> getPopularStories() {
        if (!sendError) {
            PopularStoriesApiResponse response = testUtils.loadJson("mock/get_popular_stories.json",
                    PopularStoriesApiResponse.class);
            return Single.just(response);
        }
        return Single.error(new IOException());
    }

    @Override
    public Single<StoriesApiResponse> getBusinessStories() {
        if (!sendError) {
            StoriesApiResponse response = testUtils.loadJson("mock/get_business_stories.json",
                    StoriesApiResponse.class);
            return Single.just(response);
        }
        return Single.error(new IOException());
    }

    @Override
    public Single<StoriesApiResponse> getSportsStories() {
        if (!sendError) {
            StoriesApiResponse response = testUtils.loadJson("mock/get_sports_stories.json",
                    StoriesApiResponse.class);
            return Single.just(response);
        }
        return Single.error(new IOException());
    }
}