package com.eigendaksh.newsapp.home.worldnews;

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

public class WorldNewsViewModel extends ViewModel {

    private final MutableLiveData<List<Story>> topStories = new MutableLiveData<>();
    private final MutableLiveData<Boolean> topStoriesLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private Disposable disposable;


    public WorldNewsViewModel() {
        loadTopStories();
    }


    public LiveData<List<Story>> getTopStories() {
        return topStories;
    }

    public LiveData<Boolean> getError() {
        return topStoriesLoadError;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }


    private void loadTopStories() {
        loading.setValue(true);

        Single<StoriesApiResponse> storyCall = NewsApi.getInstance().getTopStories();
        disposable = storyCall.subscribeOn(Schedulers.io())
                .subscribe(storiesApiResponse -> {
                    topStoriesLoadError.postValue(false);
                    topStories.postValue(storiesApiResponse.stories());
                    loading.postValue(false);
                }, throwable -> {
                    Timber.e(throwable.getLocalizedMessage());
                    topStoriesLoadError.postValue(true);
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
