package com.actvn.shopapp.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.actvn.shopapp.MainActivity;
import com.actvn.shopapp.R;
import com.actvn.shopapp.api.model.Login;
import com.actvn.shopapp.api.model.ResultLogin;
import com.actvn.shopapp.api.service.UserService;
import com.actvn.shopapp.fragment.ProfileFragment;
import com.actvn.shopapp.views.HeadphoneActivity;
import com.actvn.shopapp.views.RegisterActivity;
import com.actvn.shopapp.utils.ConstApp;
import com.actvn.shopapp.utils.ShareStoreUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private UserService userServic;
    private EditText edtEmail, edtPass;
    private TextView txtDismiss;
    private TextView txtSignUp;
    private ImageView imgClose;
    private Button btnSignIN;

    private ProfileFragment profileFragment;
    private ProgressBar progressBar;

    LinearLayout lnWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // neu token khac null thi chay toi main
        /*if (ShareStoreUtils.getToken(this) != null) {
            startMainActivity();
            return;
        }*/
        setContentView(R.layout.activity_login);
        // doan nay tim hieu roi note lai
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC.BASIC);
        client.addInterceptor(logging);
        //khoi tao retrofit
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ConstApp.BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create());
        //chay retrofit
        Retrofit retrofit = builder.build();
        //khoi tao phuong thuc trong userservice
        userServic = retrofit.create(UserService.class);
        innit();
    }

    public void innit() {
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btnSignIN = (Button) findViewById(R.id.btnSignIN_lo);
        txtSignUp = findViewById(R.id.txtSignUp);
        imgClose = findViewById(R.id.imgClose);

        txtDismiss = findViewById(R.id.txtDismiss);
        lnWrong = findViewById(R.id.lnWrong);

        progressBar = findViewById(R.id.progressBar);

        profileFragment = new ProfileFragment();

        btnSignIN.setOnClickListener(this);
        txtSignUp.setOnClickListener(this);
        txtDismiss.setOnClickListener(this);
        imgClose.setOnClickListener(this);
    }

    public void login() {
        String email = edtEmail.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();
        Log.d("pass", pass);
        Login login = new Login(email, pass);
        Call<ResultLogin> call = userServic.login(login);
        call.enqueue(new Callback<ResultLogin>() {
            @Override
            public void onResponse(Call<ResultLogin> call, Response<ResultLogin> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // neu ket noi thanh cong se luu token va chay toi man main
                    ShareStoreUtils.saveToken(LoginActivity.this, response.body().getAccessToken());
                    startMainActivity();

                } else {
                    lnWrong.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResultLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "erros", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

        Toast.makeText(LoginActivity.this, "Login is failed", Toast.LENGTH_SHORT);

    }

    private void startMainActivity() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtDismiss:
                lnWrong.setVisibility(View.INVISIBLE);
                break;
            case R.id.btnSignIN_lo:
                int current = progressBar.getProgress();
                if (current >= progressBar.getMax()){
                    current = 0;
                }
                progressBar.setProgress(current + 10);
                login();
                break;
            case R.id.txtSignUp:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;

            case R.id.imgClose:
                onBackPressed();
                break;
        }
    }

}