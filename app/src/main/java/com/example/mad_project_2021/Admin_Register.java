package com.example.mad_project_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_project_2021.tableModules.Admin;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_Register extends AppCompatActivity {

    EditText userName, email, mobile, password;
    Button register;

    DatabaseReference Adminre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);

        userName = (EditText) findViewById(R.id.AR_enName);
        email = (EditText) findViewById(R.id.AR_enMail);
        mobile = (EditText) findViewById(R.id.AR_Enum);
        password = (EditText) findViewById(R.id.AR_enpass);

        register = (Button) findViewById(R.id.AR_but);

        Adminre = FirebaseDatabase.getInstance().getReference().child("Admin");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String AuserName = userName.getText().toString();
                String Aemail = email.getText().toString();
                String AmobileNumber = mobile.getText().toString();
                String Apassword = password.getText().toString();

                Admin deliver = new Admin(AuserName, Aemail, AmobileNumber, Apassword);

                if(AuserName.isEmpty() || Aemail.isEmpty() || AmobileNumber.isEmpty() || Apassword.isEmpty()){
                    Toast.makeText(Admin_Register.this, "Fill All Details", Toast.LENGTH_SHORT).show();

                }else {
                    Adminre.child(userName.getText().toString()).setValue(deliver);
                    Toast.makeText(Admin_Register.this, "Admin Registered", Toast.LENGTH_SHORT).show();

                    navigateLogin();
                }

            }
        });
    }
    public void navigateLogin(){
        Intent intent = new Intent(this, Admin_Loin.class);
        startActivity(intent);
    }
}