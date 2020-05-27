package com.ayman.searchengine.ui.search.text_search;

import androidx.lifecycle.LiveData;

import com.ayman.searchengine.model.SearchResult;
import com.ayman.searchengine.network.client.SearchApiClient;
import com.ayman.searchengine.network.client.TextSearchApiClient;
import com.ayman.searchengine.ui.search.BaseViewModel;

import java.util.List;

public class TextSearchViewModel extends BaseViewModel {

    public TextSearchViewModel() {
        mSearchResults = mApi.getSearchResults();
    }

    @Override
    protected SearchApiClient getApi() {
        return TextSearchApiClient.getInstance();
    }

}