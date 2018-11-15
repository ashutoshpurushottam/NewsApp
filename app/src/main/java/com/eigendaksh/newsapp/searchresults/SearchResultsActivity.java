package com.eigendaksh.newsapp.searchresults;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.apiResponses.SearchApiResponse;
import com.eigendaksh.newsapp.article.WebViewActivity;
import com.eigendaksh.newsapp.base.BaseActivity;
import com.eigendaksh.newsapp.data.NewsApi;
import com.eigendaksh.newsapp.home.MainActivity;
import com.eigendaksh.newsapp.home.adapter.StoriesAdapter;
import com.eigendaksh.newsapp.home.businessnews.BusinessNewsViewModel;
import com.eigendaksh.newsapp.home.trendingnews.PopularStoriesAdapter;
import com.eigendaksh.newsapp.model.search.SearchDocument;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class SearchResultsActivity extends BaseActivity implements StoriesAdapter.OnStoryClickedListener {

    @BindView(R.id.news_list) RecyclerView newsList;
    @BindView(R.id.loading_indicator) View loadingView;
    @BindView(R.id.tv_error) TextView errorText;

    private SearchResultsViewModel viewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String queryText = bundle.getString("query");
            String categoriesText = bundle.getString("categories");
            String beginDate = bundle.getString("begin");
            String endDate = bundle.getString("end");

            viewModel = ViewModelProviders.of(this).get(SearchResultsViewModel.class);

            viewModel.setQueryParameters(
                    queryText,
                    categoriesText,
                    beginDate,
                    endDate
            );

            newsList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            newsList.setLayoutManager(new LinearLayoutManager(this));
            newsList.setAdapter(new SearchStoriesAdapter(this));
            observeViewModel();
        }
    }


    @SuppressWarnings("ConstantConditions")
    private void observeViewModel() {
        viewModel.getLoading().observe(this, isLoading -> {
            loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            newsList.setVisibility(isLoading ? View.GONE : View.VISIBLE);
            errorText.setVisibility(isLoading ? View.GONE : errorText.getVisibility());
        });

        viewModel.getError().observe(this, isError -> {
            if(isError) {
                errorText.setVisibility(View.VISIBLE);
                newsList.setVisibility(View.GONE);
                errorText.setText(R.string.api_error_story);
            } else {
                errorText.setVisibility(View.GONE);
                errorText.setText(null);
            }

        });

        viewModel.getSearchStories().observe(this, searchDocuments -> {
            SearchStoriesAdapter adapter = (SearchStoriesAdapter) newsList.getAdapter();
            adapter.setData(searchDocuments);
        });

    }



    @Override
    protected int layoutRes() {
        return R.layout.activity_search_results;
    }


    @Override
    public void onItemClicked(String url) {
        Intent intent = new Intent(SearchResultsActivity.this, WebViewActivity.class);
        intent.putExtra("article_url", url);
        startActivity(intent);

    }
}
