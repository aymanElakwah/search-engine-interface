package com.ayman.searchengine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.ayman.searchengine.R;
import com.ayman.searchengine.model.ListItem;
import com.ayman.searchengine.model.SearchResult;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ListItem> mSearchResults = new ArrayList<>();
    private int mPageNumber = 1;
    private UrlClickListener mUrlClickListener;
    private RetryListener mRetryListener;
    private int mItemLayoutID;

    public SearchResultsAdapter(UrlClickListener urlClickListener, int itemLayoutID) {
        mUrlClickListener = urlClickListener;
        mItemLayoutID = itemLayoutID;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ListItem.DATA:
                ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), mItemLayoutID, parent, false);
                return new SearchResultViewHolder(binding);
            case ListItem.LOADING:
                View loading = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_item, parent, false);
                return new SimpleViewHolder(loading);
            case ListItem.NO_MORE:
                View noMore = LayoutInflater.from(parent.getContext()).inflate(R.layout.no_more_item, parent, false);
                return new SimpleViewHolder(noMore);
            default:
                View internetConnection = LayoutInflater.from(parent.getContext()).inflate(R.layout.internet_connection_item, parent, false);
                return new NoInternetViewHolder(internetConnection, mRetryListener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SearchResultViewHolder)
            ((SearchResultViewHolder) holder).bind((SearchResult) mSearchResults.get(position));
    }

    @Override
    public int getItemCount() {
        return mSearchResults == null ? 0 : mSearchResults.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mSearchResults.get(position).getType();
    }

    public int getPageNumberToFetch() {
        return mPageNumber;
    }

    public void clear() {
        mPageNumber = 1;
        mSearchResults.clear();
        notifyDataSetChanged();
    }

    public void setSearchResults(List<SearchResult> results) {
        if (results == null)
            mSearchResults = new ArrayList<>();
        else
            mSearchResults = new ArrayList<ListItem>(results);
        notifyDataSetChanged();
        int size = mSearchResults.size();
        if (size == 0) return;
        mPageNumber++;
    }

    private void removeLastItem(int type) {
        if (!isAdded(type)) return;
        int size = mSearchResults.size();
        mSearchResults.remove(size - 1);
        notifyItemRemoved(size - 1);
    }

    private void addItem(int type) {
        if (isAdded(type)) return;
        mSearchResults.add(new ListItem(type));
        notifyItemInserted(mSearchResults.size() - 1);
    }

    private boolean isAdded(int type) {
        int size = getItemCount();
        if (size == 0) return false;
        return mSearchResults.get(size - 1).getType() == type;
    }

    public void setNoMore(boolean noMore) {
        if (noMore) {
            addItem(ListItem.NO_MORE);
        } else {
            removeLastItem(ListItem.NO_MORE);
        }
    }

    public void setNoInternet(boolean noInternet) {
        if (noInternet) {
            removeLastItem(ListItem.LOADING);
            addItem(ListItem.NO_INTERNET);
        } else {
            removeLastItem(ListItem.NO_INTERNET);
        }
    }

    public void setLoading() {
        addItem(ListItem.LOADING);
    }

    public boolean isLoading() {
        return isAdded(ListItem.LOADING);
    }

    public void setRetryListener(RetryListener retryListener) {
        mRetryListener = retryListener;
    }

    public interface UrlClickListener {
        void openURL(String url);
    }

    public interface RetryListener {
        void retry();
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mBinding;

        SearchResultViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SearchResult searchResult = (SearchResult) mSearchResults.get(getAdapterPosition());
                    mUrlClickListener.openURL(searchResult.getUrl());
                }
            });
            mBinding = binding;
        }

        void bind(SearchResult result) {
            mBinding.setVariable(BR.item, result);
            mBinding.executePendingBindings();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
