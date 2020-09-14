package com.actvn.shopapp.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.actvn.shopapp.R;

public class LocatorActivity extends AppCompatActivity {
    private Toolbar toolbarLocator;
    private TextView txtCall1, txtCall2, txtCall3;
    private Button btnLocator1, btnLocator2, btnLocator3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locator);
        initView();
        actionToolbar();
    }

    private void initView() {
        toolbarLocator = findViewById(R.id.toolbarLocator);
        txtCall1 = findViewById(R.id.txtCall1);
        txtCall2 = findViewById(R.id.txtCall2);
        txtCall2 = findViewById(R.id.txtCall2);
        btnLocator1 = findViewById(R.id.btnGetLocation1);
        btnLocator2 = findViewById(R.id.btnGetLocation2);
        btnLocator3 = findViewById(R.id.btnGetLocation3);
        setSupportActionBar(toolbarLocator);

        btnLocator1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });

        txtCall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneCall();
            }
        });
    }

    private void phoneCall() {
        String phone = "0972103797";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel: " + phone));
        startActivity(intent);

    }

    private void actionToolbar() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLocator.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
