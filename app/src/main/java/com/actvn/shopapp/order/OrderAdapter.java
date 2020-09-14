package com.actvn.shopapp.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.actvn.shopapp.R;
import com.actvn.shopapp.api.model.Data;
import com.actvn.shopapp.api.model.Detail;
import com.actvn.shopapp.api.model.Order;
import com.bumptech.glide.Glide;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    public static final String BASE_URL = "http://app.baomoiday.net/public/";

    private Context context;
    private List<Detail> details;
    private List<Data> datas;

    public OrderAdapter(Context context, List<Detail> details, List<Data> datas){
        this.context = context;
        this.details = details;
        this.datas = datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Detail detail = details.get(position);
        Data data = datas.get(position);

        holder.txtNameOrder.setText(String.valueOf(data.getDescriptions().get(1).getName()));
        holder.txtCostOrder.setText(String.valueOf(detail.getQty()) + " Sản Phẩm | " + String.valueOf(detail.getPrice()) + "đ");

        Glide.with(context).load(BASE_URL + data.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgOrder);
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgOrder;
        TextView txtNameOrder;
        TextView txtCostOrder;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgOrder = itemView.findViewById(R.id.imgOrder);
            txtNameOrder = itemView.findViewById(R.id.txtNameOrder);
            txtCostOrder = itemView.findViewById(R.id.txtCostOrder);
        }
    }
}
