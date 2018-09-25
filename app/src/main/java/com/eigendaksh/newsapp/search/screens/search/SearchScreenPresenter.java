package com.eigendaksh.newsapp.search.screens.search;

import com.eigendaksh.newsapp.di.ScreenScope;
import javax.inject.Inject;


@ScreenScope
public class SearchScreenPresenter {

    private final SearchScreenViewModel viewModel;

    @Inject
    public SearchScreenPresenter(SearchScreenViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void searchButtonPressed(String query, String categories) throws Exception {
        if (query == null || query.isEmpty()) {
            viewModel.getMessageCons().accept("Please enter some search query");
        } else if (categories == null || categories.isEmpty()) {
            viewModel.getMessageCons().accept("Please select at least one category");
        } else {
            viewModel.getNextScreenCons().accept(new String[]{query, categories});
        }
    }

}
