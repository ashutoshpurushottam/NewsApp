package com.eigendaksh.newsapp.home.trendingnews;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.eigendaksh.newsapp.data.NewsService;
import com.eigendaksh.newsapp.model.popular.PopularStory;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class TrendingNewsViewModel extends ViewModel {

    private final MutableLiveData<List<PopularStory>> popularStories = new MutableLiveData<>();
    private final MutableLiveData<Boolean> storiesLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private final NewsService newsService;

    private Disposable disposable;


    @Inject
    public TrendingNewsViewModel(NewsService newsService) {
        this.newsService = newsService;
        loadPopularStories();
    }


    public LiveData<List<PopularStory>> getPopularStories() {
        return popularStories;
    }

    public LiveData<Boolean> getError() {
        return storiesLoadError;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }


    private void loadPopularStories() {
        loading.setValue(true);

        disposable = newsService.getPopularStories().subscribeOn(Schedulers.io())
                .subscribe(popularStoriesApiResponse -> {
                    storiesLoadError.postValue(false);
                    popularStories.postValue(popularStoriesApiResponse.popularStories());
                    loading.postValue(false);
                }, throwable -> {
                    Timber.e(throwable.getLocalizedMessage());
                    storiesLoadError.postValue(true);
                    loading.postValue(false);
                });

    }

    @Override
    protected void onCleared() {
        if(disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            disposable = null;
        }
        super.onCleared();
    }


}
