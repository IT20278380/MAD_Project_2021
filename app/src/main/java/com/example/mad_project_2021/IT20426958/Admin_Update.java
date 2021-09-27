package com.example.mad_project_2021.IT20426958;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mad_project_2021.IT20276232.Deliver_Login;
import com.example.mad_project_2021.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin_Update extends AppCompatActivity {

    Button updpate;
    EditText email0, number0, password0;
    TextView userName0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update);

        Intent intent = getIntent();

        String userName = intent.getStringExtra("userName");
        String email = intent.getStringExtra("email");
        String number = intent.getStringExtra("number");
        String password = intent.getStringExtra("password");

        userName0 = (TextView) findViewById(R.id.A_Up_name);
        email0 = (EditText) findViewById(R.id.A_Up_email);
        number0 = (EditText) findViewById(R.id.A_Up_num);
        password0 = (EditText) findViewById(R.id.A_up_pass);
        updpate = (Button) findViewById(R.id.A_up_but00);

        userName0.setText(userName);
        email0.setText(email);
        number0.setText(number);
        password0.setText(password);

        //update deliver
        updpate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase update = FirebaseDatabase.getInstance();
                DatabaseReference myref = update.getReference();
                myref.child("Admin").child(userName0.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child("auserName").setValue(userName0.getText().toString());
                        snapshot.getRef().child("aemail").setValue(email0.getText().toString());
                        snapshot.getRef().child("amobileNumber").setValue(number0.getText().toString());
                        snapshot.getRef().child("apassword").setValue(password0.getText().toString());

                        Intent i = new Intent(getApplicationContext(), Deliver_Login.class);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


    }
}