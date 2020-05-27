package com.ayman.searchengine.ui.search.image_search;

import com.ayman.searchengine.network.client.ImageSearchApiClient;
import com.ayman.searchengine.network.client.SearchApiClient;
import com.ayman.searchengine.ui.search.BaseViewModel;

public class ImageSearchViewModel extends BaseViewModel {

    public ImageSearchViewModel() {
        mSearchResults = mApi.getSearchResults();
    }

    @Override
    protected SearchApiClient getApi() {
        return ImageSearchApiClient.getInstance();
    }

}