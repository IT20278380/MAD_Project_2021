package com.example.mad_project_2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.mad_project_2021.tableModules.Computer;
import com.example.mad_project_2021.tableModules.Items;
import com.example.mad_project_2021.tableModules.Itemslist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Deliver_Items extends AppCompatActivity {

    public static String province;
    RecyclerView recyclerView78;
    DatabaseReference data;
    Itemslist itemslist;
    ArrayList<Items> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_items);

        Intent intent = getIntent();
        province = intent.getStringExtra("province");

        recyclerView78 = findViewById(R.id.order_list);
        data = FirebaseDatabase.getInstance().getReference("Order_Items").child(province);
        recyclerView78.setHasFixedSize(true);
        recyclerView78.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        itemslist = new Itemslist(this,list);
        recyclerView78.setAdapter(itemslist);

        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Items items = dataSnapshot.getValue(Items.class);
                    list.add(items);


                }
                itemslist.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}