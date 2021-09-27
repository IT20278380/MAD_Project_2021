package com.example.mad_project_2021.IT20479428;

import static android.text.TextUtils.isEmpty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project_2021.IT20426958.Admin_Profile;
import com.example.mad_project_2021.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Admin_Loin extends AppCompatActivity {

    TextView textView;
    EditText userName, password;
    Button login;

    DatabaseReference readDB;
    DatabaseReference pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_loin);

        textView = (TextView) findViewById(R.id.A_go_link);
        userName = (EditText) findViewById(R.id.A_log_En_name);
        password = (EditText) findViewById(R.id.A_log_Epass);
        login = (Button) findViewById(R.id.A_but);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Admin_Register.class);
                startActivity(i);
            }
        });

        //login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(userName.getText().toString())){
                    userName.setError("User Name Cannot Be Empty");
                    userName.requestFocus();
                }else if (isEmpty(password.getText().toString())){
                    password.setError("Password Cannot Be Empty");
                    password.requestFocus();
                }else{

                    readDB = FirebaseDatabase.getInstance().getReference().child("Admin").child(userName.getText().toString());
                    readDB.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            if(!snapshot.exists()){
                                Toast.makeText(Admin_Loin.this, "Not Admin", Toast.LENGTH_SHORT).show();
                            }else {
                                pass = FirebaseDatabase.getInstance().getReference().child("Admin").child(userName.getText().toString());
                                pass.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot0) {

                                        String passw = snapshot0.child("apassword").getValue().toString();

                                        if(passw.equals(password.getText().toString())){
                                            Toast.makeText(Admin_Loin.this, "Deliver Found", Toast.LENGTH_SHORT).show();
                                            navigateLogin();
                                        }else {
                                            Toast.makeText(Admin_Loin.this, "Password Is Incorrect", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });
                }

            }
        });

    }
    public void navigateLogin(){
        Intent i = new Intent(getApplicationContext(), Admin_Profile.class);
        i.putExtra("userName", userName.getText().toString());
        startActivity(i);
    }
}