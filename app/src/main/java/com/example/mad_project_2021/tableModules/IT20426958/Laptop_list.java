package com.example.mad_project_2021.tableModules.IT20426958;

import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
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

public class Laptop_list extends RecyclerView.Adapter<Laptop_list.LpViewHolder> {

    Context con;

    ArrayList<Laptop> lst;

    public Laptop_list(Context con, ArrayList<Laptop> lst) {
        this.con = con;
        this.lst = lst;
    }

    @NonNull
    @Override
    public LpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(con).inflate(R.layout.laptop_list,parent, false);
        return new LpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LpViewHolder hold, int position) {
        Laptop laptop = lst.get(position);
        hold.name.setText(laptop.getNew_Lname());
        hold.price.setText(laptop.getNew_Lprice());
        hold.description.setText(laptop.getNew_Ldescription());
        Glide.with(con).load(lst.get(position).getNew_Limage()).into(hold.image);

        hold.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(hold.image.getContext())
                        .setContentHolder(new ViewHolder(R.layout.laptop_update))
                        .setExpanded(true,800)
                        .create();

                View view = dialogPlus.getHolderView();

                EditText price = view.findViewById(R.id.lap_b_up);
                Button upprice = view.findViewById(R.id.lap_b_upbut);

                price.setText(laptop.getNew_Lprice());

                dialogPlus.show();

                upprice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseDatabase update = FirebaseDatabase.getInstance();
                        DatabaseReference myref = update.getReference();
                        myref.child("Brand_New_Laptops").child(laptop.getNew_Lname()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                snapshot.getRef().child("new_Lprice").setValue(price.getText().toString());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }

                });

            }
        });

        hold.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference().child("Brand_New_Laptops")
                        .child(laptop.getNew_Lname()).setValue(null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    public static class LpViewHolder extends RecyclerView.ViewHolder {

        TextView name, price, description;
        ImageView image;
        Button update,delete;

        public LpViewHolder(@NonNull View itemView0) {
            super(itemView0);

            name = itemView.findViewById(R.id.b_l_name);
            price = itemView.findViewById(R.id.b_l_price);
            description = itemView.findViewById(R.id.b_l_des);

            image = itemView.findViewById(R.id.l_image);

            update = (Button) itemView.findViewById(R.id.b_l_up);
            delete = (Button) itemView.findViewById(R.id.b_l_del);
        }
    }
}
