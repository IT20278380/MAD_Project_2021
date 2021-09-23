package com.example.mad_project_2021.tableModules;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mad_project_2021.Admin_Brand_New;
import com.example.mad_project_2021.Admin_Brand_New_Computer;
import com.example.mad_project_2021.Admin_Loin;
import com.example.mad_project_2021.Admin_Profile;
import com.example.mad_project_2021.Admin_Update;
import com.example.mad_project_2021.Deliver_Login;
import com.example.mad_project_2021.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

public class Computer_list extends RecyclerView.Adapter<Computer_list.MyViewHolder> {

    Context context;

    ArrayList<Computer> list;


    public Computer_list(Context context, ArrayList<Computer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.computer_list,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Computer computer = list.get(position);
        holder.name.setText(computer.getNew_Cname());
        holder.price.setText(computer.getNew_Cprice());
        holder.description.setText(computer.getNew_Cdescription());
        Glide.with(context).load(list.get(position).getNew_Cimage()).into(holder.image);

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.image.getContext())
                        .setContentHolder(new ViewHolder(R.layout.computer_update))
                        .setExpanded(true,800)
                        .create();

                View view = dialogPlus.getHolderView();

                EditText price = view.findViewById(R.id.com_b_up);
                Button upprice = view.findViewById(R.id.com_b_upbut);

                price.setText(computer.getNew_Cprice());

                dialogPlus.show();

                upprice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseDatabase update = FirebaseDatabase.getInstance();
                        DatabaseReference myref = update.getReference();
                        myref.child("Brand_New_Computers").child(computer.getNew_Cname()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                snapshot.getRef().child("new_Cprice").setValue(price.getText().toString());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }

                });
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference().child("Brand_New_Computers")
                        .child(computer.getNew_Cname()).setValue(null);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        View.OnClickListener OnClickListener;
        TextView name, price, description;
        ImageView image;
        Button update,delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.b_c_name);
            price = itemView.findViewById(R.id.b_c_price);
            description = itemView.findViewById(R.id.b_c_des);

            image = itemView.findViewById(R.id.c_image);

            update = (Button) itemView.findViewById(R.id.b_c_up);
            delete = (Button) itemView.findViewById(R.id.b_c_del);

        }

    }
}