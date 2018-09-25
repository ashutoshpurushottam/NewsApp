package com.eigendaksh.newsapp.search.screens.searcharticles;

import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.di.ScreenScope;
import com.eigendaksh.newsapp.model.search.SearchDocument;
import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

@ScreenScope
public class SearchArticlesViewModel {

    private final BehaviorRelay<List<SearchDocument>> storyRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();

    @Inject
    public SearchArticlesViewModel() {
    }

    // Expose relays as Observables to the views
    protected Observable<Boolean> loading() {
        return loadingRelay;
    }

    protected Observable<List<SearchDocument>> stories() {
        return storyRelay;
    }

    protected Observable<Integer> error() {
        return errorRelay;
    }

    // To update these relays from outside
    protected Consumer<Boolean> loadingUpdated() {
        return loadingRelay;
    }

    protected Consumer<List<SearchDocument>> storyUpdated() {
        errorRelay.accept(-1);
        return storyRelay;
    }

    protected Consumer<Throwable> onError() {
        return throwable -> {
            Timber.e(throwable, "Error loading articles");
            errorRelay.accept(R.string.api_search_error);
        };
    }

}
