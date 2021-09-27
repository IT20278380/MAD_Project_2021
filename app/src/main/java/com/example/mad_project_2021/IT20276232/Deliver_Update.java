package com.example.mad_project_2021.IT20276232;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mad_project_2021.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Deliver_Update extends AppCompatActivity {

    EditText mobile, email1, password1;
    TextView userName1, province1;
    Button button;

    DatabaseReference Dupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_update);

        Intent intent = getIntent();

        String userName = intent.getStringExtra("userName");
        String email = intent.getStringExtra("email");
        String mobileNumber = intent.getStringExtra("mobileNumber");
        String password = intent.getStringExtra("password");
        String province = intent.getStringExtra("province");

        String Ddelete = userName;

        userName1 = (TextView) findViewById(R.id.D_Up_name);
        email1 = (EditText) findViewById(R.id.D_Up_email);
        mobile = (EditText) findViewById(R.id.D_Up_num);
        province1 = (TextView) findViewById(R.id.D_up_pro);
        password1 = (EditText) findViewById(R.id.D_up_pass);

        button = (Button) findViewById(R.id.D_up_but00);

        userName1.setText(userName);
        email1.setText(email);
        mobile.setText(mobileNumber);
        province1.setText(province);
        password1.setText(password);

        //update deliver
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase update = FirebaseDatabase.getInstance();
                DatabaseReference myref = update.getReference();
                myref.child("Delivers").child(userName1.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child("duserName").setValue(userName1.getText().toString());
                        snapshot.getRef().child("demail").setValue(email1.getText().toString());
                        snapshot.getRef().child("dmobileNumber").setValue(mobile.getText().toString());
                        snapshot.getRef().child("dpassword").setValue(password1.getText().toString());
                        snapshot.getRef().child("dprovince").setValue(province1.getText().toString());

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