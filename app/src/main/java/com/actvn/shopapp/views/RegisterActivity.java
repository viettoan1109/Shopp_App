package com.actvn.shopapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.actvn.shopapp.R;
import com.actvn.shopapp.api.model.Register;
import com.actvn.shopapp.api.model.ResultRegister;
import com.actvn.shopapp.api.service.UserService;
import com.actvn.shopapp.login.LoginActivity;
import com.actvn.shopapp.utils.ConstApp;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private UserService userServic;
    private EditText edtFirtName, edtEmailR, edtPassR, edtAdressR, edtPhoneR;
    private TextView txtSignIn_re;
    private Button resiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        innit();

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(logging);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ConstApp.BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        userServic = retrofit.create(UserService.class);

    }

    private static String name;

    public void register() {
        String firtname = edtFirtName.getText().toString().trim();
        String email = edtEmailR.getText().toString().trim();
        String pass = edtPassR.getText().toString().trim();
        String address = edtAdressR.getText().toString().trim();
        String phone = edtPhoneR.getText().toString().trim();
        Register register = new Register(firtname, email, pass, address, phone);
        Call<ResultRegister> call = userServic.create(register);
        call.enqueue(new Callback<ResultRegister>() {
            @Override
            public void onResponse(Call<ResultRegister> call, Response<ResultRegister> response) {
                if (response.isSuccessful()) {
                    name = response.body().getFirstName();
                    Toast.makeText(RegisterActivity.this, name + " Đã được tạo thành công", Toast.LENGTH_SHORT).show();
                    login();
                } else {
                    Toast.makeText(RegisterActivity.this, "khong ket noi hoac tk da ton tai", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultRegister> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "erros", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }

    public void innit() {
        edtFirtName = (EditText) findViewById(R.id.edtFirtname);
        edtEmailR = (EditText) findViewById(R.id.edtEmailRegister);
        edtPassR = (EditText) findViewById(R.id.edtPassR);
        edtAdressR = (EditText) findViewById(R.id.edtAdressR);
        edtPhoneR = (EditText) findViewById(R.id.edtPhoneR);
        resiter = (Button) findViewById(R.id.btnRegister);
        txtSignIn_re = findViewById(R.id.txtSignIn_re);

        txtSignIn_re.setOnClickListener(this);
        resiter.setOnClickListener(this);
    }

    public void login() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                register();
                break;

            case R.id.txtSignIn_re:
                login();
                break;
        }

    }
}