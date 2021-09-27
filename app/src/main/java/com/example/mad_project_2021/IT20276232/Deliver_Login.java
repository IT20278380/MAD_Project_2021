package com.example.mad_project_2021.IT20276232;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project_2021.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import static android.text.TextUtils.isEmpty;

public class Deliver_Login extends AppCompatActivity {

    TextView textView;

    EditText userName, password;
    Button login;

    DatabaseReference readDB;
    DatabaseReference pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_login);


        textView = (TextView) findViewById(R.id.D_log_link);

        userName = (EditText) findViewById(R.id.D_log_En_name);
        password = (EditText) findViewById(R.id.D_log_Epass);

        login = (Button) findViewById(R.id.D_but);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
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

                    readDB = FirebaseDatabase.getInstance().getReference().child("Delivers").child(userName.getText().toString());
                    readDB.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            if(!snapshot.exists()){
                                Toast.makeText(Deliver_Login.this, "Not Deliver", Toast.LENGTH_SHORT).show();
                            }else {
                                pass = FirebaseDatabase.getInstance().getReference().child("Delivers").child(userName.getText().toString());
                                pass.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot0) {

                                        String passw = snapshot0.child("dpassword").getValue().toString();

                                        if(passw.equals(password.getText().toString())){
                                            Toast.makeText(Deliver_Login.this, "Deliver Found", Toast.LENGTH_SHORT).show();
                                            navigateLogin();
                                        }else {
                                            Toast.makeText(Deliver_Login.this, "Password Is Incorrect", Toast.LENGTH_SHORT).show();
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
        Intent i = new Intent(getApplicationContext(), Deliver_Profile.class);
        i.putExtra("name", userName.getText().toString());
        startActivity(i);
    }


    public void openNewActivity(){
        Intent intent = new Intent(this, Deliver_Register.class);
        startActivity(intent);
    }
}