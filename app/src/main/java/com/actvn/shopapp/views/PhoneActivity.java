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
import com.actvn.shopapp.api.model.Phone;
import com.actvn.shopapp.adapter.PhoneAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;

public class PhoneActivity extends AppCompatActivity {
    private RecyclerView rvPhone;
    private PhoneAdapter phoneAdapter;
    private ArrayList<Phone> phones = new ArrayList<>();
    private ProgressBar progressBar;
    private Toolbar toolbarPhone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        initView();
        actionToolbar();

    }

    private void initView() {
        toolbarPhone = findViewById(R.id.toolbarPhone);
        rvPhone = findViewById(R.id.rvPhone);
        progressBar = findViewById(R.id.progressBar);

        rvPhone.setHasFixedSize(true);
        rvPhone.setLayoutManager(new LinearLayoutManager(this));
        rvPhone.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        phoneAdapter = new PhoneAdapter(phones,this);
        rvPhone.setAdapter(phoneAdapter);

        Content content = new Content();
        content.execute();

    }

    private void actionToolbar() {
        setSupportActionBar(toolbarPhone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbarSearch.setNavigationIcon(R.drawable.ic_arrow_back_24);
        toolbarPhone.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private class Content extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(PhoneActivity.this,android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(PhoneActivity.this,android.R.anim.fade_out));
            phoneAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://app.baomoiday.net/public/category/mobile.html";
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
                    phones.add(new Phone(imgUrl,title,cost));
                }


            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
