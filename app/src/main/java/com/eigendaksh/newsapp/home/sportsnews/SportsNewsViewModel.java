package com.eigendaksh.newsapp.home.sportsnews;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.eigendaksh.newsapp.apiResponses.StoriesApiResponse;
import com.eigendaksh.newsapp.data.NewsService;
import com.eigendaksh.newsapp.model.others.Story;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class SportsNewsViewModel extends ViewModel {

    private final MutableLiveData<List<Story>> sportsStories = new MutableLiveData<>();
    private final MutableLiveData<Boolean> storiesLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private final NewsService newsService;

    private Disposable disposable;


    @Inject
    public SportsNewsViewModel(NewsService newsService) {
        this.newsService = newsService;
        loadSportsStories();
    }


    public LiveData<List<Story>> getSportsStories() {
        return sportsStories;
    }

    public LiveData<Boolean> getError() {
        return storiesLoadError;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }


    private void loadSportsStories() {
        loading.setValue(true);

        Single<StoriesApiResponse> storyCall = newsService.getSportsStories();
        disposable = newsService.getSportsStories().subscribeOn(Schedulers.io())
                .subscribe(storiesApiResponse -> {
                    storiesLoadError.postValue(false);
                    sportsStories.postValue(storiesApiResponse.stories());
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
