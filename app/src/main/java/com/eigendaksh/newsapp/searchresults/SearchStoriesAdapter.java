package com.eigendaksh.newsapp.searchresults;

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
import com.eigendaksh.newsapp.home.adapter.StoriesAdapter;
import com.eigendaksh.newsapp.model.search.SearchDocument;
import com.eigendaksh.newsapp.utils.Utilities;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchStoriesAdapter extends RecyclerView.Adapter<SearchStoriesAdapter
        .NewsViewHolder> {

    private final StoriesAdapter.OnStoryClickedListener listener;
    private final List<SearchDocument> data = new ArrayList<>();

    SearchStoriesAdapter(StoriesAdapter.OnStoryClickedListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public SearchStoriesAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news,
                parent, false);
        return new SearchStoriesAdapter.NewsViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchStoriesAdapter.NewsViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).hashCode();
    }

    public void setData(List<SearchDocument> stories) {
        if (stories != null) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new SearchStoryDiffCallBack
                    (data, stories));
            data.clear();
            data.addAll(stories);
            diffResult.dispatchUpdatesTo(this);
        } else {
            data.clear();
            notifyDataSetChanged();
        }

    }

    static final class NewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_image_news)
        ImageView newsImage;
        @BindView(R.id.list_item_continent)
        TextView sectionText;
        @BindView(R.id.list_item_date)
        TextView dateText;
        @BindView(R.id.list_item_news_text)
        TextView titleText;

        private SearchDocument searchDocument;

        NewsViewHolder(View itemView, StoriesAdapter.OnStoryClickedListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> listener.onItemClicked(searchDocument.webUrl()));


        }

        void bind(SearchDocument searchDocument) {
            this.searchDocument = searchDocument;
            titleText.setText(searchDocument.searchHeadline().mainHeadline());
            dateText.setText(Utilities.getDateFromSearchDocument(searchDocument));
            sectionText.setText(searchDocument.sectionName());
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.ic_paper)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.NORMAL);
            Glide.with(newsImage.getContext())
                    .load(Utilities.getImageFromUrlFromSearchDocument(searchDocument))
                    .apply(options)
                    .into(newsImage);
        }

    }
}