package com.eigendaksh.newsapp.home.screens;

import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.di.ScreenScope;
import com.eigendaksh.newsapp.model.others.Story;
import com.eigendaksh.newsapp.model.popular.PopularStory;
import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

@ScreenScope
public class StoriesViewModel {

    private final BehaviorRelay<List<Story>> storyRelay = BehaviorRelay.create();
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

    public Observable<List<Story>> stories() {
        return storyRelay;
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

    public Consumer<List<Story>> storyUpdated() {
        errorRelay.accept(-1);
        return storyRelay;
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