package com.example.mad_project_2021.tableModules;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mad_project_2021.Deliver_Items;
import com.example.mad_project_2021.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Itemslist extends RecyclerView.Adapter<Itemslist.ItemHolder>{

    Context context;

    ArrayList<Items> list;

    Deliver_Items deliver_items = new Deliver_Items();

    public Itemslist(Context context, ArrayList<Items> list){
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
        return  new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Items items = list.get(position);
        holder.adress.setText(items.getOaddres());
        holder.number.setText(items.getOnumber());
        holder.price.setText(items.getOiprice());
        holder.province.setText(items.getOprovince());

        holder.conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Order_Items").child(deliver_items.province)
                        .child(items.getOnumber()).setValue(null);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {

        TextView adress, number, price, province;
        Button conf;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            adress = itemView.findViewById(R.id.item_add);
            number = itemView.findViewById(R.id.item_num);
            price = itemView.findViewById(R.id.tem_price);
            province = itemView.findViewById(R.id.item_pro);
            conf = (Button) itemView.findViewById(R.id.item_but12);
        }
    }
}
