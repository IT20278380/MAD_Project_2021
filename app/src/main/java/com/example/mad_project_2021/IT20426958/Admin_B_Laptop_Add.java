package com.example.mad_project_2021.IT20426958;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_project_2021.R;
import com.example.mad_project_2021.tableModules.IT20426958.Brand_New_Laptop;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_B_Laptop_Add extends AppCompatActivity {

    EditText name, price, genarate, description, image;
    Button add ,img;

    DatabaseReference addCom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_blaptop_add);

        Intent intent = getIntent();

        String uri = intent.getStringExtra("uri");

        name = (EditText) findViewById(R.id.add_l_name);
        price = (EditText) findViewById(R.id.add_l_price);
        genarate = (EditText) findViewById(R.id.add_l_gene);
        description = (EditText) findViewById(R.id.add_l_des);
        image = (EditText) findViewById(R.id.add_l_img);

        add = (Button) findViewById(R.id.add_blbutt);
        img = (Button) findViewById(R.id.add_lap_img);

        image.setText(uri);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Admin_New_Laptop_Image.class);
                startActivity(i);
            }
        });

        addCom = FirebaseDatabase.getInstance().getReference().child("Brand_New_Laptops");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String New_Lname = name.getText().toString();
                String New_Lprice = price.getText().toString();
                String New_Lgenarate = genarate.getText().toString();
                String New_Ldescription = description.getText().toString();
                String New_Limage = image.getText().toString();

                Brand_New_Laptop brand_new_laptop = new Brand_New_Laptop(New_Lname, New_Lprice, New_Lgenarate, New_Ldescription, New_Limage);

                if(New_Lname.isEmpty() || New_Lprice.isEmpty() || New_Lgenarate.isEmpty() || New_Ldescription.isEmpty() || New_Limage.isEmpty()){
                    Toast.makeText(Admin_B_Laptop_Add.this, "Fill All Details", Toast.LENGTH_SHORT).show();

                }else {
                    addCom.child(name.getText().toString()).setValue(brand_new_laptop);
                    Toast.makeText(Admin_B_Laptop_Add.this, "Laptop Added", Toast.LENGTH_SHORT).show();

                    navigateLogin();
                }

            }
        });
    }
    public void navigateLogin(){
        Intent intent = new Intent(this, Admin_Brand__New_Leptop.class);
        startActivity(intent);
    }
}