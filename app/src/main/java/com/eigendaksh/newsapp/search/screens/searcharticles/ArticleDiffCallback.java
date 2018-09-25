package com.eigendaksh.newsapp.search.screens.searcharticles;

import android.support.v7.util.DiffUtil;

import com.eigendaksh.newsapp.model.search.SearchDocument;

import java.util.List;

public class ArticleDiffCallback extends DiffUtil.Callback {

    private final List<SearchDocument> oldList;
    private final List<SearchDocument> newList;

    public ArticleDiffCallback(List<SearchDocument> oldList, List<SearchDocument> newList) {
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
        return oldList.get(oldItemPosition).docId().equals(newList.get(newItemPosition).docId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

}
