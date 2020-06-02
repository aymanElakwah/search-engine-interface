package com.ayman.searchengine;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private String currentQuery;

    public String getCurrentQuery() {
        return currentQuery;
    }

    public void setCurrentQuery(String currentQuery) {
        this.currentQuery = currentQuery;
    }
}
