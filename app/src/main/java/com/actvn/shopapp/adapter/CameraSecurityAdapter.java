package com.actvn.shopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.actvn.shopapp.R;
import com.actvn.shopapp.api.model.ProductsJsoup;
import com.actvn.shopapp.views.ProptiesActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CameraSecurityAdapter extends RecyclerView.Adapter<CameraSecurityAdapter.ViewHolder> {
    private ArrayList<ProductsJsoup> cameraSecuritys;
    private Context context;

    public CameraSecurityAdapter(ArrayList<ProductsJsoup> cameraSecurityAdapters, Context context) {
        this.cameraSecuritys = cameraSecurityAdapters;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ProductsJsoup cameraSecurity = cameraSecuritys.get(position);
        holder.txtTitle.setText(cameraSecurity.getTitle());
        holder.txtCost.setText((cameraSecurity.getCost()));

        Glide.with(context).load(cameraSecurity.getImgUrl())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgItem);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(holder.itemView.getContext(), ProptiesActivity.class);
                intent.putExtra("imgs", cameraSecurity.getImgUrl());
                intent.putExtra("money", cameraSecurity.getCost() + "đ");
                intent.putExtra("name", cameraSecurity.getTitle());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return cameraSecuritys.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgItem;
        TextView txtTitle;
        TextView txtCost;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardViewProduct);
            imgItem = itemView.findViewById(R.id.img);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtCost = itemView.findViewById(R.id.txtCost);
        }
    }
}
