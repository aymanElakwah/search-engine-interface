package com.ayman.searchengine.network.response;

import com.ayman.searchengine.model.SearchResult;
import com.ayman.searchengine.model.TextSearchResult;

import java.util.List;

public class TextSearchResultsResponse {
    private Integer code;
    private List<TextSearchResult> data;

    public TextSearchResultsResponse(Integer code, List<TextSearchResult> data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public List<TextSearchResult> getData() {
        return data;
    }
}
