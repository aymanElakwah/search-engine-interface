package com.ayman.searchengine.network.client;


import android.util.Log;

import androidx.lifecycle.LiveData;

import com.ayman.searchengine.model.SearchResult;
import com.ayman.searchengine.network.ServiceGenerator;
import com.ayman.searchengine.network.response.TextSearchResultsResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TextSearchApiClient extends SearchApiClient {

    private static final int RESULTS_PER_PAGE = 10;
    private static TextSearchApiClient instance;
    private Call<TextSearchResultsResponse> mTextSearchCall;
    private Callback<TextSearchResultsResponse> mTextSearchCallBack;

    private TextSearchApiClient() {
        mTextSearchCallBack = new Callback<TextSearchResultsResponse>() {
            @Override
            public void onResponse(Call<TextSearchResultsResponse> call, Response<TextSearchResultsResponse> response) {
                if (!response.isSuccessful()) return;
                TextSearchResultsResponse body = response.body();
                List<SearchResult> list = new ArrayList<SearchResult>(body.getData());
                if (mLastID == -1) {
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
            public void onFailure(Call<TextSearchResultsResponse> call, Throwable t) {
                if(call.isCanceled()) return;
                mNoInternet.postValue(true);
                if (mLastID == -1) mSearchResults.postValue(null);
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
        mTextSearchCall = getTextSearchResults(mQuery, mLastID);
        mTextSearchCall.enqueue(mTextSearchCallBack);
    }

    private Call<TextSearchResultsResponse> getTextSearchResults(String query, int lastID) {
        return ServiceGenerator.getSearchApi().searchText(
                query,
                String.valueOf(lastID),
                RESULTS_PER_PAGE
        );
    }
}













