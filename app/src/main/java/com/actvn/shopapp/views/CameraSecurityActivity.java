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
import com.actvn.shopapp.adapter.CameraSecurityAdapter;
import com.actvn.shopapp.adapter.TvAdapter;
import com.actvn.shopapp.api.model.ProductsJsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class CameraSecurityActivity extends AppCompatActivity {

    private RecyclerView rvCameraSecurity;
    private CameraSecurityAdapter cameraSecurityAdapter;
    private ArrayList<ProductsJsoup> cameraSecuritys = new ArrayList<>();
    private ProgressBar progressBar;
    private Toolbar toolbarCameraSecuritys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_security);
        initView();
        actionToolbar();
    }

    private void initView() {
        toolbarCameraSecuritys = findViewById(R.id.toolbarCameraSecurity);
        rvCameraSecurity = findViewById(R.id.rvCameraSecurity);
        progressBar = findViewById(R.id.progressBar);

        rvCameraSecurity.setHasFixedSize(true);
        rvCameraSecurity.setLayoutManager(new LinearLayoutManager(this));
        rvCameraSecurity.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        cameraSecurityAdapter = new CameraSecurityAdapter(cameraSecuritys, this);
        rvCameraSecurity.setAdapter(cameraSecurityAdapter);

        Content content = new Content();
        content.execute();
    }

    private void actionToolbar() {
        setSupportActionBar(toolbarCameraSecuritys);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbarSearch.setNavigationIcon(R.drawable.ic_arrow_back_24);
        toolbarCameraSecuritys.setNavigationOnClickListener(new View.OnClickListener() {
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
            progressBar.startAnimation(AnimationUtils.loadAnimation(CameraSecurityActivity.this,android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(CameraSecurityActivity.this,android.R.anim.fade_out));
            cameraSecurityAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "http://app.baomoiday.net/public/category/men-clothing.html";
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
                    cameraSecuritys.add(new ProductsJsoup(imgUrl,title,cost));
                }


            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }

}