package com.eigendaksh.newsapp.home.businessnews;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.eigendaksh.newsapp.data.NewsService;
import com.eigendaksh.newsapp.model.others.Story;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class BusinessNewsViewModel extends ViewModel {

    private final MutableLiveData<List<Story>> businessStories = new MutableLiveData<>();
    private final MutableLiveData<Boolean> storiesLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private final NewsService newsService;

    private Disposable disposable;

    @Inject
    public BusinessNewsViewModel(NewsService newsService) {
        this.newsService = newsService;
        loadBusinessStories();
    }


    public LiveData<List<Story>> getBusinessStories() {
        return businessStories;
    }

    public LiveData<Boolean> getError() {
        return storiesLoadError;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }


    private void loadBusinessStories() {
        loading.setValue(true);

        disposable = newsService.getBusinessStories().subscribeOn(Schedulers.io())
                .subscribe(storiesApiResponse -> {
                    storiesLoadError.postValue(false);
                    businessStories.postValue(storiesApiResponse.stories());
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
