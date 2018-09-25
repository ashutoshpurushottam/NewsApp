package com.eigendaksh.newsapp.search;

import com.bluelinelabs.conductor.Controller;
import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.base.BaseActivity;
import com.eigendaksh.newsapp.search.screens.search.SearchScreenController;

public class SearchActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected Controller initialScreen() {
        return new SearchScreenController();
    }
}
