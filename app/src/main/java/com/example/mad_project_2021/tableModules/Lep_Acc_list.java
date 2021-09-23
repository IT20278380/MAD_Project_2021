package com.example.mad_project_2021.tableModules;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mad_project_2021.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

public class Lep_Acc_list extends RecyclerView.Adapter<Lep_Acc_list.LAViewHolder> {

    Context con;

    ArrayList<Lep_Acc> list;

    public Lep_Acc_list(Context con, ArrayList<Lep_Acc> list){
        this.con = con;
        this.list = list;
    }

    @NonNull
    @Override
    public LAViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(con).inflate(R.layout.lap_acc_list,parent,false);
        return new LAViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LAViewHolder holder, int position) {
        Lep_Acc lep_acc = list.get(position);
        holder.name.setText(lep_acc.getAcc_Lname());
        holder.price.setText(lep_acc.getAcc_Lprice());
        holder.description.setText(lep_acc.getAcc_Ldescription());
        Glide.with(con).load(list.get(position).getAcc_Limg()).into(holder.image);

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.image.getContext())
                        .setContentHolder(new ViewHolder(R.layout.lep_ass_update))
                        .setExpanded(true,800)
                        .create();

                View view = dialogPlus.getHolderView();

                EditText price = view.findViewById(R.id.comas_las_up);
                Button upprice = view.findViewById(R.id.comas_las_upbut);

                price.setText(lep_acc.getAcc_Lprice());

                dialogPlus.show();

                upprice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseDatabase update = FirebaseDatabase.getInstance();
                        DatabaseReference myref = update.getReference();
                        myref.child("Laptop Accessories").child(lep_acc.getAcc_Lname()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                snapshot.getRef().child("acc_Lprice").setValue(price.getText().toString());
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

                FirebaseDatabase.getInstance().getReference().child("Laptop Accessories")
                        .child(lep_acc.getAcc_Lname()).setValue(null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class LAViewHolder extends RecyclerView.ViewHolder{
        TextView name, price, description;
        ImageView image;
        Button update,delete;

        public LAViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.b_las_name);
            price = itemView.findViewById(R.id.b_las_price);
            description = itemView.findViewById(R.id.b_las_des);

            image = itemView.findViewById(R.id.las_image);

            update = (Button) itemView.findViewById(R.id.b_las_up);
            delete = (Button) itemView.findViewById(R.id.b_las_del);
        }
    }
}
