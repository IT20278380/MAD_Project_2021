package com.example.mad_project_2021;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class Deliver_Profile extends AppCompatActivity {

    TextView userName, email, mobileNumber, password, province;
    Button update, delete, items;

    DatabaseReference readDB;
    DatabaseReference Ddelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_profile);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");

        userName = (TextView) findViewById(R.id.D_pro0_name2);
        email = (TextView) findViewById(R.id.D_pro0_email2);
        mobileNumber = (TextView) findViewById(R.id.D_pro0_phone2);
        password = (TextView) findViewById(R.id.D_pro0_pass2);
        province = (TextView) findViewById(R.id.D_pro0_province2);

        delete = (Button) findViewById(R.id.D_pro0_but2);
        update = (Button) findViewById(R.id.D_pro0_but3);
        items = (Button) findViewById(R.id.D_pro0_but);

        //read data
        readDB = FirebaseDatabase.getInstance().getReference().child("Delivers").child(name);
        readDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                String DuserName = snapshot.child("duserName").getValue().toString();
                String Demail = snapshot.child("demail").getValue().toString();
                String DmobileNumber = snapshot.child("dmobileNumber").getValue().toString();
                String Dpassword = snapshot.child("dpassword").getValue().toString();
                String Dprovince = snapshot.child("dprovince").getValue().toString();
                userName.setText(DuserName);
                email.setText(Demail);
                mobileNumber.setText(DmobileNumber);
                password.setText(Dpassword);
                province.setText(Dprovince);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


        //update Deliver
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Deliver_Update.class);
                i.putExtra("userName", userName.getText().toString());
                i.putExtra("email", email.getText().toString());
                i.putExtra("mobileNumber", mobileNumber.getText().toString());
                i.putExtra("password", password.getText().toString());
                i.putExtra("province", province.getText().toString());
                startActivity(i);
            }
        });

        //delete Deliver
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ddelete = FirebaseDatabase.getInstance().getReference();
                Ddelete.child("Delivers").child(userName.getText().toString()).setValue(null);

                Toast.makeText(Deliver_Profile.this, "Delete Deliver", Toast.LENGTH_SHORT).show();

            }
        });

        items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Deliver_Items.class);
                i.putExtra("province", province.getText().toString());
                startActivity(i);
            }
        });

    }
}