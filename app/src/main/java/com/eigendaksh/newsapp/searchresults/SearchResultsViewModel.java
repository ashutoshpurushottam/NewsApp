package com.eigendaksh.newsapp.searchresults;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.eigendaksh.newsapp.apiResponses.SearchApiResponse;
import com.eigendaksh.newsapp.data.NewsService;
import com.eigendaksh.newsapp.model.search.SearchDocument;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchResultsViewModel extends ViewModel {

    private final MutableLiveData<List<SearchDocument>> searchDocs = new MutableLiveData<>();
    private final MutableLiveData<Boolean> storiesLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private final NewsService newsService;
    private Disposable disposable;

    private String queryText;
    private String categoryText;
    private String beginDate;
    private String endDate;


    @Inject
    public SearchResultsViewModel(NewsService newsService) {
        this.newsService = newsService;
    }

    public void setQueryParameters(
            String queryText,
            String categoryText,
            String beginDate,
            String endDate) {

        makeCallForSearch(
                queryText,
                categoryText,
                beginDate,
                endDate
        );
    }

    @SuppressLint("CheckResult")
    public void makeCallForSearch(
            String query,
            String categories,
            String beginDate,
            String endDate) {

        Single<SearchApiResponse> searchStories = newsService.getSearchStories(
                query,
                categories,
                beginDate,
                endDate,
                "oldest");

        disposable = searchStories.subscribeOn(Schedulers.io())
                .subscribe(searchApiResponse -> {
                    searchDocs.postValue(searchApiResponse.searchResponseWrapper().searchDocumentList());
                    storiesLoadError.postValue(false);
                    loading.postValue(false);
                }, throwable -> {
                    storiesLoadError.postValue(true);
                    loading.postValue(false);
                });

    }


    public LiveData<List<SearchDocument>> getSearchStories() {
        return searchDocs;
    }

    public LiveData<Boolean> getError() {
        return storiesLoadError;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
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
