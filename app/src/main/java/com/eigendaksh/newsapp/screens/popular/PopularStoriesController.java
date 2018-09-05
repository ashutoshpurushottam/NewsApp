package com.eigendaksh.newsapp.screens.popular;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.base.BaseController;
import com.eigendaksh.newsapp.screens.StoriesViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class PopularStoriesController extends BaseController {

    @Inject StoriesViewModel viewModel;
    @Inject PopularStoriesPresenter presenter;

    @BindView(R.id.news_list) RecyclerView newsList;
    @BindView(R.id.loading_indicator) View loadingView;
    @BindView(R.id.tv_error) TextView errorText;

    @Override
    protected int layoutRes() {
        return R.layout.most_popular_screen;
    }

    @Override
    protected void onViewBound(View view) {
        newsList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        newsList.setAdapter(new PopularStoriesAdapter(presenter));
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[] {
                viewModel.loading()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(loading -> {
                    loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
                    newsList.setVisibility(loading ? View.GONE : View.VISIBLE);
                    errorText.setVisibility(loading ? View.GONE : errorText.getVisibility());
                }),
                viewModel.popularStories()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(popularStories -> {
                            PopularStoriesAdapter adapter =
                                    (PopularStoriesAdapter) newsList
                                            .getAdapter();
                            adapter.setData(popularStories);
                        }),
                viewModel.error()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(errorRes -> {
                    if(errorRes == -1) {
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
