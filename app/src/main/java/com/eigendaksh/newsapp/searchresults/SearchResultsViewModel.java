package com.eigendaksh.newsapp.searchresults;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.eigendaksh.newsapp.apiResponses.SearchApiResponse;
import com.eigendaksh.newsapp.data.NewsApi;
import com.eigendaksh.newsapp.model.others.Story;
import com.eigendaksh.newsapp.model.search.SearchDocument;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class SearchResultsViewModel extends ViewModel {

    private final MutableLiveData<List<SearchDocument>> searchDocs = new MutableLiveData<>();
    private final MutableLiveData<Boolean> storiesLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private Disposable disposable;

    private String queryText;
    private String categoryText;
    private String beginDate;
    private String endDate;


    public SearchResultsViewModel() {
    }

    /*
                String queryText = bundle.getString("query");
            String categoriesText = bundle.getString("categories");
            String beginDate = bundle.getString("begin");
            String endDate = bundle.getString("end");

     */
    public void setQueryParameters(String queryText, String categoryText, String beginDate, String endDate) {
        queryText = queryText;
        categoryText = categoryText;
        beginDate = beginDate;
        endDate = endDate;

        makeTestCallForSearch(
                queryText,
                categoryText,
                beginDate,
                endDate
        );
    }

    @SuppressLint("CheckResult")
    public void makeTestCallForSearch(
            String query,
            String categories,
            String beginDate,
            String endDate) {

        Single<SearchApiResponse> searchStories = NewsApi.getInstance().getSearchStories(
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
