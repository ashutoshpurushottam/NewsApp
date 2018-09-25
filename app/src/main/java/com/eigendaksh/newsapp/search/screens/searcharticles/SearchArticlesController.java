package com.eigendaksh.newsapp.search.screens.searcharticles;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bluelinelabs.conductor.Controller;
import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.base.BaseController;
import com.eigendaksh.newsapp.home.screens.StoriesAdapter;
import com.eigendaksh.newsapp.model.search.SearchDocument;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

public class SearchArticlesController extends BaseController {

    @Inject SearchArticlesViewModel viewModel;
    @Inject SearchArticlesPresenter presenter;

    @BindView(R.id.news_list)
    RecyclerView newsList;
    @BindView(R.id.loading_indicator)
    View loadingView;
    @BindView(R.id.tv_error)
    TextView errorText;


    static final String QUERY_KEY = "query";
    static final String CATEGORIES_KEY = "categories";

    public static Controller newInstance(String query, String categories) {
        Bundle bundle = new Bundle();
        bundle.putString(QUERY_KEY, query);
        bundle.putString(CATEGORIES_KEY, categories);
        return new SearchArticlesController(bundle);
    }

    public SearchArticlesController(Bundle bundle) {
        super(bundle);
    }

    @Override
    protected int layoutRes() {
        return R.layout.search_articles_screen;
    }

    @Override
    protected void onViewBound(View view) {
        super.onViewBound(view);
        String query = getArgs().getString(QUERY_KEY);
        String categories = getArgs().getString(CATEGORIES_KEY);
        newsList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        newsList.setAdapter(new ArticlesAdapter(presenter));
        presenter.searchArticles(query, categories);
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                viewModel.loading()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(loading -> {
                    loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
                    newsList.setVisibility(loading ? View.GONE : View.VISIBLE);
                    errorText.setVisibility(loading ? View.GONE : errorText.getVisibility());
                }),
                viewModel.stories()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<SearchDocument>>() {
                    @Override
                    public void accept(List<SearchDocument> searchDocuments) throws Exception {
                        ((ArticlesAdapter) newsList.getAdapter()).setData(searchDocuments);
                    }
                }),
                viewModel.error()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(errorRes -> {
                    if (errorRes == -1) {
                        errorText.setText(null);
                        errorText.setVisibility(View.GONE);
                    } else {
                        errorText.setVisibility(View.VISIBLE);
                        newsList.setVisibility(View.GONE);
                        errorText.setText(errorRes);
                    }
                })
        };
    }


}
