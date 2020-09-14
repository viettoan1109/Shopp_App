package com.actvn.shopapp.views;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.actvn.shopapp.R;
import com.actvn.shopapp.adapter.HeadphoneAdapter;
import com.actvn.shopapp.adapter.TabletAdapter;
import com.actvn.shopapp.api.model.Headphone;
import com.actvn.shopapp.api.model.Tablet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class HeadphoneActivity extends AppCompatActivity {
    private RecyclerView rvHeadphone;
    private HeadphoneAdapter headphoneAdapter;
    private ArrayList<Headphone> headphones = new ArrayList<>();
    private ProgressBar progressBar;
    private Toolbar toolbarHeadphone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headphone);
        initView();
        actionToolbar();

    }

    private void initView() {
        toolbarHeadphone = findViewById(R.id.toolbarHeadphone);
        rvHeadphone = findViewById(R.id.rvHeadphone);
        progressBar = findViewById(R.id.progressBar);

        rvHeadphone.setHasFixedSize(true);
        rvHeadphone.setLayoutManager(new LinearLayoutManager(this));
        rvHeadphone.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        headphoneAdapter = new HeadphoneAdapter(headphones, this);
        rvHeadphone.setAdapter(headphoneAdapter);

        Content content = new HeadphoneActivity.Content();
        content.execute();

    }

    private void actionToolbar() {
        setSupportActionBar(toolbarHeadphone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbarSearch.setNavigationIcon(R.drawable.ic_arrow_back_24);
        toolbarHeadphone.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(HeadphoneActivity.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(HeadphoneActivity.this, android.R.anim.fade_out));
            headphoneAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "http://app.baomoiday.net/public/category/appliances.html";
                Document doc = (Document) Jsoup.connect(url).get();
                Elements data = doc.select("div.product-main");
                int size = data.size();
                for (int i = 0; i < size; i++) {
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
                    headphones.add(new Headphone(imgUrl, title, cost));
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
