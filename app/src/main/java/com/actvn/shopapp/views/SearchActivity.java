package com.actvn.shopapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.actvn.shopapp.R;
import com.actvn.shopapp.api.model.Data;
import com.actvn.shopapp.api.model.Products;
import com.actvn.shopapp.api.service.UserService;
import com.actvn.shopapp.adapter.SearchAdapter;
import com.actvn.shopapp.utils.ConstApp;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    private UserService userService;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Data> datas = new ArrayList<>();
    private List<Products> mProducts;
    private SearchAdapter searchAdapter;

    private Toolbar toolbarSearch;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(logging);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ConstApp.BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        userService = retrofit.create(UserService.class);


        initView();
        actionToolbar();
        search();
    }

    private void initView() {

        searchAdapter = new SearchAdapter(this, datas);

        recyclerView = findViewById(R.id.recyclerViewSearch);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(searchAdapter);

        toolbarSearch = findViewById(R.id.toolbarSearch);
        searchView = findViewById(R.id.searchView);
    }

    private void actionToolbar() {
        setSupportActionBar(toolbarSearch);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbarSearch.setNavigationIcon(R.drawable.ic_arrow_back_24);
        toolbarSearch.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void search() {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 1) {
                    loadJson(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                loadJson(newText);
                return false;
            }
        });

    }

    private void loadJson(final String keyword) {

        Call<Products> call;
        if (keyword.length() > 0){
            call = userService.getSearchProducts(keyword);
            //Toast.makeText(SearchActivity.this, keyword, Toast.LENGTH_SHORT).show();

        } else {
            call = userService.getProducts();
            Toast.makeText(SearchActivity.this, "No Result", Toast.LENGTH_SHORT).show();

        }

        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if (response.isSuccessful() && response.body().getData() != null){
                    if (!datas.isEmpty()){
                        datas.clear();
                    }
                    datas = response.body().getData();
                    searchAdapter = new SearchAdapter(SearchActivity.this, datas);
                    recyclerView.setAdapter(searchAdapter);
                    searchAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(SearchActivity.this, "No Result", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {

            }

        });

    }
}