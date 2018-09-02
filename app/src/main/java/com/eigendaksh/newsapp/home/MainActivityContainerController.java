package com.eigendaksh.newsapp.home;

import android.view.View;

import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.base.BaseController;
import com.eigendaksh.newsapp.data.NewsRequester;
import com.eigendaksh.newsapp.model.TopStory;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class MainActivityContainerController extends BaseController {

    @Inject NewsRequester requester;

    @Override
    protected int layoutRes() {
        return R.layout.main_container_screen;
    }

    @Override
    protected void onViewBound(View view) {
        super.onViewBound(view);
        requester.getTopStories()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> {
                    Timber.d("Subscribe to request");
                })
                .doOnSuccess(topStories -> {
                    for (TopStory story : topStories) {
                        Timber.d(story.articleUrl());
                    }
                })
                .doOnError((Throwable throwable) -> {
                    Timber.e(throwable.getLocalizedMessage());
                }).subscribe();

    }
}
