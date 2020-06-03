package com.ayman.searchengine.ui.search;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ayman.searchengine.MainActivity;
import com.ayman.searchengine.R;
import com.ayman.searchengine.adapter.SearchResultsAdapter;
import com.ayman.searchengine.databinding.FragmentSearchBinding;
import com.ayman.searchengine.ui.CustomRecyclerView;
import com.ayman.searchengine.ui.home.Countries;
import com.ayman.searchengine.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchFragment extends Fragment implements SearchResultsAdapter.UrlClickListener {
    private static final int RECOGNIZE_SPEECH = 10;
    protected NavBackStackEntry mBackStackEntry;
    private FragmentSearchBinding mBinding;
    private BaseViewModel mViewModel;
    private CustomRecyclerView mRecyclerView;
    private AutoCompleteTextView mSearchBox;
    private SearchResultsAdapter mAdapter;
    private String mUserID;
    private String mCountryCode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        mBackStackEntry = navController.getBackStackEntry(R.id.mobile_navigation);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        mViewModel = getViewModel();
        mBinding.setModel(mViewModel);
        View root = mBinding.getRoot();
        loadUser();
        setupSearchBox(root);
        setupRecyclerView(root);
        setupAutoComplete(root);
        final String currentQuery = ((MainActivity) requireActivity()).getCurrentQuery();
        if (currentQuery == null) {
            mSearchBox.post(new Runnable() {
                @Override
                public void run() {
                    showKeyboard(mSearchBox);
                }
            });
            return root;
        }
        if (!mViewModel.getSearchedCountry().equals(mCountryCode) || !mViewModel.getSearchQuery().equals(currentQuery))
            search(currentQuery);
        mRecyclerView.requestFocus();
        return root;
    }

    private void loadUser() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        mCountryCode = Countries.getSavedCountryCode(preferences);
        mUserID = preferences.getString(HomeViewModel.USER_ID, "");
    }

    private void setupAutoComplete(View root) {
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_spinner_dropdown_item);
        final AutoCompleteTextView autoComplete = root.findViewById(R.id.search_box);
        autoComplete.setThreshold(1);
        autoComplete.setAdapter(adapter);
        mViewModel.getSuggestions().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter.clear();
                adapter.addAll(strings);
                adapter.notifyDataSetChanged();
                // force autocomplete to update
                adapter.getFilter().filter(autoComplete.getText(), autoComplete);
            }
        });
    }


    private void setupSearchBox(View root) {
        mSearchBox = root.findViewById(R.id.search_box);
        mSearchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String query = v.getText().toString().trim();
                    search(query);
                    mSearchBox.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mSearchBox.clearFocus();
                        }
                    }, 10);
                }
                return false;
            }
        });
        mSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean showClearText = !s.toString().trim().isEmpty();
                mBinding.setShowClearText(showClearText);
                if (showClearText) mViewModel.complete(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        ImageView mClearButton = root.findViewById(R.id.clear_text);
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchBox.setText("");
                showKeyboard(mSearchBox);
            }
        });
        ImageView mMicButton = root.findViewById(R.id.mic);
        mMicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(mSearchBox);
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(intent, RECOGNIZE_SPEECH);
            }
        });
    }

    private void setupRecyclerView(View root) {
        mAdapter = new SearchResultsAdapter(this, getItemLayoutID());
        mAdapter.setHasStableIds(true);
        mRecyclerView = root.findViewById(R.id.text_search_results);
        mRecyclerView.requestFocus();
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.setAdapter(mAdapter);
        CustomRecyclerView.LoadMore loadMore = new CustomRecyclerView.LoadMore() {
            @Override
            public void loadMore(int pageNumber) {
                mViewModel.searchNext(pageNumber);
            }
        };
        mRecyclerView.init(getViewLifecycleOwner(), loadMore,
                mViewModel.getSearchResults(), mViewModel.isRequestTimedOut(), mViewModel.isQueryExhausted());

    }

    private void search(String query) {
        ((MainActivity) requireActivity()).setCurrentQuery(query);
        mAdapter.clear();
        mAdapter.setLoading();
        mViewModel.search(query, mCountryCode, mUserID);
    }

    private void showKeyboard(View view) {
        Context context = getContext();
        if (context == null) return;
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    private void hideKeyboard(View view) {
        Context context = getContext();
        if (context == null) return;
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void openURL(String url) {
        mViewModel.click(url);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RECOGNIZE_SPEECH && data != null) {
            ArrayList result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (result == null || result.isEmpty()) return;
            String text = result.get(0).toString();
            mSearchBox.setText(text);
            mSearchBox.setSelection(text.length());
            showKeyboard(mSearchBox);
        }
    }

    protected abstract BaseViewModel getViewModel();

    protected abstract RecyclerView.LayoutManager getLayoutManager();

    protected abstract int getItemLayoutID();
}
