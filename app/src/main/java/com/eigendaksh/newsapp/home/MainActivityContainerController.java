package com.eigendaksh.newsapp.home;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.base.BaseController;
import com.eigendaksh.newsapp.data.NewsRequester;
import com.eigendaksh.newsapp.screens.business.BusinessStoriesController;
import com.eigendaksh.newsapp.screens.popular.PopularStoriesController;
import com.eigendaksh.newsapp.screens.sports.SportsStoriesController;
import com.eigendaksh.newsapp.screens.topstories.TopStoriesController;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivityContainerController extends BaseController {

    private final String[] PAGE_TITLES = {"TOP STORIES", "MOST POPULAR", "BUSINESS", "SPORTS"};

    @Inject
    NewsRequester requester;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private final RouterPagerAdapter pagerAdapter;

    public MainActivityContainerController() {
        pagerAdapter = new RouterPagerAdapter(this) {
            @Override
            public void configureRouter(@NonNull Router router, int position) {
                if (!router.hasRootController()) {
                    Controller page;
                    switch (position) {
                        case 0:
                            page = new TopStoriesController();
                            break;
                        case 1:
                            page = new PopularStoriesController();
                            break;
                        case 2:
                            page = new BusinessStoriesController();
                            break;
                        case 3:
                            page = new SportsStoriesController();
                            break;
                        default:
                            page = new TopStoriesController();
                    }
                    router.setRoot(RouterTransaction.with(page));
                }
            }

            @Override
            public int getCount() {
                return PAGE_TITLES.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return PAGE_TITLES[position];
            }
        };

    }

    @Override
    protected int layoutRes() {
        return R.layout.main_container_screen;
    }

    @Override
    protected void onViewBound(View view) {
        super.onViewBound(view);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        if (getActivity() != null && !getActivity().isChangingConfigurations()) {
            viewPager.setAdapter(null);
        }
        tabLayout.setupWithViewPager(null);
        super.onDestroyView(view);
    }
}
