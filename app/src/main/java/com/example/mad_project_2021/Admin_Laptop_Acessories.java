package com.example.mad_project_2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mad_project_2021.tableModules.Com_Acc;
import com.example.mad_project_2021.tableModules.Com_Acc_list;
import com.example.mad_project_2021.tableModules.Lep_Acc;
import com.example.mad_project_2021.tableModules.Lep_Acc_list;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin_Laptop_Acessories extends AppCompatActivity {

    Button add;

    // creating a variable for
    // our Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our
    // Database Reference for Firebase.
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    DatabaseReference databace;
    Lep_Acc_list lep_acc_list;
    ArrayList<Lep_Acc> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_laptop_acessories);

        add = (Button)findViewById(R.id.add_as_lap);

        recyclerView = findViewById(R.id.aslap_list);
        databace = FirebaseDatabase.getInstance().getReference("Laptop Accessories");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        lep_acc_list = new Lep_Acc_list(this,list);
        recyclerView.setAdapter(lep_acc_list);

        databace.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Lep_Acc lep_acc = dataSnapshot.getValue(Lep_Acc.class);
                    list.add(lep_acc);
                }
                lep_acc_list.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Admin_Lap_Acc_Add.class);
                startActivity(i);
            }
        });
    }
}