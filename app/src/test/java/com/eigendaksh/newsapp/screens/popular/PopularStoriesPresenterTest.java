package com.eigendaksh.newsapp.screens.popular;

import com.eigendaksh.newsapp.apiResponses.PopularStoriesApiResponse;
import com.eigendaksh.newsapp.apiResponses.StoriesApiResponse;
import com.eigendaksh.newsapp.data.NewsRequester;
import com.eigendaksh.newsapp.model.others.Story;
import com.eigendaksh.newsapp.model.popular.PopularStory;
import com.eigendaksh.newsapp.screens.StoriesViewModel;
import com.eigendaksh.newsapp.screens.business.BusinessStoriesPresenter;
import com.eigendaksh.newsapp.testutils.TestUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class PopularStoriesPresenterTest {
    @Mock
    StoriesViewModel viewModel;
    @Mock
    NewsRequester newsRequester;
    @Mock
    Consumer<Boolean> loadingConsumer;
    @Mock
    Consumer<List<PopularStory>> storiesUpdatedConsumer;
    @Mock
    Consumer<Throwable> onErrorConsumer;

    private PopularStoriesPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(viewModel.loadingUpdated()).thenReturn(loadingConsumer);
        when(viewModel.onError()).thenReturn(onErrorConsumer);
        when(viewModel.popularStoriesUpdated()).thenReturn(storiesUpdatedConsumer);
    }

    @Test
    public void storiesLoaded() throws Exception {
        List<PopularStory> storyList = setUpSuccess();
        initializePresenter();
        // Verify that when presenter is created getBusinessStories method gets called
        verify(newsRequester).getPopularStories();
        // This is success case: so we expect success consumer should be called
        verify(storiesUpdatedConsumer).accept(storyList);
        // There should be no interaction with the error consumer
        verifyZeroInteractions(onErrorConsumer);
    }

    @Test
    public void storiesUpdatedError() throws Exception {
        Throwable error = setUpError();
        initializePresenter();
        // This is error case: we expect error consumer to be called
        verify(onErrorConsumer).accept(error);
        // There should be no interaction with on success consumer
        verifyZeroInteractions(storiesUpdatedConsumer);
    }

    @Test
    public void loadingSuccess() throws Exception {
        setUpSuccess();
        initializePresenter();

        // verify that methods were called in a particular order
        InOrder inOrder = Mockito.inOrder(loadingConsumer);
        inOrder.verify(loadingConsumer).accept(true);
        inOrder.verify(loadingConsumer).accept(false);
    }

    @SuppressWarnings("ThrowableNotThrown")
    @Test
    public void loadingFailed() throws Exception {
        setUpError();
        initializePresenter();
        // verify that methods were called in a particular order
        InOrder inOrder = Mockito.inOrder(loadingConsumer);
        inOrder.verify(loadingConsumer).accept(true);
        inOrder.verify(loadingConsumer).accept(false);
    }

    private void initializePresenter() {
        presenter = new PopularStoriesPresenter(viewModel, newsRequester);
    }

    private List<PopularStory> setUpSuccess() {
        PopularStoriesApiResponse response = TestUtils.loadJson("mock/get_popular_stories.json",
                PopularStoriesApiResponse.class);
        List<PopularStory> storyList = response.popularStories();
        when(newsRequester.getPopularStories()).thenReturn(Single.just(storyList));
        return storyList;
    }

    private Throwable setUpError() {
        Throwable error = new IOException();
        when(newsRequester.getPopularStories()).thenReturn(Single.error(error));
        return error;
    }

}