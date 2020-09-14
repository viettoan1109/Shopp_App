package com.actvn.shopapp.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.actvn.shopapp.R;
import com.actvn.shopapp.adapter.CartProductAdapter;
import com.actvn.shopapp.login.LoginActivity;
import com.actvn.shopapp.roomdatabase.Cart;
import com.actvn.shopapp.views.ViewsSliderActivity;

import java.util.List;


public class CartFragment extends Fragment  {

    RecyclerView rv;
    int counts = 0;
    CartProductAdapter cartProductAdapter;
    List<Cart> carts;
    private View view;
    private  TextView tvcount;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        init();
        getCartData();
        int count=cartProductAdapter.getItemCount();
        updatacartcount();

        return view;

     }
     public void init()
     {
         tvcount =view.findViewById(R.id.txtcartcount);
         rv = view.findViewById(R.id.rc_cart);
         rv.setHasFixedSize(true);
         rv.setLayoutManager(new LinearLayoutManager(getContext()));


         LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMasage,new IntentFilter("mymsg"));
     }
    private void getCartData() {

            carts = ViewsSliderActivity.myDatabase.cartDao().getData();
            cartProductAdapter = new CartProductAdapter(carts, getContext());

            rv.setAdapter(cartProductAdapter);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updatacartcount();
    }

    public BroadcastReceiver mMasage=new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            String cartcount=intent.getStringExtra("cartcount");
            if (carts.size()  ==0){
                Toast.makeText(getContext(),"Cart is emty",Toast.LENGTH_SHORT).show();
            }
        }
    };
    private void updatacartcount() {
        int count=cartProductAdapter.getItemCount();

        if (count==0){
            tvcount.setText("Your Cart is Empty");
        }else {
            tvcount.setText(String.valueOf(count));
        }

    }




    @Override
    public void onResume() {
        super.onResume();
        updatacartcount();
    }



}
