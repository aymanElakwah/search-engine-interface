package com.ayman.searchengine.model;

public class ListItem {
    public static final int DATA = 0;
    public static final int LOADING = 1;
    public static final int NO_MORE = 2;
    public static final int NO_INTERNET = 3;

    private int mType;

    public ListItem(int type) {
        this.mType = type;
    }

    public int getType() {
        return mType;
    }
}
