package com.actvn.shopapp.views;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.actvn.shopapp.MainActivity;
import com.actvn.shopapp.R;
import com.actvn.shopapp.roomdatabase.Cart;
import com.bumptech.glide.Glide;

public class ProptiesActivity extends AppCompatActivity {
    private  ImageView imageView;
    private  TextView tvName, tvMoney, tvLorem;
    private ImageButton btnBack;
    private  Button btnAddToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propties);
        init();
        Intent intent = getIntent();
      final String money =  intent.getStringExtra("money");
      final String name = intent.getStringExtra("name");
      final String url = intent.getStringExtra("imgs");
      final int id = intent.getIntExtra("id",0);
      //String content = intent.getStringExtra("content");
      tvMoney.setText(money);
      tvName.setText(name);
    //  tvLorem.setText(content);
      Glide.with(this).load(url).into(imageView);
      btnBack.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              onBackPressed();
          }
      });
      btnAddToCart.setOnClickListener(
              new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Cart cart=new Cart();
                      cart.setId(id);
                      cart.setImageid(url);
                      cart.setName(name);
                      cart.setPrice(money);
                      if ( ViewsSliderActivity.myDatabase.cartDao().isAddToCart(id)!=1){
                          ViewsSliderActivity.myDatabase.cartDao().addToCart(cart);

                      }else {
                          Toast.makeText(ProptiesActivity.this, "You are Already added to cart!", Toast.LENGTH_SHORT).show();

                      }
                      Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                      startActivity(intent1);
                      finish();

                  }
              }
      );

    }
    private  void init() {
        imageView = findViewById(R.id.image_product_id);
        tvName = findViewById(R.id.name_product_id);
        tvMoney = findViewById(R.id.tv_currency);
        tvLorem = findViewById(R.id.tv_lorem);
        btnBack = findViewById(R.id.back_to_parent);
        btnAddToCart = findViewById(R.id.btnAddtocart);
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}