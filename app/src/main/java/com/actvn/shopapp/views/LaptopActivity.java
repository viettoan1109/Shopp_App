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
import com.actvn.shopapp.adapter.LaptopAdapter;
import com.actvn.shopapp.adapter.PhoneAdapter;
import com.actvn.shopapp.api.model.Laptop;
import com.actvn.shopapp.api.model.Phone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class LaptopActivity extends AppCompatActivity {
    private RecyclerView rvLaptop;
    private LaptopAdapter laptopAdapter;
    private ArrayList<Laptop> laptops = new ArrayList<>();
    private ProgressBar progressBar;
    private Toolbar toolbarLaptop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);
        initView();
        actionToolbar();
    }

    private void initView() {
        toolbarLaptop = findViewById(R.id.toolbarLaptop);
        rvLaptop = findViewById(R.id.rvLaptop);
        progressBar = findViewById(R.id.progressBar);

        rvLaptop.setHasFixedSize(true);
        rvLaptop.setLayoutManager(new LinearLayoutManager(this));
        rvLaptop.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        laptopAdapter = new LaptopAdapter(laptops,this);
        rvLaptop.setAdapter(laptopAdapter);

        Content content = new Content();
        content.execute();


    }

    private void actionToolbar() {
        setSupportActionBar(toolbarLaptop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbarSearch.setNavigationIcon(R.drawable.ic_arrow_back_24);
        toolbarLaptop.setNavigationOnClickListener(new View.OnClickListener() {
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
            progressBar.startAnimation(AnimationUtils.loadAnimation(LaptopActivity.this,android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(LaptopActivity.this,android.R.anim.fade_out));
            laptopAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://app.baomoiday.net/public/category/computers.html";
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
                    laptops.add(new Laptop(imgUrl,title,cost));
                }


            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
