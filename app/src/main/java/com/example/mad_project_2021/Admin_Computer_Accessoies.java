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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin_Computer_Accessoies extends AppCompatActivity {

    Button add;

    // creating a variable for
    // our Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our
    // Database Reference for Firebase.
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    DatabaseReference databace;
    Com_Acc_list com_acc_list;
    ArrayList<Com_Acc> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_computer_accessoies);

        add = (Button) findViewById(R.id.add_as_com);

        recyclerView = findViewById(R.id.ascom_list);
        databace = FirebaseDatabase.getInstance().getReference("Computers Accessories");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        com_acc_list = new Com_Acc_list(this,list);
        recyclerView.setAdapter(com_acc_list);

        databace.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Com_Acc com_acc = dataSnapshot.getValue(Com_Acc.class);
                    list.add(com_acc);
                }
                com_acc_list.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Admin_Com_Acc_Add.class);
                startActivity(i);
            }
        });
    }
}