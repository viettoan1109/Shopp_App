package com.actvn.shopapp.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.actvn.shopapp.R;

public class QuestionActivity extends AppCompatActivity {

    private Toolbar toolbarQuestion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        initView();
        actionToolbar();
    }

    @SuppressLint("ResourceAsColor")
    private void initView() {
        toolbarQuestion = findViewById(R.id.toolbarQuestion);
        setSupportActionBar(toolbarQuestion);

    }

    private void actionToolbar() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbarSearch.setNavigationIcon(R.drawable.ic_arrow_back_24);
        toolbarQuestion.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
