package com.eigendaksh.newsapp.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.model.others.Story;
import com.eigendaksh.newsapp.utils.Utilities;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.NewsViewHolder> {

    private final OnStoryClickedListener listener;
    private final List<Story> data = new ArrayList<>();

    public StoriesAdapter(OnStoryClickedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news,
                parent, false);
        return new NewsViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Story> stories) {
        if (stories != null) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new StoryDiffCallBack(data,
                    stories));
            data.clear();
            data.addAll(stories);
            diffResult.dispatchUpdatesTo(this);
        } else {
            data.clear();
            notifyDataSetChanged();
        }

    }

    static final class NewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_image_news) ImageView newsImage;
        @BindView(R.id.list_item_continent) TextView sectionText;
        @BindView(R.id.list_item_date) TextView dateText;
        @BindView(R.id.list_item_news_text) TextView titleText;

        private Story story;

        NewsViewHolder(View itemView, OnStoryClickedListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> listener.onItemClicked(story.articleUrl()));

        }

        void bind(Story story) {
            this.story = story;
            titleText.setText(story.title());
            dateText.setText(Utilities.getDateFromStory(story));
            sectionText.setText(story.section());
            // Use Glide to load image
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.ic_paper)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.NORMAL);
            Glide.with(newsImage.getContext())
                    .load(Utilities.getImageUrl(story))
                    .apply(options)
                    .into(newsImage);
        }

    }

    public interface OnStoryClickedListener {
        void onItemClicked(String url);
    }
}