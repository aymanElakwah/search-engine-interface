package com.ayman.searchengine.ui.search.image_search;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavArgument;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.ayman.searchengine.R;
import com.ayman.searchengine.ui.search.BaseViewModel;
import com.ayman.searchengine.ui.search.SearchFragment;

public class ImageSearchFragment extends SearchFragment {

    @Override
    protected BaseViewModel getViewModel() {
        return new ViewModelProvider(mBackStackEntry).get(ImageSearchViewModel.class);
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected int getItemLayoutID() {
        return R.layout.image_search_result_item;
    }
}
