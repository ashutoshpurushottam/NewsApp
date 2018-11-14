package com.eigendaksh.newsapp.home.trendingnews;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.eigendaksh.newsapp.apiResponses.PopularStoriesApiResponse;
import com.eigendaksh.newsapp.data.NewsApi;
import com.eigendaksh.newsapp.model.popular.PopularStory;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class TrendingNewsViewModel extends ViewModel {

    private final MutableLiveData<List<PopularStory>> popularStories = new MutableLiveData<>();
    private final MutableLiveData<Boolean> storiesLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private Disposable disposable;


    public TrendingNewsViewModel() {
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

        Single<PopularStoriesApiResponse> storyCall = NewsApi.getInstance().getPopularStories();

        disposable = storyCall.subscribeOn(Schedulers.io())
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
