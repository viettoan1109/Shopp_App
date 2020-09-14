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
import com.actvn.shopapp.api.model.Phone;
import com.actvn.shopapp.views.ProptiesActivity;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ViewHolder> {

    private ArrayList<Phone> phoneList;
    private Context context;

    public PhoneAdapter(ArrayList<Phone> phoneList, Context context) {
        this.phoneList = phoneList;
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
        final Phone phone = phoneList.get(position);
        holder.txtTitle.setText(phone.getTitle());
        holder.txtCost.setText((phone.getCost()));

        Glide.with(context).load(phone.getImgUrl())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgItem);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(holder.itemView.getContext(), ProptiesActivity.class);
                intent.putExtra("imgs", phone.getImgUrl());
                intent.putExtra("money", phone.getCost() + "đ");
                intent.putExtra("name", phone.getTitle());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return phoneList.size();
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
