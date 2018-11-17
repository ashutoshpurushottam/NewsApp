package com.eigendaksh.newsapp.home.businessnews;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.base.BaseFragment;
import com.eigendaksh.newsapp.base.MyApplication;
import com.eigendaksh.newsapp.home.adapter.StoriesAdapter;
import com.eigendaksh.newsapp.viewmodel.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessNewsFragment extends BaseFragment {

    @Inject ViewModelFactory viewModelFactory;

    @BindView(R.id.news_list) RecyclerView newsList;
    @BindView(R.id.loading_indicator) View loadingView;
    @BindView(R.id.tv_error) TextView errorText;

    private BusinessNewsViewModel viewModel;

    public BusinessNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static BusinessNewsFragment newInstance() {
        return new BusinessNewsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MyApplication.getAppComponent(context).inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BusinessNewsViewModel.class);
        newsList.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
        newsList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        newsList.setAdapter(new StoriesAdapter(((StoriesAdapter.OnStoryClickedListener) getActivity())));
        observeViewModel();
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

        viewModel.getBusinessStories().observe(this, stories -> {
            StoriesAdapter adapter = (StoriesAdapter) newsList.getAdapter();
            adapter.setData(stories);
        });

    }


    @Override
    public int layoutRes() {
        return R.layout.screen_trending_news;
    }


}
