package com.example.mad_project_2021.IT20426958;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_project_2021.R;
import com.example.mad_project_2021.tableModules.IT20426958.Brand_New_Computer;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_B_Computer_Add extends AppCompatActivity {

    EditText name, price, genarate, description, image;
    Button add ,img;

    DatabaseReference addCom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_bcomputer_add);

        Intent intent = getIntent();

        String uri = intent.getStringExtra("uri");

        name = (EditText) findViewById(R.id.add_c_name);
        price = (EditText) findViewById(R.id.add_c_price);
        genarate = (EditText) findViewById(R.id.add_c_gene);
        description = (EditText) findViewById(R.id.add_c_des);

        img = (Button) findViewById(R.id.add_com_img);
        add = (Button) findViewById(R.id.add_bcbutt);


        image = (EditText) findViewById(R.id.add_c_img);

        image.setText(uri);

        addCom = FirebaseDatabase.getInstance().getReference().child("Brand_New_Computers");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String New_Cname = name.getText().toString();
                String New_Cprice = price.getText().toString();
                String New_Cgenarate = genarate.getText().toString();
                String New_Cdescription = description.getText().toString();
                String New_Cimage = image.getText().toString();

                Brand_New_Computer brand_new_computer = new Brand_New_Computer(New_Cname, New_Cprice, New_Cgenarate, New_Cdescription, New_Cimage);

                if(New_Cname.isEmpty() || New_Cprice.isEmpty() || New_Cgenarate.isEmpty() || New_Cdescription.isEmpty() || New_Cimage.isEmpty()){
                    Toast.makeText(Admin_B_Computer_Add.this, "Fill All Details", Toast.LENGTH_SHORT).show();

                }else {
                    addCom.child(name.getText().toString()).setValue(brand_new_computer);
                    Toast.makeText(Admin_B_Computer_Add.this, "Computer Added", Toast.LENGTH_SHORT).show();

                    navigateLogin();
                }

            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Admin_New_Computer_Image.class);
                startActivity(intent);
            }
        });
    }
    public void navigateLogin(){
        Intent intent = new Intent(this, Admin_Brand_New_Computer.class);
        startActivity(intent);
    }
}