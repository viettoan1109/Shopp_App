package com.actvn.shopapp.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.actvn.shopapp.R;
import com.actvn.shopapp.api.model.Data;
import com.actvn.shopapp.api.model.Detail;
import com.actvn.shopapp.api.model.Order;
import com.actvn.shopapp.api.model.User;
import com.actvn.shopapp.api.service.UserService;
import com.actvn.shopapp.utils.ConstApp;
import com.actvn.shopapp.utils.ShareStoreUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class OrderActivity extends AppCompatActivity {

    private UserService userService;
    private Order order;
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<Data> datas = new ArrayList<>();
    private List<Detail> details = new ArrayList<>();

    private Toolbar toolbarOrder;
    private TextView txtOrderTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(logging);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ConstApp.BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        userService = retrofit.create(UserService.class);
        loadJson();
        initView();
        actionToolbar();



    }

    private void initView() {
        toolbarOrder = findViewById(R.id.toolbarOrder);

        txtOrderTextView = findViewById(R.id.txtOrder);

        orderAdapter = new OrderAdapter(this, details, datas);
        recyclerView = findViewById(R.id.recyclerViewOrder);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(orderAdapter);
    }

    private void actionToolbar() {
        setSupportActionBar(toolbarOrder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbarSearch.setNavigationIcon(R.drawable.ic_arrow_back_24);
        toolbarOrder.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void loadJson() {
        String token_type ="Bearer"+" "+ShareStoreUtils.getToken(this);
        Call<Order> call = userService.getOrder(token_type);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(@NotNull Call<Order> call, @NotNull Response<Order> response) {
                if (response.body()!= null) {

                    Toast.makeText(getApplicationContext(), "dd", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Log.d("null data", "sssssss");
                }
            }

            @Override
            public void onFailure(@NotNull Call<Order> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "d555d", Toast.LENGTH_SHORT).show();

            }
        });

    }
}