package com.ayman.searchengine.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class TextSearchResult extends SearchResult {
    private Integer id;
    private String url;
    private String iconUrl;
    private String title;
    private String description;

    public TextSearchResult(Integer id, String url, String iconUrl, String title, String description) {
        this.id = id;
        this.url = url;
        this.iconUrl = iconUrl;
        this.title = title;
        this.description = description;
    }

    @BindingAdapter("iconURL")
    public static void loadUrlIcon(ImageView imageView, String iconURL) {
        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(new RequestOptions().circleCrop())
                .load(iconURL)
                .into(imageView);
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
