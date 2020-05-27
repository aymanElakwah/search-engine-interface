package com.ayman.searchengine.ui.search.text_search;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavArgument;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ayman.searchengine.R;
import com.ayman.searchengine.ui.search.BaseViewModel;
import com.ayman.searchengine.ui.search.SearchFragment;

public class TextSearchFragment extends SearchFragment {


    @Override
    protected BaseViewModel getViewModel() {
        return new ViewModelProvider(mBackStackEntry).get(TextSearchViewModel.class);
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    protected int getItemLayoutID() {
        return R.layout.text_search_result_item;
    }

}
