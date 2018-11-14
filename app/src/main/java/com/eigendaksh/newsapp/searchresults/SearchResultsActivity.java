package com.eigendaksh.newsapp.searchresults;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.apiResponses.SearchApiResponse;
import com.eigendaksh.newsapp.base.BaseActivity;
import com.eigendaksh.newsapp.data.NewsApi;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class SearchResultsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String queryText = bundle.getString("query");
            String categoriesText = bundle.getString("categories");
            String beginDate = bundle.getString("begin");
            String endDate = bundle.getString("end");

            makeTestCallForSearch(queryText, categoriesText, beginDate, endDate);
        }
    }

    @SuppressLint("CheckResult")
    private void makeTestCallForSearch(String query, String categories, String beginDate, String endDate) {
        Single<SearchApiResponse> searchStories = NewsApi.getInstance().getSearchStories(
                query,
                categories,
                beginDate,
                endDate,
                "oldest");

        searchStories.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchApiResponse ->
                        showToast(searchApiResponse.searchResponseWrapper().searchDocumentList().size() + ""), new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable.getLocalizedMessage());
                    }
                });

    }


    @Override
    protected int layoutRes() {
        return R.layout.activity_search_results;
    }


}
