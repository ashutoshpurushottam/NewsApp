package com.eigendaksh.newsapp.home.sportsnews;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.eigendaksh.newsapp.apiResponses.StoriesApiResponse;
import com.eigendaksh.newsapp.data.NewsApi;
import com.eigendaksh.newsapp.model.others.Story;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class SportsNewsViewModel extends ViewModel {

    private final MutableLiveData<List<Story>> sportsStories = new MutableLiveData<>();
    private final MutableLiveData<Boolean> storiesLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private Disposable disposable;


    public SportsNewsViewModel() {
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

        Single<StoriesApiResponse> storyCall = NewsApi.getInstance().getSportsStories();
        disposable = storyCall.subscribeOn(Schedulers.io())
                .doOnSuccess(storiesApiResponse -> {
                    storiesLoadError.postValue(false);
                    sportsStories.postValue(storiesApiResponse.stories());
                    loading.postValue(false);
                })
                .doOnError(throwable -> {
                    Timber.e(throwable.getLocalizedMessage());
                    storiesLoadError.postValue(true);
                    loading.postValue(false);
                })
                .observeOn(Schedulers.io())
                .subscribe();

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
