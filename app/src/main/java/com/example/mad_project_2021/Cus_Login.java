package com.example.mad_project_2021;

import static android.text.TextUtils.isEmpty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Cus_Login extends AppCompatActivity {

    TextView textView;
    Button login;

    EditText userName, password;

    public static String name12;

    DatabaseReference readDB;
    DatabaseReference pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_login);

        textView = (TextView) findViewById(R.id.CusLog_link);
        userName = (EditText)findViewById(R.id.cusLog_inname);
        password = (EditText)findViewById(R.id.CusLog_inPass);

        login = (Button) findViewById(R.id.cus_but);

        //login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name12 = userName.getText().toString();
                if (isEmpty(userName.getText().toString())){
                    userName.setError("User Name Cannot Be Empty");
                    userName.requestFocus();
                }else if (isEmpty(password.getText().toString())){
                    password.setError("Password Cannot Be Empty");
                    password.requestFocus();
                }else{

                    readDB = FirebaseDatabase.getInstance().getReference().child("Customers").child(userName.getText().toString());
                    readDB.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            if(!snapshot.exists()){
                                Toast.makeText(Cus_Login.this, "Not Customer", Toast.LENGTH_SHORT).show();
                            }else {
                                pass = FirebaseDatabase.getInstance().getReference().child("Customers").child(userName.getText().toString());
                                pass.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot0) {

                                        String passw = snapshot0.child("cpassword").getValue().toString();

                                        if(passw.equals(password.getText().toString())){
                                            Toast.makeText(Cus_Login.this, "Customer Found", Toast.LENGTH_SHORT).show();

                                            navigateLogin();
                                        }else {
                                            Toast.makeText(Cus_Login.this, "Password Is Incorrect", Toast.LENGTH_SHORT).show();
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

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });
    }
    public void navigateLogin(){
        Intent i = new Intent(getApplicationContext(), Customer_Area.class);
        startActivity(i);
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, Cus_Register.class);
        startActivity(intent);
    }

}
