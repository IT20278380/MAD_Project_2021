package com.example.mad_project_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_project_2021.tableModules.Com_Accessories;
import com.example.mad_project_2021.tableModules.Lep_Accessories;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_Lap_Acc_Add extends AppCompatActivity {

    EditText name, price, comName, description, img;
    Button add, image;

    DatabaseReference addLapAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_lap_acc_add);

        Intent intent = getIntent();

        String uri = intent.getStringExtra("im");

        name = (EditText) findViewById(R.id.add_las_name);
        price = (EditText) findViewById(R.id.add_las_price);
        comName = (EditText) findViewById(R.id.add_las_comp);
        description = (EditText) findViewById(R.id.add_las_des);
        img = (EditText) findViewById(R.id.add_las_img);
        img.setText(uri);

        add = (Button) findViewById(R.id.add_lasbutt);
        image = (Button) findViewById(R.id.add_lapas_img);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Admin_Accessories_Lap_Image.class);
                startActivity(i);

            }
        });

        addLapAcc = FirebaseDatabase.getInstance().getReference().child("Laptop Accessories");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Acc_Lname = name.getText().toString();
                String Acc_Lprice = price.getText().toString();
                String Acc_LcomName = comName.getText().toString();
                String Acc_Ldescription = description.getText().toString();
                String Acc_Limg = img.getText().toString();

                Lep_Accessories lep_accessories = new Lep_Accessories(Acc_Lname, Acc_Lprice, Acc_LcomName, Acc_Ldescription, Acc_Limg);

                if (Acc_Lname.isEmpty() || Acc_Lprice.isEmpty() || Acc_LcomName.isEmpty() || Acc_Ldescription.isEmpty() || Acc_Limg.isEmpty()) {
                    Toast.makeText(Admin_Lap_Acc_Add.this, "Fill All Details", Toast.LENGTH_SHORT).show();

                } else {
                    addLapAcc.child(name.getText().toString()).setValue(lep_accessories);
                    Toast.makeText(Admin_Lap_Acc_Add.this, "Accessories Added", Toast.LENGTH_SHORT).show();

                    navigateLogin();
                }

            }
        });
    }

    public void navigateLogin() {
        Intent intent = new Intent(this, Admin_Laptop_Acessories.class);
        startActivity(intent);

    }
}