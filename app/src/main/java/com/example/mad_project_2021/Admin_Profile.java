package com.example.mad_project_2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Admin_Profile extends AppCompatActivity {

    Button update, delete, computer, accessories;
    TextView name, email, mobile;
    String pass;

    DatabaseReference readDB;
    DatabaseReference Adelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        Intent intent = getIntent();

        String userName = intent.getStringExtra("userName");

        update = (Button) findViewById(R.id.ADP_but_1);
        delete = (Button) findViewById(R.id.ADP_but_2);
        computer = (Button) findViewById(R.id.ADP_but_3);
        accessories = (Button) findViewById(R.id.ADP_but_4);

        name = (TextView) findViewById(R.id.ADP_name);
        email = (TextView) findViewById(R.id.ADP_enmail);
        mobile = (TextView) findViewById(R.id.ADP_n1);

        //read data
        readDB = FirebaseDatabase.getInstance().getReference().child("Admin").child(userName);
        readDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                String AuserName = snapshot.child("auserName").getValue().toString();
                String Aemail = snapshot.child("aemail").getValue().toString();
                String AmobileNumber = snapshot.child("amobileNumber").getValue().toString();
                String Apassword = snapshot.child("apassword").getValue().toString();
                name.setText(AuserName);
                email.setText(Aemail);
                mobile.setText(AmobileNumber);
                pass=Apassword;

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        //update Deliver
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Admin_Update.class);
                i.putExtra("userName", name.getText().toString());
                i.putExtra("email", email.getText().toString());
                i.putExtra("number", mobile.getText().toString());
                i.putExtra("password", pass);
                startActivity(i);
            }
        });

       //delete Deliver
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admindelete();
                Intent i = new Intent(getApplicationContext(), Admin_Loin.class);
                startActivity(i);
              }
        });

        computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Admin_Brand_New.class);
                startActivity(i);

            }
        });

    }

    public void Admindelete(){
        Adelete = FirebaseDatabase.getInstance().getReference();
        Adelete.child("Admin").child(name.getText().toString()).setValue(null);

        Toast.makeText(Admin_Profile.this, "Delete Admin", Toast.LENGTH_SHORT).show();

    }
}
