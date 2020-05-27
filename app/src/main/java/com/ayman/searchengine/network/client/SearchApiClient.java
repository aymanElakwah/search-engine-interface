package com.ayman.searchengine.network.client;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ayman.searchengine.model.SearchResult;

import java.util.List;

public abstract class SearchApiClient {

    String mQuery;
    int mLastID;
    MutableLiveData<List<SearchResult>> mSearchResults;
    MutableLiveData<Boolean> mNoInternet = new MutableLiveData<>();
    MutableLiveData<Boolean> mIsQueryExhausted = new MutableLiveData<>();

    SearchApiClient() {
        mSearchResults = new MutableLiveData<>();
    }

    public LiveData<Boolean> isNoInternet() {
        return mNoInternet;
    }

    public LiveData<Boolean> isQueryExhausted() {
        return mIsQueryExhausted;
    }

    public LiveData<List<SearchResult>> getSearchResults() {
        return mSearchResults;
    }

    public void search(String query) {
        mQuery = query;
        mLastID = -1;
        search();
    }

    public void searchNext(int lastID) {
        mLastID = lastID;
        search();
    }

    protected abstract void search();
}













