package com.ayman.searchengine.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.ayman.searchengine.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImageSearchResult extends SearchResult {
    private Integer id;
    private String url;
    private String title;

    public ImageSearchResult(Integer id, String url, String title) {
        this.id = id;
        this.url = url;
        this.title = title;
    }

    @BindingAdapter("imageURL")
    public static void loadImage(ImageView imageView, String imageURL) {
        RequestOptions requestOptions = new RequestOptions().
                placeholder(R.drawable.placeholder)
                .error(R.drawable.image_not_found);
        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(imageURL)
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

    public String getBaseUrl() {
        String[] split = url.split("/");
        if (split.length >= 3)
            return split[2];
        return url;
    }

    public String getTitle() {
        return title;
    }

}
