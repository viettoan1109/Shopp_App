package com.actvn.shopapp.views;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.actvn.shopapp.R;

public class TermActivity extends AppCompatActivity {
    private Toolbar toolbarTerms;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        initView();
        actionToolbar();
    }

    private void initView() {
        toolbarTerms = findViewById(R.id.toolbarTerms);
        setSupportActionBar(toolbarTerms);

    }

    private void actionToolbar() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbarSearch.setNavigationIcon(R.drawable.ic_arrow_back_24);
        toolbarTerms.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    

}
