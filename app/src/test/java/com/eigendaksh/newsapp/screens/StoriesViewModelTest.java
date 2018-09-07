package com.eigendaksh.newsapp.screens;

import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.apiResponses.PopularStoriesApiResponse;
import com.eigendaksh.newsapp.apiResponses.StoriesApiResponse;
import com.eigendaksh.newsapp.testutils.TestUtils;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Collections;

import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;

public class StoriesViewModelTest {

    private StoriesViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        viewModel = new StoriesViewModel();
    }

    @Test
    public void loading() throws Exception {
        // Test that the Boolean observable emits certain values in order
        // and the observer receives them correctly
        TestObserver<Boolean> loadingObserver = viewModel.loading().test();
        viewModel.loadingUpdated().accept(true);
        viewModel.loadingUpdated().accept(false);
        loadingObserver.assertValues(true, false);
    }

    @Test
    public void stories() throws Exception {
        StoriesApiResponse response = TestUtils.loadJson("mock/get_top_stories.json", StoriesApiResponse.class);
        viewModel.storyUpdated().accept(response.stories());
        // Assert that the observer received the same set of stories
        viewModel.stories().test().assertValue(response.stories());
    }

    @Test
    public void popularStories() throws Exception {
        PopularStoriesApiResponse response = TestUtils.loadJson("mock/get_popular_stories.json", PopularStoriesApiResponse.class);
        viewModel.popularStoriesUpdated().accept(response.popularStories());
        // Assert that the observer received same set of popular stories
        viewModel.popularStories().test().assertValue(response.popularStories());
    }

    @Test
    public void error() throws Exception {
        TestObserver<Integer> errorObserver = viewModel.error().test();
        viewModel.onError().accept(new IOException());
        viewModel.storyUpdated().accept(Collections.emptyList());
        errorObserver.assertValues(R.string.api_error_story, -1);
    }

}