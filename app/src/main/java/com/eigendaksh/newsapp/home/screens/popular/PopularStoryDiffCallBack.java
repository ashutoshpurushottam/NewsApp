package com.eigendaksh.newsapp.home.screens.popular;

import android.support.v7.util.DiffUtil;

import com.eigendaksh.newsapp.model.popular.PopularStory;

import java.util.List;

public class PopularStoryDiffCallBack extends DiffUtil.Callback {


    private final List<PopularStory> oldList;
    private final List<PopularStory> newList;

    public PopularStoryDiffCallBack(List<PopularStory> oldList, List<PopularStory> newList) {
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
        return oldList.get(oldItemPosition).id() == newList.get(newItemPosition).id();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

}
