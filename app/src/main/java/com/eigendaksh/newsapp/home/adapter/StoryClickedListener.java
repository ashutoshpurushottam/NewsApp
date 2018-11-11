package com.eigendaksh.newsapp.home.adapter;

import android.content.Context;

public interface StoryClickedListener {
    void onStoryClicked(Context context, String articleUrl);
}
