package com.example.mad_project_2021.IT20426958;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mad_project_2021.R;
import com.example.mad_project_2021.tableModules.IT20426958.Laptop;
import com.example.mad_project_2021.tableModules.IT20426958.Laptop_list;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin_Brand__New_Leptop extends AppCompatActivity {

    // creating a variable for
    // our Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our
    // Database Reference for Firebase.
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    DatabaseReference database;
    Laptop_list laptop_list;
    ArrayList<Laptop> list;

    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_brand_new_leptop);

        add = (Button) findViewById(R.id.add_b_lap);

        recyclerView = findViewById(R.id.lap_list);
        database = FirebaseDatabase.getInstance().getReference("Brand_New_Laptops");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        laptop_list = new Laptop_list(this,list);
        recyclerView.setAdapter(laptop_list);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Laptop laptop = dataSnapshot.getValue(Laptop.class);
                    list.add(laptop);
                }
                laptop_list.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Admin_B_Laptop_Add.class);
                startActivity(i);
            }
        });
    }
}