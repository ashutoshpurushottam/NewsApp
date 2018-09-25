package com.eigendaksh.newsapp.home.screens;

import android.support.v7.util.DiffUtil;

import com.eigendaksh.newsapp.model.others.Story;

import java.util.List;

public class StoryDiffCallBack extends DiffUtil.Callback {

    private final List<Story> oldList;
    private final List<Story> newList;

    public StoryDiffCallBack(List<Story> oldList, List<Story> newList) {
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
