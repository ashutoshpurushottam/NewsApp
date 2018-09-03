package com.eigendaksh.newsapp.screens.topstories;

import android.support.v7.util.DiffUtil;

import com.eigendaksh.newsapp.model.TopStory;

import java.util.List;

public class TopStoryDiffCallBack extends DiffUtil.Callback {

    private final List<TopStory> oldList;
    private final List<TopStory> newList;

    public TopStoryDiffCallBack(List<TopStory> oldList, List<TopStory> newList) {
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
        return oldList.get(oldItemPosition).articleUrl().equals(newList.get(newItemPosition).articleUrl());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
