package com.ayman.searchengine.ui;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.ayman.searchengine.adapter.SearchResultsAdapter;
import com.ayman.searchengine.model.SearchResult;

import java.util.List;

public class CustomRecyclerView extends RecyclerView {
    private SearchResultsAdapter mAdapter;
    private boolean mNoMore, mNoInternet;
    private LoadMore mLoadMore;

    public CustomRecyclerView(@NonNull Context context) {
        super(context);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(LifecycleOwner owner, LoadMore loadMore, LiveData<List<SearchResult>> searchResults, LiveData<Boolean> timeOutLiveData, LiveData<Boolean> noMoreLiveData) {
        mLoadMore = loadMore;
        searchResults.observe(owner, new Observer<List<SearchResult>>() {
            @Override
            public void onChanged(List<SearchResult> searchResults) {
                mAdapter.setSearchResults(searchResults);
            }
        });
        timeOutLiveData.observe(owner, new Observer<Boolean>() {
            @Override
            public void onChanged(final Boolean timeOut) {
                mNoInternet = timeOut;
                post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setNoInternet(timeOut);
                    }
                });
            }
        });
        noMoreLiveData.observe(owner, new Observer<Boolean>() {
            @Override
            public void onChanged(final Boolean noMore) {
                mNoMore = noMore;
                post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setNoMore(noMore);
                    }
                });
            }
        });
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mNoMore || mNoInternet || mAdapter.isLoading() || !isLastItemVisible()) return;
                post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setLoading();
                    }
                });
                mLoadMore.loadMore(mAdapter.getLastId());
            }
        });
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter == null)
            throw new NullPointerException("CustomRecyclerView Adapter can't be null");
        mAdapter = (SearchResultsAdapter) adapter;
        mAdapter.setRetryListener(new SearchResultsAdapter.RetryListener() {
            @Override
            public void retry() {
                if (!mNoInternet) return;
                mLoadMore.loadMore(mAdapter.getLastId());
                post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setLoading();
                    }
                });
            }
        });
    }

    private boolean isLastItemVisible() {
        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null) return false;
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager lm = (LinearLayoutManager) layoutManager;
            return lm.findLastVisibleItemPosition() == mAdapter.getItemCount() - 1;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager lm = (StaggeredGridLayoutManager) layoutManager;
            int spanCount = lm.getSpanCount();
            int[] into = new int[spanCount];
            lm.findLastVisibleItemPositions(into);
            for (int i = 0; i < spanCount; i++) {
                if (into[i] == mAdapter.getItemCount() - 1)
                    return true;
            }
            return false;
        }
        return false;
    }

    public interface LoadMore {
        void loadMore(int lastID);
    }
}
