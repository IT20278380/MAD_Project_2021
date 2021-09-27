package com.example.mad_project_2021.IT20479428;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_project_2021.R;
import com.example.mad_project_2021.tableModules.IT20479428.Com_Accessories;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_Com_Acc_Add extends AppCompatActivity {

    EditText name, price, comName, description, img;
    Button add, image;

    DatabaseReference addComAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_com_acc_add);

        Intent intent = getIntent();

        String uri = intent.getStringExtra("uri");

        name = (EditText) findViewById(R.id.add_cas_name);
        price = (EditText) findViewById(R.id.add_cas_price);
        comName = (EditText) findViewById(R.id.add_cas_comp);
        description = (EditText) findViewById(R.id.add_cas_des);
        img = (EditText) findViewById(R.id.add_cas_img);
        img.setText(uri);

        add = (Button) findViewById(R.id.add_casbutt);
        image = (Button) findViewById(R.id.add_comas_img);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Admin_Accessories_Com_Image.class);
                startActivity(i);

            }
        });

        addComAcc = FirebaseDatabase.getInstance().getReference().child("Computers Accessories");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Acc_Cname = name.getText().toString();
                String Acc_Cprice = price.getText().toString();
                String Acc_CcomName = comName.getText().toString();
                String Acc_Cdescription = description.getText().toString();
                String Acc_Cimg = img.getText().toString();

                Com_Accessories com_accessories = new Com_Accessories(Acc_Cname, Acc_Cprice, Acc_CcomName, Acc_Cdescription, Acc_Cimg);

                if(Acc_Cname.isEmpty() || Acc_Cprice.isEmpty() || Acc_Cprice.isEmpty() || Acc_Cdescription.isEmpty() || Acc_Cimg.isEmpty()){
                    Toast.makeText(Admin_Com_Acc_Add.this, "Fill All Details", Toast.LENGTH_SHORT).show();

                }else {
                    addComAcc.child(name.getText().toString()).setValue(com_accessories);
                    Toast.makeText(Admin_Com_Acc_Add.this, "Accessories Added", Toast.LENGTH_SHORT).show();

                    navigateLogin();
                }

            }
        });
    }
    public void navigateLogin(){
        Intent intent = new Intent(this, Admin_Computer_Accessoies.class);
        startActivity(intent);
    }
}