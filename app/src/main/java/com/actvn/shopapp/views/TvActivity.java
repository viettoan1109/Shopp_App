package com.actvn.shopapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.actvn.shopapp.R;
import com.actvn.shopapp.adapter.LaptopAdapter;
import com.actvn.shopapp.adapter.TvAdapter;
import com.actvn.shopapp.api.model.Laptop;
import com.actvn.shopapp.api.model.ProductsJsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class TvActivity extends AppCompatActivity {

    private RecyclerView rvTv;
    private TvAdapter tvAdapter;
    private ArrayList<ProductsJsoup> tvs = new ArrayList<>();
    private ProgressBar progressBar;
    private Toolbar toolbarTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);
        initView();
        actionToolbar();
    }

    private void initView() {
        toolbarTv = findViewById(R.id.toolbarTv);
        rvTv = findViewById(R.id.rvTv);
        progressBar = findViewById(R.id.progressBar);

        rvTv.setHasFixedSize(true);
        rvTv.setLayoutManager(new LinearLayoutManager(this));
        rvTv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        tvAdapter = new TvAdapter(tvs, this);
        rvTv.setAdapter(tvAdapter);

        Content content = new Content();
        content.execute();
    }

    private void actionToolbar() {
        setSupportActionBar(toolbarTv);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbarSearch.setNavigationIcon(R.drawable.ic_arrow_back_24);
        toolbarTv.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private class Content extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(TvActivity.this,android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(TvActivity.this,android.R.anim.fade_out));
            tvAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "http://app.baomoiday.net/public/category/tv.html";
                Document doc = (Document) Jsoup.connect(url).get();
                Elements data = doc.select("div.product-main");
                int size = data.size();
                for (int i=0; i<size; i++){
                    String imgUrl = data.select("div.product-photo")
                            .select("img")
                            .eq(i)
                            .attr("src");
                    String title = data.select("div.product-name")
                            .select("h4")
                            .eq(i)
                            .text();
                    String cost = data.select("div.product-price")
                            .eq(i)
                            .text();
                    tvs.add(new ProductsJsoup(imgUrl,title,cost));
                }


            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }

}