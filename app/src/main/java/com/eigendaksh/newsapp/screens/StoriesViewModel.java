package com.eigendaksh.newsapp.screens;

import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.di.ScreenScope;
import com.eigendaksh.newsapp.model.PopularStory;
import com.eigendaksh.newsapp.model.TopStory;
import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

@ScreenScope
public class StoriesViewModel {

    private final BehaviorRelay<List<TopStory>> topStoryRelay = BehaviorRelay.create();
    private final BehaviorRelay<List<PopularStory>> popularStoryRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();

    @Inject
    public StoriesViewModel() {
    }

    // Expose relays as Observables to the views
    public Observable<Boolean> loading() {
        return loadingRelay;
    }

    public Observable<List<TopStory>> stories() {
        return topStoryRelay;
    }

    public Observable<List<PopularStory>> popularStories() {
        return popularStoryRelay;
    }

    public Observable<Integer> error() {
        return errorRelay;
    }

    // To update these relays from outside
    public Consumer<Boolean> loadingUpdated() {
        return loadingRelay;
    }

    public Consumer<List<TopStory>> topStoryUpdated() {
        errorRelay.accept(-1);
        return topStoryRelay;
    }

    public Consumer<List<PopularStory>> popularStoriesUpdated() {
        errorRelay.accept(-1);
        return popularStoryRelay;
    }

    public Consumer<Throwable> onError() {
        return throwable -> {
            Timber.e(throwable, "Error loading Stories");
            errorRelay.accept(R.string.api_error_story);
        };
    }

}
