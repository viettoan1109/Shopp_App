package com.actvn.shopapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.actvn.shopapp.R;
import com.actvn.shopapp.api.model.Data;
import com.actvn.shopapp.views.ProptiesActivity;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {
    public static final String PRODUCT_URL = "http://app.baomoiday.net/public/";
    private List<Data> datas;
    private Context context;

    public StoreAdapter(List<Data> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Data data = datas.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtName.setText(String.valueOf(data.getDescriptions().get(1).getName()));
        holder.txtCost.setText(String.valueOf(decimalFormat.format(data.getCost())+ "₫"));
        Glide.with(context).load((PRODUCT_URL + data.getImage()))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgItem);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(holder.itemView.getContext(), ProptiesActivity.class);
                intent.putExtra("imgs",PRODUCT_URL + data.getImage());
                intent.putExtra("money",data.getCost()+"đ");
                intent.putExtra("name",data.getDescriptions().get(1).getName());
                intent.putExtra("id",data.getId());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgItem;
        TextView txtName;
        TextView txtCost;
        Button btnView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            imgItem = itemView.findViewById(R.id.imgItem);
            txtName = itemView.findViewById(R.id.txtName);
            txtCost = itemView.findViewById(R.id.txtCost);
            btnView = itemView.findViewById(R.id.btnView);
        }
    }

}
