package com.eigendaksh.newsapp.home.screens.sports;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.base.BaseController;
import com.eigendaksh.newsapp.home.screens.StoriesAdapter;
import com.eigendaksh.newsapp.home.screens.StoriesViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class SportsStoriesController extends BaseController {

    @Inject
    StoriesViewModel viewModel;
    @Inject
    SportsStoriesPresenter presenter;

    @BindView(R.id.news_list)
    RecyclerView newsList;
    @BindView(R.id.loading_indicator)
    View loadingView;
    @BindView(R.id.tv_error)
    TextView errorText;


    @Override
    protected int layoutRes() {
        return R.layout.screen_trending_news;
    }

    @Override
    protected void onViewBound(View view) {
        newsList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        newsList.setAdapter(new StoriesAdapter(presenter));
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
                        .subscribe(((StoriesAdapter) newsList.getAdapter())::setData),
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
