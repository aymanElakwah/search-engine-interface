package com.ayman.searchengine.network.response;

import com.ayman.searchengine.model.ImageSearchResult;
import com.ayman.searchengine.model.SearchResult;
import com.ayman.searchengine.model.TextSearchResult;

import java.util.List;

public class ImageSearchResultsResponse {
    private Integer code;
    private List<ImageSearchResult> data;

    public ImageSearchResultsResponse(Integer code, List<ImageSearchResult> data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public List<ImageSearchResult> getData() {
        return data;
    }
}
