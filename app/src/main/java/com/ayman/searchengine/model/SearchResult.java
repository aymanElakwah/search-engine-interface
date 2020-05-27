package com.ayman.searchengine.model;

public abstract class SearchResult extends ListItem {

    public SearchResult() {
        super(ListItem.DATA);
    }

    public abstract String getUrl();

    public abstract Integer getID();
}
