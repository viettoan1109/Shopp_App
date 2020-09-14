package com.actvn.shopapp.search;

import android.content.SearchRecentSuggestionsProvider;
import android.provider.SearchRecentSuggestions;

public class SearchSuggestions extends SearchRecentSuggestionsProvider {
    public static final String AUTHORITY = "com.actvn.shopapp.search.SearchSuggestions";
    public static final int MODE = DATABASE_MODE_QUERIES;

    public SearchSuggestions() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
