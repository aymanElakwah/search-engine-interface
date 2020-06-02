package com.ayman.searchengine.network.client;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ayman.searchengine.model.SearchResult;

import java.util.List;

public abstract class SearchApiClient {

    String mQuery;
    int mPageNumber;
    String mCountry;
    String mUser;
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

    public void search(String query, String country, String user) {
        mQuery = query;
        mPageNumber = 1;
        mCountry = country;
        mUser = user;
        mSearchResults.setValue(null);
        search();
    }

    public void searchNext(int pageNumber) {
        mPageNumber = pageNumber;
        search();
    }

    protected abstract void search();
}













