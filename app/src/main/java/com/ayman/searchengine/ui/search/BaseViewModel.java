package com.ayman.searchengine.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ayman.searchengine.model.SearchResult;
import com.ayman.searchengine.network.client.SearchApiClient;
import com.ayman.searchengine.network.client.SuggestionsApiClient;

import java.util.List;

public abstract class BaseViewModel extends ViewModel {
    protected SearchApiClient mApi;
    protected LiveData<List<SearchResult>> mSearchResults;
    private SuggestionsApiClient mSuggestionsApi;
    private MutableLiveData<String> searchQuery;
    private LiveData<Boolean> isQueryExhausted;
    private LiveData<Boolean> isRequestTimedOut;
    private LiveData<List<String>> suggestions;

    protected BaseViewModel() {
        mApi = getApi();
        mSuggestionsApi = SuggestionsApiClient.getInstance();
        isQueryExhausted = mApi.isQueryExhausted();
        isRequestTimedOut = mApi.isNoInternet();
        suggestions = mSuggestionsApi.getSuggestions();
        searchQuery = new MutableLiveData<>("");
    }

    public LiveData<String> getSearchQuery() {
        return searchQuery;
    }

    LiveData<Boolean> isQueryExhausted() {
        return isQueryExhausted;
    }

    LiveData<Boolean> isRequestTimedOut() {
        return isRequestTimedOut;
    }

    LiveData<List<String>> getSuggestions() {
        return suggestions;
    }

    public void complete(String query) {
        mSuggestionsApi.complete(query);
    }

    public void search(String query) {
        searchQuery.setValue(query);
        mApi.search(query);
    }

    void searchNext(int lastID) {
        mApi.searchNext(lastID);
    }


    LiveData<List<SearchResult>> getSearchResults() {
        return mSearchResults;
    }

    protected abstract SearchApiClient getApi();
}
