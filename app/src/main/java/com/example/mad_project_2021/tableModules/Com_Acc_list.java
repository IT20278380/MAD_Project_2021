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

public class Com_Acc_list extends RecyclerView.Adapter<Com_Acc_list.CAViewHolder>{

    Context con;

    ArrayList<Com_Acc> list;

    public Com_Acc_list(Context con, ArrayList<Com_Acc> list){
        this.con = con;
        this.list = list;
    }

    @NonNull
    @Override
    public CAViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(con).inflate(R.layout.com_acc_list,parent,false);
        return new CAViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CAViewHolder holder, int position) {
        Com_Acc com_acc = list.get(position);
        holder.name.setText(com_acc.getAcc_Cname());
        holder.price.setText(com_acc.getAcc_Cprice());
        holder.description.setText(com_acc.getAcc_Cdescription());
        Glide.with(con).load(list.get(position).getAcc_Cimg()).into(holder.image);

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.image.getContext())
                        .setContentHolder(new ViewHolder(R.layout.com_ass_update))
                        .setExpanded(true,800)
                        .create();

                View view = dialogPlus.getHolderView();

                EditText price = view.findViewById(R.id.comas_b_up);
                Button upprice = view.findViewById(R.id.comas_b_upbut);

                price.setText(com_acc.getAcc_Cprice());

                dialogPlus.show();

                upprice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseDatabase update = FirebaseDatabase.getInstance();
                        DatabaseReference myref = update.getReference();
                        myref.child("Computers Accessories").child(com_acc.getAcc_Cname()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                snapshot.getRef().child("acc_Cprice").setValue(price.getText().toString());
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

                FirebaseDatabase.getInstance().getReference().child("Computers Accessories")
                        .child(com_acc.getAcc_Cname()).setValue(null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CAViewHolder extends RecyclerView.ViewHolder {

        TextView name, price, description;
        ImageView image;
        Button update,delete;

        public CAViewHolder(@NonNull View itemView0) {
            super(itemView0);

            name = itemView.findViewById(R.id.b_cas_name);
            price = itemView.findViewById(R.id.b_cas_price);
            description = itemView.findViewById(R.id.b_cas_des);

            image = itemView.findViewById(R.id.cas_image);

            update = (Button) itemView.findViewById(R.id.b_cas_up);
            delete = (Button) itemView.findViewById(R.id.b_cas_del);
        }
    }

}