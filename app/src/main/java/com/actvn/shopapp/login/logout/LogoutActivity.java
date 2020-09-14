package com.actvn.shopapp.login.logout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.actvn.shopapp.WellcomeActivity;
import com.actvn.shopapp.R;
import com.actvn.shopapp.utils.ShareStoreUtils;

public class LogoutActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        findViewById(R.id.btn_logout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ShareStoreUtils.saveToken(this, null);
        Intent intent = new Intent();
        intent.setClass(this, WellcomeActivity.class);
        startActivity(intent);
        finish();
    }
}