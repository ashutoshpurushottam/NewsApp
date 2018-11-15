package com.eigendaksh.newsapp.searchresults;

import android.support.v7.util.DiffUtil;

import com.eigendaksh.newsapp.model.popular.PopularStory;
import com.eigendaksh.newsapp.model.search.SearchDocument;

import java.util.List;

public class SearchStoryDiffCallBack extends DiffUtil.Callback {

    private final List<SearchDocument> oldList;
    private final List<SearchDocument> newList;

    public SearchStoryDiffCallBack(List<SearchDocument> oldList, List<SearchDocument> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).docId() == newList.get(newItemPosition).docId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }


}
