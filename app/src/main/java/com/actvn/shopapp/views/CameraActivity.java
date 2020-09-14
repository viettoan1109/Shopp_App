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
import com.actvn.shopapp.adapter.CameraAdapter;
import com.actvn.shopapp.adapter.TvAdapter;
import com.actvn.shopapp.api.model.ProductsJsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class CameraActivity extends AppCompatActivity {

    private RecyclerView rvCamera;
    private CameraAdapter cameraAdapter;
    private ArrayList<ProductsJsoup> cameras = new ArrayList<>();
    private ProgressBar progressBar;
    private Toolbar toolbarCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initView();
        actionToolbar();
    }

    private void initView() {
        toolbarCamera = findViewById(R.id.toolbarCamera);
        rvCamera = findViewById(R.id.rvCamera);
        progressBar = findViewById(R.id.progressBar);

        rvCamera.setHasFixedSize(true);
        rvCamera.setLayoutManager(new LinearLayoutManager(this));
        rvCamera.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        cameraAdapter = new CameraAdapter(cameras, this);
        rvCamera.setAdapter(cameraAdapter);

        Content content = new Content();
        content.execute();
    }

    private void actionToolbar() {
        setSupportActionBar(toolbarCamera);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbarSearch.setNavigationIcon(R.drawable.ic_arrow_back_24);
        toolbarCamera.setNavigationOnClickListener(new View.OnClickListener() {
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
            progressBar.startAnimation(AnimationUtils.loadAnimation(CameraActivity.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(CameraActivity.this, android.R.anim.fade_out));
            cameraAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "http://app.baomoiday.net/public/category/camera.html";
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
                    cameras.add(new ProductsJsoup(imgUrl, title, cost));
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}