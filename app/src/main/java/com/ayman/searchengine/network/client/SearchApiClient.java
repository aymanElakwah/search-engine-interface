package com.ayman.searchengine.network.client;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ayman.searchengine.model.SearchResult;
import com.ayman.searchengine.network.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void click(String link) {
        ServiceGenerator.getSearchApi().click(mUser, link).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {
            }
        });
    }

    protected abstract void search();
}













