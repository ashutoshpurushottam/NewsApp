package com.eigendaksh.newsapp.search.screens.search;

import com.eigendaksh.newsapp.di.ScreenScope;
import com.jakewharton.rxrelay2.BehaviorRelay;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

@ScreenScope
public class SearchScreenViewModel {

    private final BehaviorRelay<String> messageRelay = BehaviorRelay.create();
    private final BehaviorRelay<String[]> nextScreenRelay = BehaviorRelay.create();

    @Inject
    public SearchScreenViewModel() {

    }

    // Expose relay to view as observable
    public Observable<String> getMessageObs() {
        return messageRelay;
    }

    public Observable<String[]> getNextScreenObs() {
        return nextScreenRelay;
    }

    // Expose as Consumer to presenter
    public Consumer<String> getMessageCons() {
        return messageRelay;
    }

    public Consumer<String[]> getNextScreenCons() {
        return nextScreenRelay;
    }
}
