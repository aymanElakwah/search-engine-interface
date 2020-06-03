package com.ayman.searchengine.network.client;


import androidx.annotation.NonNull;

import com.ayman.searchengine.model.SearchResult;
import com.ayman.searchengine.model.TextSearchResult;
import com.ayman.searchengine.network.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TextSearchApiClient extends SearchApiClient {

    private static final int RESULTS_PER_PAGE = 10;
    private static TextSearchApiClient instance;
    private Call<List<TextSearchResult>> mTextSearchCall;
    private Callback<List<TextSearchResult>> mTextSearchCallBack;

    private TextSearchApiClient() {
        mTextSearchCallBack = new Callback<List<TextSearchResult>>() {
            @Override
            public void onResponse(@NonNull Call<List<TextSearchResult>> call, @NonNull Response<List<TextSearchResult>> response) {
                if (!response.isSuccessful()) return;
                List<SearchResult> list = new ArrayList<SearchResult>(response.body());
                if (mPageNumber == 1) {
                    mSearchResults.postValue(list);
                } else {
                    List<SearchResult> searchResults = mSearchResults.getValue();
                    searchResults.addAll(list);
                    mSearchResults.postValue(searchResults);
                }
                if (list.size() == 0 || list.size() % RESULTS_PER_PAGE != 0)
                    mIsQueryExhausted.postValue(true);
            }

            @Override
            public void onFailure(@NonNull Call<List<TextSearchResult>> call, @NonNull Throwable t) {
                if (call.isCanceled()) return;
                mNoInternet.postValue(true);
                if (mPageNumber == 1) mSearchResults.postValue(null);
            }
        };
    }

    public static TextSearchApiClient getInstance() {
        if (instance == null) {
            instance = new TextSearchApiClient();
        }
        return instance;
    }

    @Override
    protected void search() {
        if (mTextSearchCall != null) mTextSearchCall.cancel();
        mIsQueryExhausted.setValue(false);
        mNoInternet.setValue(false);
        mTextSearchCall = getTextSearchResults(mQuery, mPageNumber, mCountry, mUser);
        mTextSearchCall.enqueue(mTextSearchCallBack);
    }

    private Call<List<TextSearchResult>> getTextSearchResults(String query, int pageNumber, String country, String user) {
        return ServiceGenerator.getSearchApi().searchText(
                query,
                pageNumber,
                RESULTS_PER_PAGE,
                country,
                user
        );
    }
}













