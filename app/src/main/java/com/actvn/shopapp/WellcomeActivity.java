package com.actvn.shopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.actvn.shopapp.login.LoginActivity;
import com.actvn.shopapp.views.RegisterActivity;

public class WellcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignIn;
    private TextView btnSignUp;
    private ImageView imgClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        innit();
    }

    public void innit() {
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        imgClose = findViewById(R.id.imgClose);

        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        imgClose.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignIn:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.btnSignUp:
                Intent intent1 = new Intent(this, RegisterActivity.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.imgClose:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                finish();
                break;
        }

    }
}